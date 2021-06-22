package pjatk.mas.MAS.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class DateRange {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String from;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String to;
}
