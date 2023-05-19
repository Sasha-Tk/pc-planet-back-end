package com.pcplanet.pcplanetbackend.component;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ComponentListResponse<C> {
    private Integer pageCount;
    private List<C> componentList;
}
