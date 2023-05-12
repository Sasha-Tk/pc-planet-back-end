package com.pcplanet.pcplanetbackend.component.gpu;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GPUFilterDao {
    private final EntityManager entityManager;
    private final ObjectMapper objectMapper;

    public List<GPU> findGPUByFilterParameters(GPUFilter gpuFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GPU> criteriaQuery = criteriaBuilder.createQuery(GPU.class);
        Root<GPU> root = criteriaQuery.from(GPU.class);

        Map<String, Object> fields = objectMapper.convertValue(gpuFilter, objectMapper.getTypeFactory().constructType(Map.class));
        List<Predicate> predicates = new ArrayList<>();
        fields.forEach((fieldName, fieldValue) -> {
            Method getMethod = ReflectionUtils.findMethod(gpuFilter.getClass(), "get" + StringUtils.capitalize(fieldName));
            if (getMethod != null) {
                try {
                    if (fieldValue != null && ((List<?>) getMethod.invoke(gpuFilter)).size() > 0) {
                        predicates.add(root.get(fieldName).in(((List<?>)getMethod.invoke(gpuFilter))));
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
