package pjatk.mas.MAS.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pjatk.mas.MAS.constants.RegexConstants;
import pjatk.mas.MAS.interfaces.user.Admin;
import pjatk.mas.MAS.interfaces.user.Customer;
import pjatk.mas.MAS.interfaces.user.Employee;
import pjatk.mas.MAS.interfaces.user.VIP;
import pjatk.mas.MAS.model.enums.AdminAccessLevel;
import pjatk.mas.MAS.model.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "users")
public class User implements Serializable, Employee, Customer, VIP, Admin {

    public static Integer MIN_RESERVATION_CUSTOMER_YEAR = 21;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotNull
    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = RegexConstants.FIRST_NAME_REGEX)
    @Length(max = 30)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = RegexConstants.LAST_NAME_REGEX)
    @Length(max = 30)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = RegexConstants.EMAIL_REGEX, message = "Please provide a valid email address")
    @Column
    private String email;

    @NotNull(message = "Birthdate cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    @Past
    private LocalDate birthdate;

    @NotNull(message = "Phone number cannot be null")
    @Digits(integer = 9, fraction = 0)
    private BigInteger phoneNumber;

    @NotNull(message = "Customer address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_address", nullable = false)
    private Address address;

    @Length(min = 5, max = 15, message = "Username should be at least 5 chars long and maximum 15 chars long")
    @Column(unique = true)
    private String username;

    @Min(0)
    @Max(50)
    private Integer discount;

    @Enumerated(EnumType.STRING)
    @Column
    private AdminAccessLevel adminAccessLevel;

    @Length(min = 10, max = 10)
    private String pesel;

    @ElementCollection
    @CollectionTable(name = "user_type", joinColumns = @JoinColumn(name = "id_user"))
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<UserType> userType = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonIgnore
    private Set<Reservation> customerReservations = new HashSet<>();


    public void addReservation(@NotNull Reservation reservation) {
        customerReservations.add(reservation);
    }

    public void removeReservation(@NotNull Reservation reservation) {
        customerReservations.remove(reservation);
    }

    public Integer getAge() {
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

}
