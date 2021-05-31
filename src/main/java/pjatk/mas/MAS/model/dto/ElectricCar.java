package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("ELECTRIC")
@Entity
public class ElectricCar extends Car implements Serializable {

    @NotNull
    @Min(1)
    @Column(name = "miles_per_charge")
    private Integer milesPerCharge;

    @NotNull
    private Boolean isSelfDriving;

}
