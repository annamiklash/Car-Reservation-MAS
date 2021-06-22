package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
@DiscriminatorValue("FUEL")
public class ICECar extends Car {

    @NotNull
    private Integer exhaustPipeCount;


    @NotNull
    private Integer milesPerGallon;

    @Override
    public String toString() {
        return super.toString() +
                "exhaustPipeCount=" + exhaustPipeCount +
                ", milesPerGallon=" + milesPerGallon +
                '}';
    }
}


