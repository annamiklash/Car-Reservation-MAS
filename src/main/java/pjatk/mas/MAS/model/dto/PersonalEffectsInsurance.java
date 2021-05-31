package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("PERSONAL_EFFECTS")
@Entity
public class PersonalEffectsInsurance extends Insurance implements Serializable {

    @NotNull
    @Min(1)
    @Column(name = "personal_effects_value")
    private Integer personalEffectsValue;
}
