package com.example.datamanagementserver.controller;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/database")
public class DataBaseController {
    private final Flyway flyway;

    @PostMapping("/restart")
    public ResponseEntity<String> restartDataBase() {
        flyway.clean();
        flyway.migrate();
        return ResponseEntity.ok("Good");
    }
}
