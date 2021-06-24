package pjatk.mas.MAS.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import pjatk.mas.MAS.constants.RegexConstants;
import pjatk.mas.MAS.model.dto.interfaces.location.OpenLocation;
import pjatk.mas.MAS.model.dto.interfaces.location.TemporarilyClosedLocation;
import pjatk.mas.MAS.model.enums.LocationTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a location where a car can be rented from
 */
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "rental_location")
public class RentalLocation implements Serializable, OpenLocation, TemporarilyClosedLocation {

    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental_location")
    private Long id;

    /**
     * Rental Location name
     */
    @NotBlank(message = "Company name cannot be null or empty")
    @Pattern(regexp = RegexConstants.NAME_REGEX)
    @Column(name = "rental_location_name")
    private String name;

    /**
     * Rental Location email
     */
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please provide a valid email address")
    @Column
    private String email;

    /**
     * Rental Location phone number
     */
    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    @Digits(integer = 9, fraction = 0)
    private BigInteger phoneNumber;

    /**
     * Rental Location type (open or closed)
     */
    @NotNull(message = "Location type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private LocationTypeEnum locationType;

    /**
     * Rental Location opening date time in case when location is temporarily closed
     */
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm")
    private LocalDateTime openingDateTime;

    /**
     * Rental Location Business Hours when the location is open for business
     */
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "business_hours", joinColumns = @JoinColumn(name = "id_rental_location"))
    @Builder.Default
    @ToString.Exclude
    private Set<BusinessHours> businessHours = new HashSet<>();

    /**
     * Rental Location address
     */
    @NotNull(message = "Company address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rental_location_address", nullable = false)
    private Address address;

    /**
     * The company that owns a given rental location
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company", nullable = false)
    @NotNull(message = "HQ cannot be null")
    private Company hq;

    /**
     * Employees that work at a given rental location
     */
    @OneToMany(mappedBy = "workplace", cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<User> employees = new HashSet<>();

    /**
     * Cars that can be rented from the given rental location
     */
    @OneToMany(mappedBy = "rentalLocation", cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Car> cars = new HashSet<>();

    /**
     * @param car a car that needs to be added so that it can be rented from a given location
     */
    public void addCar(@NotNull Car car) {
        cars.add(car);
    }

    /**
     * @param car a car that needs to be removed so that it can no longer be rented from a given location
     */
    public void removeCar(@NotNull Car car) {
        cars.remove(car);
    }

    /**
     * @return Rental Location opening date time in case when location is temporarily closed
     */
    @Override
    public LocalDateTime getOpeningDateTime() {
        return openingDateTime;
    }

    /**
     * @param openingDateTime Rental Location opening date time in case when location is temporarily closed
     */
    @Override
    public void setOpeningDateTime(LocalDateTime openingDateTime) {
        this.openingDateTime = openingDateTime;
    }

    /**
     * @return Rental Location Business Hours when the location is open for business
     */
    @Override
    public Set<BusinessHours> getBusinessHours() {
        return businessHours;
    }

    /**
     * @param businessHours Rental Location Business Hours when the location is open for business
     */
    @Override
    public void setBusinessHours(Set<BusinessHours> businessHours) {
        this.businessHours = businessHours;
    }
}


