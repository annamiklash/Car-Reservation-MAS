package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("HYBRID")
@Entity
public class HybridCar extends Car {

    @NotNull
    @Min(1)
    @Column(name = "miles_per_charge")
    private Integer milesPerCharge;

    @NotNull
    private Boolean isSelfDriving;

    @NotNull
    @Min(1)
    @Column(name = "exhaust_pipes")
    private Integer exhaustPipes;

    @NotNull
    @Min(1)
    @Column(name = "miles_per_gallon")
    private Integer milesPerGallon;

}
