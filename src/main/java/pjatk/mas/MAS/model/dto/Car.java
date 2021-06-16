package pjatk.mas.MAS.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.Length;
import pjatk.mas.MAS.constants.RegexConstants;
import pjatk.mas.MAS.interfaces.ElectricCar;
import pjatk.mas.MAS.interfaces.FuelCar;
import pjatk.mas.MAS.interfaces.HybridCar;
import pjatk.mas.MAS.model.enums.CarAvailabilityEnum;
import pjatk.mas.MAS.model.enums.CarBodyStyleEnum;
import pjatk.mas.MAS.model.enums.CarEngineTypeEnum;
import pjatk.mas.MAS.model.enums.CarFeatureEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
@Entity(name = "car")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED) //joined
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Car implements Serializable, FuelCar, ElectricCar, HybridCar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private Long id;

    @NotNull
    @NotBlank(message = "Car make is mandatory")
    @Length(max = 20)
    @Column
    private String make;

    @NotNull
    @NotBlank(message = "Car model is mandatory")
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

    @NotNull(message = "Car availability cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private CarAvailabilityEnum availability;

    @NotNull(message = "Car manufacture year is mandatory")
    @Min(1900)
    @Column
    private Integer manufactureYear;

    @NotNull(message = "Passenger capacity cannot be null")
    @Min(1)
    private Integer passengerCapacity;

    @NotNull(message = "Car body style cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private CarBodyStyleEnum bodyStyle;

    @NotNull(message = "Car engine type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private CarEngineTypeEnum engineTypeEnum;

    @NotNull(message = "Car daily rent cost cannot be null")
    @Column
    @Min(1)
    private Integer dailyRentCost;

    @NotBlank(message = "Registration plate cannot be null or empty")
    @Pattern(regexp = RegexConstants.REGISTRATION_PLATE_REGEX)
    @Column(unique = true)
    @Length(min = 7, max = 7)
    private String registrationPlate;

    @Column
    @NotNull
    @Min(1)
    private Integer horsePower;

    @NotNull
    @Embedded
    private LastMaintenanceInfo lastMaintenanceInfo;

    @Min(1)
    @Column(name = "exhaust_pipes")
    private Integer exhaustPipes;

    @Min(1)
    @Column(name = "miles_per_gallon")
    private Integer milesPerGallon;


    @Min(1)
    @Column(name = "miles_per_charge")
    private Integer milesPerCharge;

    private Boolean isSelfDriving;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<CarFeature> features = new HashSet<>();

    public void addFeature(@NotNull CarFeatureEnum feature, @NotBlank String featureValue) {
        features.add(CarFeature.builder()
                .carFeatureEnum(feature)
                .value(featureValue)
                .owner(this)
                .build());
    }

    public void removeFeature(@NotNull CarFeature carFeature) {
        features.remove(carFeature);
    }

    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    Set<Reservation> carReservations = new HashSet<>();

    public void addReservation(@NotNull Reservation reservation) {
        carReservations.add(reservation);
    }

    public void removeReservation(@NotNull Reservation reservation) {
        carReservations.remove(reservation);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rental_location", nullable = true)
    @JsonIgnore
    private RentalLocation rentalLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mechanics_shop", nullable = true)
    private MechanicsShop mechanicsShop;


    @Override
    public Boolean getSelfDriving() {
        return isSelfDriving;
    }

    @Override
    public Integer getExhaustPipes() {
        return exhaustPipes;
    }

    @Override
    public Integer getMilesPerCharge() {
        return milesPerCharge;
    }

    @Override
    public Integer getMilesPerGallon() {
        return milesPerGallon;
    }
}
