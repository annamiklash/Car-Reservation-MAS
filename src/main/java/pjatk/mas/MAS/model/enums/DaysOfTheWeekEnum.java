package pjatk.mas.MAS.model.enums;

import lombok.Getter;

@Getter
public enum DaysOfTheWeekEnum {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7);

    private final int dayNumber;

    private DaysOfTheWeekEnum(int dayNumber) {
        this.dayNumber = dayNumber;
    }

}
