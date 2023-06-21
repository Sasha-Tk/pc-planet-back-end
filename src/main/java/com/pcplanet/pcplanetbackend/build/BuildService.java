package com.pcplanet.pcplanetbackend.build;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class BuildService {
    private final BuildRepository buildRepository;
    private final BuildMapper buildMapper;

    public Build createBuild(BuildDTO buildDTO) {
        return buildRepository.save(buildMapper.mapToEntity(buildDTO));
    }

    public Build updateBuild(BuildDTO buildDTO) {
        Assert.notNull(buildDTO.getId(), "Build id can not be null");
        Build build = buildMapper.mapToEntity(buildDTO);
        build.setId(buildDTO.getId());
        return buildRepository.save(build);
    }

    public Build createBuildOrUpdateExisting(BuildDTO buildDTO) {
        return buildDTO.getId() == null ? createBuild(buildDTO) : updateBuild(buildDTO);
    }

    public Build findBuildById(Long id) {
        return buildRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        buildRepository.delete(findBuildById(id));
    }
}
