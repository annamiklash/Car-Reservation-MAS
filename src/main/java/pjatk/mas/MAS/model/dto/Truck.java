package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pjatk.mas.MAS.constants.RegexConstants;
import pjatk.mas.MAS.model.Dimensions;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Entity(name = "truck")
public class Truck extends Vehicle {

    @NotBlank(message = "Licence plate cannot be null or empty")
    @Pattern(regexp = RegexConstants.REGISTRATION_PLATE_REGEX)
    @Column(name = "registration_plate")
    String registrationPlate;

    @NotNull
    @Embedded
    Dimensions trailerDimensions;

    @NotNull
    @Column(name = "wheels_number")
    @Min(4)
    Integer wheelsNumber;

}
