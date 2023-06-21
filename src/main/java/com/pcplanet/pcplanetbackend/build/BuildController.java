package com.pcplanet.pcplanetbackend.build;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/builds")
@RequiredArgsConstructor
public class BuildController {
    private final BuildService buildService;

    public ResponseEntity<Build> createBuildOrUpdateExisting(@RequestBody BuildDTO buildDTO) {
        return ResponseEntity.ok(buildService.createBuildOrUpdateExisting(buildDTO));
    }
}
