package org.testtask.exception;

public class WrongDelimitersException extends Exception {

    public WrongDelimitersException(String delimiters) {
        super(String.format("Wrong delimiters format: %s", delimiters));
    }
}
