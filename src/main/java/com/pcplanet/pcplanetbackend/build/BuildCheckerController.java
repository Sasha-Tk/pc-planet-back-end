package com.pcplanet.pcplanetbackend.build;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/builds/compatibility-checker")
@RequiredArgsConstructor
public class BuildCheckerController {
    private final BuildCheckerService buildCheckerService;
    @PostMapping("/filters")
    public ResponseFiltersInfo getFiltersForBuild(@RequestBody BuildDTO buildDTO){
        System.out.println(buildDTO);
        return buildCheckerService.getFiltersForBuild(buildDTO);
    }

    @PostMapping
    public BuildCheckResponse checkCompatibility(@RequestBody BuildDTO buildDTO){
        return buildCheckerService. checkCompatibility(buildDTO);
    }
}
