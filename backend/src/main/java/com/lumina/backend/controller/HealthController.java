package com.lumina.backend.controller;

import com.lumina.backend.dto.response.HealthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    private final DataSource dataSource;

    @GetMapping
    public ResponseEntity<HealthResponse> checkHealth() {
        Map<String, Object> details = new HashMap<>();
        String dbStatus = "UNKNOWN";

        try (Connection connection = dataSource.getConnection()) {
            if (connection.isValid(2)) {
                dbStatus = "CONNECTED";
                details.put("databaseProduct", connection.getMetaData().getDatabaseProductName());
                details.put("databaseVersion", connection.getMetaData().getDatabaseProductVersion());
            } else {
                dbStatus = "DEGRADED";
            }
        } catch (Exception ex) {
            dbStatus = "DISCONNECTED";
            details.put("databaseError", ex.getMessage());
        }

        details.put("javaVersion", System.getProperty("java.version"));
        details.put("osName", System.getProperty("os.name"));

        HealthResponse response = HealthResponse.builder()
                .status("UP")
                .service("lumina-backend")
                .database(dbStatus)
                .version("1.0.0")
                .timestamp(Instant.now())
                .details(details)
                .build();

        return ResponseEntity.ok(response);
    }
}
