package pjatk.mas.MAS.validator.model;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@ToString
@Builder
@Value
public class Error {

    String field;

    String value;

    String description;
}
