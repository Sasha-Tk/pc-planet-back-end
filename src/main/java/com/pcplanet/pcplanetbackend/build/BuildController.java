package com.pcplanet.pcplanetbackend.build;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/builds")
@RequiredArgsConstructor
public class BuildController {
    private final BuildService buildService;
    @PostMapping("/filters")
    public ResponseFiltersInfo getFiltersForBuild(@RequestBody BuildDTO buildDTO){
        return buildService.getFiltersForBuild(buildDTO);
    }

    @PostMapping("/compatibility")
    public BuildCheckResponse checkCompatibility(@RequestBody BuildDTO buildDTO){
        System.out.println(buildDTO);
        return buildService.checkCompatibility(buildDTO);
    }
}
