package pjatk.mas.MAS.interfaces.car;

public interface HybridCar {

    Integer getExhaustPipes();

    Integer getMilesPerGallon();

    Integer getMilesPerCharge();

    Boolean getSelfDriving();

    void setExhaustPipes(Integer exhaustPipes);

    void setMilesPerGallon(Integer milesPerGallon);

    void setMilesPerCharge(Integer milesPerCharge);

    void setIsSelfDriving(Boolean isSelfDriving);
}
