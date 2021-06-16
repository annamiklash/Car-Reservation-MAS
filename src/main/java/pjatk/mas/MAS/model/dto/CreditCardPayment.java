package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("CC")
@Entity
public class CreditCardPayment extends Payment {

    @Pattern(regexp = "^\\d{12}$")
    private String creditCardNumber;

}
