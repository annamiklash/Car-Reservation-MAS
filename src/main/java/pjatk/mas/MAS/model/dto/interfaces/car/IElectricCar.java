package pjatk.mas.MAS.model.dto.interfaces.car;

/**
 * Interface containing getters and setters for fields that are required to have by an object of class Car that is an ElectricCar
 */
public interface IElectricCar {


    Integer getMilesPerCharge();

    void setMilesPerCharge(Integer milesPerCharge);

    Boolean getSelfDriving();

    void setSelfDriving(Boolean isSelfDriving);
}
