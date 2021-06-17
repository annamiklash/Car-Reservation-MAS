package pjatk.mas.MAS.validator;

import lombok.experimental.UtilityClass;
import pjatk.mas.MAS.model.dto.Car;
import pjatk.mas.MAS.model.enums.CarEngineTypeEnum;
import pjatk.mas.MAS.validator.model.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class CarValidator {

    public List<Error> validateCarToCreate(Car car) {
        List<Error> errorList = new ArrayList<>();
        final CarEngineTypeEnum engineTypeEnum = car.getEngineTypeEnum();

        if (engineTypeEnum.equals(CarEngineTypeEnum.FUEL)) {

            errorList = validateFuel(car);

        } else if (engineTypeEnum.equals(CarEngineTypeEnum.ELECTRIC)) {
            errorList = validateElectric(car);

        } else if (engineTypeEnum.equals(CarEngineTypeEnum.HYBRID)) {
            errorList = Stream.concat(validateElectric(car).stream(),
                    validateFuel(car).stream())
                    .collect(Collectors.toList());
        }

        return errorList;
    }

    private List<Error> validateFuel(Car car) {
        final List<Error> errorList = new ArrayList<>();
        final Integer exhaustPipes = car.getExhaustPipes();
        if (exhaustPipes == null) {
            car.setExhaustPipes(exhaustPipes);
        } else {
            errorList.add(Error.builder()
                    .field("Exhaust Pipes")
                    .value("Null")
                    .description("No exhaust pipes specified for FUEL car type")
                    .build());
        }
        final Integer optionalMpg = car.getMilesPerGallon();
        if (optionalMpg == null) {
            car.setMilesPerGallon(optionalMpg);
        } else {
            errorList.add(Error.builder()
                    .field("Miles Per Gallon")
                    .value("Null")
                    .description("No miles per gallon specified for FUEL car type")
                    .build());
        }
        return errorList;

    }

    private List<Error> validateElectric(Car car) {
        final List<Error> errorList = new ArrayList<>();
        final Boolean selfDriving = car.getSelfDriving();
        if (selfDriving == null) {
            car.setIsSelfDriving(selfDriving);
        } else {
            errorList.add(Error.builder()
                    .field("Is Self Driving")
                    .value("Null")
                    .description("No flag is self driving specified for ELECTRIC car type")
                    .build());
        }
        final Integer optionalMpc = car.getMilesPerCharge();
        if (optionalMpc == null) {
            car.setMilesPerCharge(optionalMpc);
        } else {
            errorList.add(Error.builder()
                    .field("Miles Per Charge")
                    .value("Null")
                    .description("No miles per charge specified for ELECTRIC car type")
                    .build());
        }

        return errorList;
    }


}
