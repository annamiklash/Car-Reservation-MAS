package pjatk.mas.MAS.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
public class DateRange {

    @NotNull(message = "Pick up date must be selected")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String from;

    @NotNull(message = "Drop off date must be selected")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String to;
}
