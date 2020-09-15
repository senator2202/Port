package com.kharitonov.port.exception;

public class ProjectFileReaderException extends Exception {
    public ProjectFileReaderException() {
    }

    public ProjectFileReaderException(String message) {
        super(message);
    }

    public ProjectFileReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectFileReaderException(Throwable cause) {
        super(cause);
    }
}
