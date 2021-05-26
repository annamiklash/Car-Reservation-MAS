package pjatk.mas.MAS.model.dto;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import pjatk.mas.MAS.model.enums.VehicleAvailabilityEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@TypeDefs({
        @TypeDef(
                name = "string-array",
                typeClass = StringArrayType.class
        )
})
@Entity(name = "vehicle")
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_vehicle")
    private Long id;

    @NotBlank(message = "Vehicle make is mandatory")
    @Column
    private String make;

    @Column
    private String model;

    @NotNull(message = "Color cannot be null")
    @Type(type = "string-array")
    @Column(
            name = "color",
            columnDefinition = "text[]"
    )
    private String[] color;

    @NotNull(message = "Vehicle availability cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private VehicleAvailabilityEnum availability;

    @NotNull(message = "Vehicle daily rent cost cannot be null")
    @Column(name = "daily_rent_cost")
    @Min(0)
    private Integer dailyRentCost;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Reservation> vehicleReservations = new HashSet<>();

    public void addReservation(@NotNull Reservation reservation) {
        vehicleReservations.add(reservation);
    }

    public void removeReservation(@NotNull Reservation reservation) {
        vehicleReservations.remove(reservation);
    }

}
