package pjatk.mas.MAS.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Payment entity that stores information about payment for invoice
 */
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
@Entity(name = "payment")
public class Payment {

    /**
     * Unique ID for entity Payment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long id;

    /**
     * Flag whether Payment is payed or not
     */
    @NotNull
    private Boolean isPayed;

    /**
     * Amount of money payed
     */
    @NotNull
    @Min(0)
    private Integer amountPayed;
}
