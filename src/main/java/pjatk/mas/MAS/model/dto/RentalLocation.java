package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pjatk.mas.MAS.constants.RegexConstants;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "rental_location")
public class RentalLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rental_location")
    Long id;

    @NotBlank(message = "Company name cannot be null or empty")
    @Pattern(regexp = RegexConstants.NAME_REGEX)
    @Column(name = "rental_location_name")
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
    @JoinColumn(name = "id_rental_location_address", nullable = false)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_company", nullable = false)
    @NotNull(message = "HQ cannot be null")
    Company hq;
}
