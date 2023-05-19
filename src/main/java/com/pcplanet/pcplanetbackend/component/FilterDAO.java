package com.pcplanet.pcplanetbackend.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
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
public class FilterDAO<C, F extends ComponentFilter> {
    private final EntityManager entityManager;
    private final ObjectMapper objectMapper;


    public ComponentListResponse<C> findComponentsByFilter(Class<C> componentClass, F filter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<C> query = criteriaBuilder.createQuery(componentClass);
        Root<C> root = query.from(componentClass);
        Map<String, Object> fields = objectMapper.convertValue(filter, objectMapper.getTypeFactory().constructType(Map.class));
        List<Predicate> predicates = new ArrayList<>();
        fields.forEach((fieldName, fieldValue) -> {
            Method getMethod = ReflectionUtils.findMethod(filter.getClass(), "get" + StringUtils.capitalize(fieldName));
            Field field = ReflectionUtils.findField(componentClass, fieldName);
            if (getMethod != null && field != null) {
                try {
                    if (fieldValue != null && ((List<?>) getMethod.invoke(filter)).size() > 0) {
                        if (field.getType().equals(List.class)) {
                            Join<C, ?> join = root.join(fieldName);
                            predicates.add(join.in((List<?>) getMethod.invoke(filter)));
                        } else {
                            if (filter.rangeFiltersName().contains(fieldName)) {
                                String minValue = String.valueOf(((List<?>) getMethod.invoke(filter)).get(0));
                                String maxValue = String.valueOf(((List<?>) getMethod.invoke(filter)).get(1));
                                predicates.add(criteriaBuilder.between(
                                        root.get(fieldName),
                                        criteriaBuilder.literal(minValue.equals("null") ? null : minValue),
                                        criteriaBuilder.literal(maxValue.equals("null") ? null : maxValue)
                                ));
                            } else {
                                predicates.add(root.get(fieldName).in(((List<?>) getMethod.invoke(filter))));
                            }
                        }
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        query.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        ).orderBy(
                QueryUtils.toOrders(pageable.getSort(), root, criteriaBuilder)
        );

        TypedQuery<C> typedQuery = entityManager.createQuery(query);

        Integer count = (int) Math.ceil(typedQuery.getResultList().size() / (float) pageable.getPageSize());
        return new ComponentListResponse<>(count, entityManager
                .createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize()).getResultList());
    }


    public F getFilter(Class<C> componentClass, Class<F> filterClass) {
        try {
            F filter = filterClass.getDeclaredConstructor().newInstance();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            Field[] fields = filterClass.getDeclaredFields();
            for (Field field : fields) {

                CriteriaQuery<?> query = criteriaBuilder.createQuery();
                Root<?> root = query.from(componentClass);
                Method setMethod = filterClass.getDeclaredMethod("set" + StringUtils.capitalize(field.getName()), List.class);
                if (filter.rangeFiltersName().contains(field.getName())) {
                    CriteriaQuery<Number> rangeQuery = criteriaBuilder.createQuery(Number.class);
                    rangeQuery.select(criteriaBuilder.min(rangeQuery.from(componentClass).get(field.getName())));
                    Number min = entityManager.createQuery(rangeQuery).getSingleResult();
                    rangeQuery.select(criteriaBuilder.max(rangeQuery.from(componentClass).get(field.getName())));
                    Number max = entityManager.createQuery(rangeQuery).getSingleResult();
                    setMethod.invoke(filter, min != null && max != null ? List.of(min, max) : new ArrayList<>());
                } else {
                    query.select(root.get(field.getName())).distinct(true);
                    setMethod.invoke(filter, entityManager.createQuery(query).getResultList());
                }
            }
            return filter;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
