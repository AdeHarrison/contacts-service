package com.ccs.contacts.api.dto;

public class ErrorMessageDTO {
    private final String cause;
    private final String detail;

    public ErrorMessageDTO(String cause, String detail) {
        this.cause = cause;
        this.detail = detail;
    }

    public String getCause() {
        return cause;
    }

    public String getDetail() {
        return detail;
    }
}
