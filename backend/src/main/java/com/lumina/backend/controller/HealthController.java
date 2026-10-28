package com.lumina.backend.controller;

import com.lumina.backend.dto.response.HealthResponse;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    private final MongoTemplate mongoTemplate;

    @GetMapping
    public ResponseEntity<HealthResponse> checkHealth() {
        Map<String, Object> details = new HashMap<>();
        String dbStatus = "UNKNOWN";

        try {
            Document pingResult = mongoTemplate.getDb().runCommand(new Document("ping", 1));
            if (pingResult.containsKey("ok") && ((Number) pingResult.get("ok")).intValue() == 1) {
                dbStatus = "CONNECTED";
                details.put("databaseName", mongoTemplate.getDb().getName());
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
