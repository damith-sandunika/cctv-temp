package com.lumina.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private String message;
    private boolean success;
    @Builder.Default
    private Instant timestamp = Instant.now();

    public MessageResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
        this.timestamp = Instant.now();
    }
}
