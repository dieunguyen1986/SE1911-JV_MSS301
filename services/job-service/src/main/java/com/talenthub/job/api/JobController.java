package com.talenthub.job.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    @GetMapping
    public ResponseEntity<List<String>> getJob() {
        return ResponseEntity.ok(List.of("Java Developer", "Manual Testing"));
    }
}
