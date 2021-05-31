package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("COLLISION_DAMAGE")
@Entity
public class CollisionDamageInsurance extends Insurance {

    @NotBlank
    @Length(min = 10, max = 10)
    @Column(name = "drivers_licence")
    private String driversLicence;


}
