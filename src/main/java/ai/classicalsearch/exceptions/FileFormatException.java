package ai.classicalsearch.exceptions;

import java.io.IOException;

public class FileFormatException extends IOException {

    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException() {
        this("File that defines the problem is not in the correct format.");
    }
}
