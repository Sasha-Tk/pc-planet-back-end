package com.pcplanet.pcplanetbackend.content;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("api/v1/content")
public class ContentController {
    @GetMapping("{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        File imageFile = new File("src/main/resources/static/images/" + imageName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(Files.readAllBytes(imageFile.toPath()));
    }
}
