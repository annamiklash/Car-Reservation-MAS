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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Car entity that stores all necessary information about a car
 */
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
@DiscriminatorColumn(name = "TYPE")
@SuperBuilder
public class Car implements Serializable {

    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car")
    private Long id;

    /**
     * Name of the car make
     */
    @NotNull
    @NotBlank(message = "Car make is mandatory")
    @Length(max = 20)
    @Column
    private String make;

    /**
     * Name of the car model
     */
    @NotNull
    @NotBlank(message = "Car model is mandatory")
    @Column
    @Length(max = 30)
    private String model;

    /**
     * Car color or multiple colors
     */
    @NotNull(message = "Color cannot be null")
    @Type(type = "string-array")
    @Column(
            name = "color",
            columnDefinition = "text[]"
    )
    private String[] color;

    /**
     * Car availability
     */
    @NotNull(message = "Car availability cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private CarAvailabilityEnum availability;

    /**
     * Type of a car engine
     */
    @NotNull(message = "Engine type cannot be null")
    @Enumerated(EnumType.STRING)
    @Transient
    private CarEngineTypeEnum engineType;

    /**
     * Car year of manufacture
     */
    @NotNull(message = "Car manufacture year is mandatory")
    @Min(1900)
    @Column
    private Integer manufactureYear;

    /**
     * Amount of people that can be seated in a car
     */
    @NotNull(message = "Passenger capacity cannot be null")
    @Min(1)
    private Integer passengerCapacity;

    /**
     * Car body style
     */
    @NotNull(message = "Car body style cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private CarBodyStyleEnum bodyStyle;

    /**
     * Price for reserving a car for a day
     */
    @NotNull(message = "Car daily rent cost cannot be null")
    @Column
    @Min(1)
    private Integer dailyRentCost;

    /**
     * Car unique registration plate
     */
    @NotBlank(message = "Registration plate cannot be null or empty")
    @Pattern(regexp = RegexConstants.REGISTRATION_PLATE_REGEX)
    @Column(unique = true)
    @Length(min = 7, max = 7)
    private String registrationPlate;

    /**
     * Car horse power
     */
    @Column
    @NotNull
    @Min(1)
    private Integer horsePower;

    /**
     * Information about the last car maintenance check
     */
    @NotNull
    @Embedded
    private LastMaintenanceInfo lastMaintenanceInfo;

    /**
     * Car features specific to a given car
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<CarFeature> features = new HashSet<>();

    /**
     * @param feature      type of a feature
     * @param featureValue feature value/description
     */
    public void addFeature(@NotNull CarFeatureEnum feature, @NotBlank String featureValue) {
        features.add(CarFeature.builder()
                .carFeatureEnum(feature)
                .value(featureValue)
                .owner(this)
                .build());
    }

    /**
     * @param carFeature feature to remove
     */
    public void removeFeature(@NotNull CarFeature carFeature) {
        features.remove(carFeature);
    }

    /**
     * Set of reservations places for a given car
     */
    @OneToMany(mappedBy = "car", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    Set<Reservation> carReservations = new HashSet<>();

    /**
     * @param reservation reservation to add to the list of all reservations made for a given car.
     */
    public void addReservation(@NotNull Reservation reservation) {
        carReservations.add(reservation);
    }

    /**
     * @param reservation reservation to remove from the list of all reservation made for a given car.
     */
    public void removeReservation(@NotNull Reservation reservation) {
        carReservations.remove(reservation);
    }

    /**
     * Rental location where the car is available to be reserved from.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rental_location", nullable = true)
    @JsonIgnore
    private RentalLocation rentalLocation;

    /**
     * Mechanics shop where the car might be at.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mechanics_shop", nullable = true)
    private MechanicsShop mechanicsShop;

    /**
     * @return value of a discriminator (car type).
     */
    @Transient
    public String getDecriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    /**
     * @return years since the car was manufactured
     */
    public Integer getYearsSinceManufactureYear() {
        return LocalDate.now().getYear() - this.getManufactureYear();
    }

}
