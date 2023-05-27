package com.pcplanet.pcplanetbackend.component.gpu.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ComponentFilterDTOResponseItem {
    private String filterName;
    private String filterType;
    private List<?> availableFilters;
}
