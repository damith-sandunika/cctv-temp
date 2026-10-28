package com.lumina.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> allAccess() {
        return ResponseEntity.ok(Map.of("message", "Public Content - Accessible without authentication"));
    }

    @GetMapping("/superadmin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Map<String, String>> superAdminAccess() {
        return ResponseEntity.ok(Map.of("message", "Super Admin Board - Accessible only by SUPER_ADMIN"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<Map<String, String>> adminAccess() {
        return ResponseEntity.ok(Map.of("message", "Admin Board - Accessible by ADMIN and SUPER_ADMIN"));
    }

    @GetMapping("/technician")
    @PreAuthorize("hasRole('TECHNICIAN')")
    public ResponseEntity<Map<String, String>> technicianAccess() {
        return ResponseEntity.ok(Map.of("message", "Technician Portal - Accessible only by TECHNICIAN"));
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<Map<String, String>> clientAccess() {
        return ResponseEntity.ok(Map.of("message", "Client Portal - Accessible by CLIENT"));
    }
}
