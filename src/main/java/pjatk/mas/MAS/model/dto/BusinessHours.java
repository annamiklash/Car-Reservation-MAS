package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pjatk.mas.MAS.model.enums.DaysOfTheWeekEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Represents hours of operation for one working day
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class BusinessHours {

    /**
     * Opening time
     */
    @NotNull
    @Min(0)
    private Integer timeFrom;

    /**
     * Closing time
     */
    @NotNull
    @Min(0)
    private Integer timeTo;

    /**
     * Day of the week
     */
    @NotNull(message = "Day of the week cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private DaysOfTheWeekEnum day;
}
