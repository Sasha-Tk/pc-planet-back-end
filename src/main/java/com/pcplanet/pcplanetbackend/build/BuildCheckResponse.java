package com.pcplanet.pcplanetbackend.build;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BuildCheckResponse {
    private List<String> gpu = new ArrayList<>();
    private List<String> psu = new ArrayList<>();
    private List<String> motherboard = new ArrayList<>();
    private List<String> cpu = new ArrayList<>();
    private List<String> ram = new ArrayList<>();
    private List<String> computerCase = new ArrayList<>();
}
