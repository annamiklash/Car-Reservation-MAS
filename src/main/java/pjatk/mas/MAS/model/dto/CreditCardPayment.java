package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Type of payment that requires customer to enter a credit card number
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("CC")
@Entity
public class CreditCardPayment extends Payment {

    @NotNull(message = "Credit card number cannot be null")
    @NotBlank(message = "Credit card number cannot be empty")
    @Pattern(regexp = "^\\d{12}$")
    private String creditCardNumber;

}
