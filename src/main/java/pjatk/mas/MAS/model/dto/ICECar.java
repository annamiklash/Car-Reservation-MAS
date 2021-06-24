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

/**
 * Class that represents a Сar with fuel type engine
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
@DiscriminatorValue("FUEL")
public class ICECar extends Car {

    /**
     * Number of exhaust pipes
     */
    @NotNull
    private Integer exhaustPipeCount;

    /**
     * Amount of miles the car can drive on one gallon
     */
    @NotNull
    private Integer milesPerGallon;

    /**
     * @return description of an ICE car in a string format
     */
    @Override
    public String toString() {
        return super.toString() +
                "exhaustPipeCount=" + exhaustPipeCount +
                ", milesPerGallon=" + milesPerGallon +
                '}';
    }
}


