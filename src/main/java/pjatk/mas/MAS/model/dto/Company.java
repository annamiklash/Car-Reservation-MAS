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

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Long id;

    @NotBlank(message = "Company name cannot be null or empty")
    @Pattern(regexp = RegexConstants.NAME_REGEX)
    @Column(name = "company_name")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please provide a valid email address")
    @Column
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    @Digits(integer = 9, fraction = 0)
    private BigInteger phoneNumber;

    @NotNull(message = "Company address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company_address", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "hq", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonIgnore
    private Set<RentalLocation> locations = new HashSet<>();

    public void addLocation(RentalLocation rentalLocation) {
        locations.add(rentalLocation);
        rentalLocation.setHq(this);
    }

    public void removeLocation(RentalLocation rentalLocation) {
        locations.remove(rentalLocation);
        rentalLocation.setHq(null);
    }

}
