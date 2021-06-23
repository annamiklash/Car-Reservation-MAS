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

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table
@DiscriminatorValue("HYBRID")
public class HybridCar extends ICECar implements IElectricCar {

    @NotNull
    private Boolean isManualGearBox;

    @NotNull
    private Integer milesPerCharge;

    @NotNull
    private Boolean isSelfDriving;

    @Override
    public Integer getMilesPerCharge() {
        return milesPerCharge;
    }

    @Override
    public void setMilesPerCharge(Integer milesPerCharge) {
        this.milesPerCharge = milesPerCharge;
    }

    @Override
    public Boolean getSelfDriving() {
        return isSelfDriving;
    }

    @Override
    public void setSelfDriving(Boolean isSelfDriving) {
        this.setIsSelfDriving(isSelfDriving);
    }
}
