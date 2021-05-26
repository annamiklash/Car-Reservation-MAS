package pjatk.mas.MAS.model.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pjatk.mas.MAS.constants.RegexConstants;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
@Table(name = "customer")
@Entity(name = "customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    Long id;

    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = RegexConstants.FIRST_NAME_REGEX)
    @Column(name = "first_name")
    String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = RegexConstants.LAST_NAME_REGEX)
    @Column(name = "last_name")
    String lastName;

    @NotBlank(message = "Username is mandatory")
    @Length(min = 5, max = 15, message = "Username should be at least 5 chars long and maximum 15 chars long")
    @Column
    String username;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please provide a valid email address")
    @Column
    String email;

    @NotNull(message = "Birthdate cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    @Past
    LocalDate birthdate;

    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    @Digits(integer = 9, fraction = 0)
    BigInteger phoneNumber;

    @NotNull(message = "Customer address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_customer_address", nullable = false)
    Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    Set<Reservation> customerReservations = new HashSet<>();

    public void addReservation(@NotNull Reservation reservation) {
        customerReservations.add(reservation);
    }

    public void removeReservation(@NotNull Reservation reservation) {
        customerReservations.remove(reservation);
    }
}
