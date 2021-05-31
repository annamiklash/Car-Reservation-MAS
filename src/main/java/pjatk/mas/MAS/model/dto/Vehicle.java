package pjatk.mas.MAS.model.dto;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.Length;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_vehicle")
    private Long id;

    @NotBlank(message = "Vehicle make is mandatory")
    @Length(max = 20)
    @Column
    private String make;

    @Column
    @Length(max = 30)
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
    @Min(1)
    private Integer dailyRentCost;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private LastMaintenanceInfo lastMaintenanceInfo;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Reservation> vehicleReservations = new HashSet<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<VehicleFeature> features = new HashSet<>();

    public void addReservation(@NotNull Reservation reservation) {
        vehicleReservations.add(reservation);
    }

    public void removeReservation(@NotNull Reservation reservation) {
        vehicleReservations.remove(reservation);
    }

}
