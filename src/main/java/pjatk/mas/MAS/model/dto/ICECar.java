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
@DiscriminatorValue("ICE")
@Entity
public class ICECar extends Car implements Serializable {

    @NotNull
    @Min(1)
    @Column(name = "exhaust_pipes")
    private Integer exhaustPipes;

    @NotNull
    @Min(1)
    @Column(name = "miles_per_gallon")
    private Integer milesPerGallon;

}
