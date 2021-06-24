package pjatk.mas.MAS.validator.model;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

/**
 * Custom error
 */
@ToString
@Builder
@Value
public class Error {

    /**
     * filed that contains error
     */
    String field;


    /**
     * Value that is not correct
     */
    String value;

    /**
     * Additional information about error
     */
    String description;
}
