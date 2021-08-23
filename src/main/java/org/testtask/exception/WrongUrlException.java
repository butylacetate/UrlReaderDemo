package org.testtask.exception;

public class WrongUrlException extends Exception {

    public WrongUrlException(String url) {
        super(String.format("Wrong URL address: %s", url));
    }
}
