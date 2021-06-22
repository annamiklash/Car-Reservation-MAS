package pjatk.mas.MAS.validator;

import lombok.experimental.UtilityClass;
import pjatk.mas.MAS.model.dto.BusinessHours;
import pjatk.mas.MAS.validator.model.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class BusinessHoursValidator {


    public List<Error> validateBusinessHoursSet(Set<BusinessHours> businessHours) {

        final List<Error> errorList = new ArrayList<>();
        if (!isCorrectSize(businessHours)) {
            errorList.add(Error.builder()
                    .field("Size")
                    .description("Size must be not less than 5 and not greater than 7")
                    .build());
        }
        if (containsDuplicates(businessHours)) {
            errorList.add(Error.builder()
                    .field("Day")
                    .description("There cannot be duplicates")
                    .build());
        }

        return errorList;
    }

    private boolean containsDuplicates(Set<BusinessHours> businessHours) {
        return businessHours.stream()
                .map(BusinessHours::getDay)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))    // create a map {1=1, 2=1, 3=2, 4=2, 5=1, 7=1, 9=2}
                .entrySet()
                .stream()
                .anyMatch(m -> m.getValue() > 1);
    }

    private boolean isCorrectSize(Set<BusinessHours> businessHours) {
        return businessHours.size() >= 5 && businessHours.size() <= 7;
    }
}
