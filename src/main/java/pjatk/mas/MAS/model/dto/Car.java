package pjatk.mas.MAS.model.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;
import pjatk.mas.MAS.constants.RegexConstants;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ENGINE_TYPE")
@Entity(name = "car")
public class Car extends Vehicle implements Serializable {

    @NotBlank(message = "Registration plate cannot be null or empty")
    @Pattern(regexp = RegexConstants.REGISTRATION_PLATE_REGEX)
    @Column(name = "registration_plate")
    @Length(min = 7, max = 7)
    private String registrationPlate;

    @Column(name = "horse_power")
    @NotNull
    @Min(1)
    private Integer horsePower;


}
