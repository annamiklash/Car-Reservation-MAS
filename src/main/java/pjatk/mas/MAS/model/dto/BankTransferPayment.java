package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class BankTransferPayment extends Payment {

    @NotBlank
    @Length(min = 13, max = 15)
    private String bankAccountNumber;

}
