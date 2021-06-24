package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Represents payment summary for a reservation
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "invoice")
public class Invoice {

    /**
     * Unique ID in a from of sequence of random strings (ex: 496a4713-e4b1-448e-9b6e-dad39cfb6b33)
     */
    @Id
    @Column(name = "id_bill")
    private UUID id;

    /**
     * Amount of money saved by the discount
     */
    @Min(0)
    private Integer discountedAmount;

    /**
     * Amount due before discount applied
     */
    @NotNull
    @Min(0)
    private Integer totalCost;

    /**
     * Amount due after discount applied
     */
    @Transient
    private Integer amountAfterDiscount;


    /**
     * Reservation that the invoice is generated for
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_reservation")
    private Reservation reservation;

}
