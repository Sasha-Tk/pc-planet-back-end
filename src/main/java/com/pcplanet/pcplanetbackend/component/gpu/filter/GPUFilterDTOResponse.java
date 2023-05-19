package com.pcplanet.pcplanetbackend.component.gpu.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class GPUFilterDTOResponse {
    private List<GPUFilterDTOResponseItem> filters = new ArrayList<>();

    public GPUFilterDTOResponse(GPUFilterDTO filterDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> fields = objectMapper.convertValue(filterDTO, objectMapper.getTypeFactory().constructType(Map.class));
        fields.forEach((fieldName, fieldValue) ->
                filters.add(new GPUFilterDTOResponseItem(
                        fieldName,
                        filterDTO.rangeFiltersName().contains(fieldName) ? "range" : "default",
                        (List<?>) fieldValue
                ))
        );
    }
}
