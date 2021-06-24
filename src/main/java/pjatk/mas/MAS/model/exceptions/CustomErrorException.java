package pjatk.mas.MAS.model.exceptions;

import pjatk.mas.MAS.validator.model.Error;

import java.util.List;

/**
 * Custom exception
 */
public class CustomErrorException extends RuntimeException {

    private List<Error> errors;
    private Error error;

    public CustomErrorException(List<Error> errors) {
        super();
        this.errors = errors;
    }

    public CustomErrorException(Error error) {
        super();
        this.error = error;
    }
}
