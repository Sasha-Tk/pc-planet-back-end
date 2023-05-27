package com.pcplanet.pcplanetbackend.build;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ResponseFiltersInfo {
    private HashMap<String, List<?>> filters = new HashMap<>();

    public void addNewFilter(String componentName, List<?> filterList) {
        this.filters.put(componentName, filterList);
    }
}
