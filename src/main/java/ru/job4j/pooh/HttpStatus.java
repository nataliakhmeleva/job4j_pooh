package ru.job4j.pooh;

public enum HttpStatus {
    OK_200("200"), NO_CONTEXT_204("204");
    private final String state;


    HttpStatus(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
