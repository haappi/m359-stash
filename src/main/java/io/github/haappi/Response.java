package io.github.haappi;

public class Response {
    private final boolean succeeded;
    private final String message;


    public Response(String message) {
        this.message = message;
        this.succeeded = true;
    }

    public Response(boolean succeeded, String message) {
        this.succeeded = succeeded;
        this.message = message;
    }

    public Response(String message, boolean succeeded) {
        this.succeeded = succeeded;
        this.message = message;
    }


    public boolean succeeded() {
        return succeeded;
    }

    public boolean failed() {
        return !succeeded;
    }

    public String getMessage() {
        return message;
    }
}
