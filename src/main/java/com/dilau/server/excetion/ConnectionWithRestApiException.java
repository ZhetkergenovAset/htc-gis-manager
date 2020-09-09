package com.dilau.server.excetion;

public class ConnectionWithRestApiException extends Exception {
    public ConnectionWithRestApiException() {
    }

    public ConnectionWithRestApiException(String message) {
        super(message);
    }
}
