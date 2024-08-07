package com.example.libraryManagementSystem.Response;

import lombok.Data;

@Data
public class UpdateResponse {
    private String message;
    private boolean updated;

    public UpdateResponse(String message, boolean updated) {
        this.message = message;
        this.updated = updated;
    }

}
