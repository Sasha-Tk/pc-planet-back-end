package com.pcplanet.pcplanetbackend.component.gpu.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentFilterDTOResponseItem {
    private String filterName;
    private String filterType;
    private List<?> availableFilters;

    public ComponentFilterDTOResponseItem(ComponentFilterDTOResponseItem componentFilterDTOResponseItem) {
        this.filterName = componentFilterDTOResponseItem.getFilterName();
        this.filterType = componentFilterDTOResponseItem.getFilterType();
        this.availableFilters = new ArrayList<>(componentFilterDTOResponseItem.getAvailableFilters());
    }
}
