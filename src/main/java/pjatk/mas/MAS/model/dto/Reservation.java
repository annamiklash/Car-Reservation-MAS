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
    @Column(name = "id_reservation")
    private UUID id;

    @NotNull(message = "Reservation start date cannot be null")
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private LocalDate dateFrom;

    @NotNull(message = "Reservation end date cannot be null")
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private LocalDate dateTo;

    @Min(1)
    @NotNull(message = "Total number of people cannot be null")
    private Integer totalPeopleNumber;

    @NotNull(message = "Reservation status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private ReservationStatusEnum reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car", nullable = false)
    @JsonIgnore
    private Car car;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "Customer cannot be null")
    private User user;

    @OneToOne(cascade = CascadeType.REMOVE, optional = true, orphanRemoval = true)
    @JoinColumn(name = "id_bill", nullable = true)
    private Bill bill;

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


    public void addInsurance(@NotNull Insurance insurance) {
        insurances.add(insurance);
    }

    public void removeInsurance(@NotNull Insurance insurance) {
        insurances.remove(insurance);
    }

}
