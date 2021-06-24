package pjatk.mas.MAS.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pjatk.mas.MAS.constants.RegexConstants;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing the company
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "company")
public class Company {

    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long id;

    /**
     * Company name
     */
    @NotBlank(message = "Company name cannot be null or empty")
    @Pattern(regexp = RegexConstants.NAME_REGEX)
    @Column(name = "company_name")
    private String name;

    /**
     * Company email address
     */
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please provide a valid email address")
    @Column
    private String email;

    /**
     * Company phone number
     */
    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    @Digits(integer = 9, fraction = 0)
    private BigInteger phoneNumber;

    /**
     * Company address
     */
    @NotNull(message = "Company address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company_address", nullable = false)
    private Address address;

    /**
     * Rental Locations that are owned by a Company
     */
    @OneToMany(mappedBy = "hq", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonIgnore
    private Set<RentalLocation> locations = new HashSet<>();


    /**
     * Method to add a rental location to the list of company rental locations
     *
     * @param rentalLocation rental location to be added to the list of locations owned by the company
     */
    public void addLocation(RentalLocation rentalLocation) {
        locations.add(rentalLocation);
        rentalLocation.setHq(this);
    }

    /**
     * Method to remove a  rental location to the list of company rental locations
     *
     * @param rentalLocation rental location to be removed from the list of locations owned by the company
     */
    public void removeLocation(RentalLocation rentalLocation) {
        locations.remove(rentalLocation);
        rentalLocation.setHq(null);
    }

}
