package pjatk.mas.MAS.model.dto;

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
