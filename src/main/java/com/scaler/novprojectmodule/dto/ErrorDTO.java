package com.scaler.novprojectmodule.dto;

import lombok.Getter;
import lombok.Setter;


public class ErrorDTO {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;
}
