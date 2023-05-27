package com.pcplanet.pcplanetbackend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class PartialUpdate<C> {
    private final ObjectMapper objectMapper;

    public C update(C target, C updater) {
        Map<String, Object> fields = objectMapper.convertValue(updater, objectMapper.getTypeFactory().constructType(Map.class));
        fields.forEach((fieldName, fieldValue) -> {
            if (fieldValue != null) {
                Method setMethod = ReflectionUtils.findMethod(target.getClass(), "set" + StringUtils.capitalize(fieldName), null);
                Method getMethod = ReflectionUtils.findMethod(updater.getClass(), "get" + StringUtils.capitalize(fieldName));
                if (setMethod != null && getMethod != null) {
                    try {
                        setMethod.invoke(target, getMethod.invoke(updater));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        return target;
    }
}
