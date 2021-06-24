package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import pjatk.mas.MAS.model.dto.interfaces.car.IElectricCar;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Class that represents a Hybrid Car which is a mix of ICECar(FUEL) and Electric
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
@DiscriminatorValue("HYBRID")
public class HybridCar extends ICECar implements IElectricCar {

    /**
     * Flag whether the car has manual gear box or not
     */
    @NotNull
    private Boolean isManualGearBox;

    /**
     * Amount of miles the car can drive on one charge
     */
    @NotNull
    private Integer milesPerCharge;

    /**
     * Flag whether the car is able to drive by itself
     */
    @NotNull
    private Boolean isSelfDriving;

    /**
     * @return Amount of miles the car can drive on one charge
     */
    @Override
    public Integer getMilesPerCharge() {
        return milesPerCharge;
    }

    /**
     * @param milesPerCharge Amount of miles the car can drive on one charge
     */
    @Override
    public void setMilesPerCharge(Integer milesPerCharge) {
        this.milesPerCharge = milesPerCharge;
    }

    /**
     * @return Flag whether the car is able to drive by itself
     */
    @Override
    public Boolean getSelfDriving() {
        return isSelfDriving;
    }

    /**
     * @param isSelfDriving Flag whether the car is able to drive by itself
     */
    @Override
    public void setSelfDriving(Boolean isSelfDriving) {
        this.setIsSelfDriving(isSelfDriving);
    }
}
