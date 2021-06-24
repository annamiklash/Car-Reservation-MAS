package pjatk.mas.MAS.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import pjatk.mas.MAS.model.enums.ReservationStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a single car reservation
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity(name = "reservation")
public class Reservation implements Serializable {

    /**
     * Unique ID in a from of sequence of random strings (ex: 496a4713-e4b1-448e-9b6e-dad39cfb6b33)
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_reservation")
    private UUID id;

    /**
     * Car pick up date
     */
    @NotNull(message = "Reservation start date cannot be null")
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate dateFrom;

    /**
     * Car drop off date
     */
    @NotNull(message = "Reservation end date cannot be null")
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate dateTo;

    /**
     * Amount of people for reservation
     */
    @Min(1)
    @NotNull(message = "Total number of people cannot be null")
    private Integer totalPeopleNumber;

    /**
     * The current status of a reservation
     */
    @NotNull(message = "Reservation status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    @Builder.Default
    private ReservationStatusEnum reservationStatus = ReservationStatusEnum.COMPLETE;

    /**
     * A car that is reserved for a given reservation
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car", nullable = false)
    @JsonIgnore
    private Car car;

    /**
     * User that is reserving a car
     */
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "Customer cannot be null")
    private User user;

    /**
     * Invoice for a given reservation
     */
    @OneToOne(cascade = CascadeType.REMOVE, optional = true, orphanRemoval = true)
    @JoinColumn(name = "id_bill", nullable = true)
    private Invoice invoice;

    /**
     * Insurances picked for a given reservation
     */
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "reservation_insurance",
            joinColumns = {@JoinColumn(name = "id_reservation", referencedColumnName = "id_reservation")},
            inverseJoinColumns = {@JoinColumn(name = "id_insurance", referencedColumnName = "id_insurance")}
    )
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Insurance> insurances = new HashSet<>();

    /**
     * @param insurance insurance to add to the list of selected insurances
     */
    public void addInsurance(@NotNull Insurance insurance) {
        insurances.add(insurance);
    }

    /**
     * @param insurance insurance to remove from the list of selected insurances
     */
    public void removeInsurance(@NotNull Insurance insurance) {
        insurances.remove(insurance);
    }

}
