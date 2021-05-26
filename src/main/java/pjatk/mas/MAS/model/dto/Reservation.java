package pjatk.mas.MAS.model.dto;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity(name = "reservation")
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;

    @NotNull(message = "Reservation start date cannot be null")
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    LocalDate dateFrom;

    @NotNull(message = "Reservation end date cannot be null")
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    LocalDate dateTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehicle", nullable = false)
    @NotNull(message = "Vehicle cannot be null")
    Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    @NotNull(message = "Customer cannot be null")
    Customer customer;

}
