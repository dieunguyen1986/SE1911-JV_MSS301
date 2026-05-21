package com.talenthub.candidate.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final RestTemplate restTemplate;

    @GetMapping("/ping")
    public Map<?, ?> ping(){
        return Map.of("status","ok",
                "message", "Candidate service hello world!"
                );
    }

    @GetMapping("/getting-jobs")
    public Map<?, ?> getJob(){

        return Map.of(
                "message", "Get all of jobs",
                "data", restTemplate.getForEntity("http://localhost:8081/api/v1/jobs", List.class).getBody()
        );
    }
}
