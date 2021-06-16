package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "bill")
public class Bill {

    @Id
    @Column(name = "id_bill")
    private UUID id;

    @Min(0)
    private Integer discountAmount;

    @NotNull
    @Min(0)
    private Integer totalCost;

    @Transient
    private Integer amountAfterDiscount;

    @NotNull
    private Boolean isPayed;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id_reservation")
//    private Reservation reservation;

}
