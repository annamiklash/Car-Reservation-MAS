package pjatk.mas.MAS.model.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
import pjatk.mas.MAS.constants.RegexConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
@Entity(name = "car")
@Table(name = "car")
public class Car extends Vehicle implements Serializable {

    @NotBlank(message = "Registration plate cannot be null or empty")
    @Pattern(regexp = RegexConstants.REGISTRATION_PLATE_REGEX)
    @Column(name = "registration_plate")
    String registrationPlate;

    @Column(name = "horse_power")
    @NotNull
    Integer horsePower;

}
