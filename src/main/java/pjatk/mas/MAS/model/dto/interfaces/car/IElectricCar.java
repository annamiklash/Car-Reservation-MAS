package pjatk.mas.MAS.model.dto.interfaces.car;

public interface IElectricCar {


    Integer getMilesPerCharge();

    void setMilesPerCharge(Integer milesPerCharge);

    Boolean getSelfDriving();

    void setSelfDriving(Boolean isSelfDriving);
}
