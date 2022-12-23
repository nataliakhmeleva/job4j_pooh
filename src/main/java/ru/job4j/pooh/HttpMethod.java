package ru.job4j.pooh;

public enum HttpMethod {
    POST("POST"), GET("GET");
    private final String request;

    HttpMethod(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
