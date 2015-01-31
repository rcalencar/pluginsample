package com.rodrigo.pluginsample;

public class ServiceResponse {

    private String value;
    private Status status;
    private String errorDescription;

    public ServiceResponse(String value, Status status, String errorDescription) {
        super();
        this.value = value;
        this.status = status;
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return "ServiceResponse [value=" + value + ", status=" + status + ", errorDescription=" + errorDescription + "]";
    }

    public String getValue() {
        return value;
    }

    public Status getStatus() {
        return status;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
