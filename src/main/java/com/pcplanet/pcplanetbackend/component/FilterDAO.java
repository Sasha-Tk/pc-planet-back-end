package com.pcplanet.pcplanetbackend.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FilterDAO<C, F> {
    private final EntityManager entityManager;
    private final ObjectMapper objectMapper;

    public List<C> findComponentsByFilter(Class<C> componentClass, F filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<C> query = criteriaBuilder.createQuery(componentClass);
        Root<C> root = query.from(componentClass);

        Map<String, Object> fields = objectMapper.convertValue(filter, objectMapper.getTypeFactory().constructType(Map.class));
        List<Predicate> predicates = new ArrayList<>();
        fields.forEach((fieldName, fieldValue) -> {
            Method getMethod = ReflectionUtils.findMethod(filter.getClass(), "get" + StringUtils.capitalize(fieldName));
            if (getMethod != null) {
                try {
                    if (fieldValue != null && ((List<?>) getMethod.invoke(filter)).size() > 0) {
                        if (componentClass.getDeclaredField(fieldName).getType().equals(List.class)) {
                            Join<C, ?> join = root.join(fieldName);
                            predicates.add(join.in((List<?>) getMethod.invoke(filter)));
                        } else {
                            predicates.add(root.get(fieldName).in(((List<?>) getMethod.invoke(filter))));
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }

    public F getFilter(Class<C> componentClass, Class<F> filterClass) {
        try {
            F filter = filterClass.getDeclaredConstructor().newInstance();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            Field[] fields = filterClass.getDeclaredFields();
            for (Field field : fields) {
                CriteriaQuery<?> query = criteriaBuilder.createQuery();
                Root<?> root = query.from(componentClass);
                query.select(root.get(field.getName())).distinct(true);
                Method setMethod = filterClass.getDeclaredMethod("set" + StringUtils.capitalize(field.getName()), List.class);
                setMethod.invoke(filter, entityManager.createQuery(query).getResultList());
            }
            return filter;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
