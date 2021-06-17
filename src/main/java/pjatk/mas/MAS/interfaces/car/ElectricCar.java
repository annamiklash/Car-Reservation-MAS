package pjatk.mas.MAS.interfaces.car;

public interface ElectricCar {

    Integer getMilesPerCharge();

    Boolean getSelfDriving();

    void setMilesPerCharge(Integer milesPerCharge);

    void setIsSelfDriving(Boolean isSelfDriving);
}
