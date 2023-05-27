package com.pcplanet.pcplanetbackend.component.gpu.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class ComponentFilterDTOResponse {
    private List<ComponentFilterDTOResponseItem> filters = new ArrayList<>();

    public ComponentFilterDTOResponse(ComponentFilter filterDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> fields = objectMapper.convertValue(filterDTO, objectMapper.getTypeFactory().constructType(Map.class));
        fields.forEach((fieldName, fieldValue) ->
                filters.add(new ComponentFilterDTOResponseItem(
                        fieldName,
                        filterDTO.rangeFiltersName().contains(fieldName) ? "range" : "default",
                        (List<?>) fieldValue
                ))
        );
    }

    public ComponentFilterDTOResponseItem getFilterByName(String filterName) {
        return filters.stream().filter(filter -> filter.getFilterName().equals(filterName)).toList().get(0);
    }

    public void addFilter(ComponentFilterDTOResponseItem newFilter){
        this.filters.add(newFilter);
    }
}
