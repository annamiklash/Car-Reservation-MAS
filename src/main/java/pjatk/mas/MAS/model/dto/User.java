package pjatk.mas.MAS.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pjatk.mas.MAS.constants.RegexConstants;
import pjatk.mas.MAS.model.dto.interfaces.user.Admin;
import pjatk.mas.MAS.model.dto.interfaces.user.Customer;
import pjatk.mas.MAS.model.dto.interfaces.user.Employee;
import pjatk.mas.MAS.model.dto.interfaces.user.VIP;
import pjatk.mas.MAS.model.enums.AdminAccessLevel;
import pjatk.mas.MAS.model.enums.UserTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user of the app
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "users")
public class User implements Serializable, Employee, Customer, VIP, Admin {

    /**
     * Minimum required age for a user to reserve a car
     */
    public static Integer MIN_RESERVATION_CUSTOMER_YEAR = 21;

    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    /**
     * User first name
     */
    @NotNull
    @NotBlank(message = "First name is mandatory")
    @Pattern(regexp = RegexConstants.FIRST_NAME_REGEX)
    @Length(max = 30)
    @Column(name = "first_name")
    private String firstName;

    /**
     * User last name
     */
    @NotNull
    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = RegexConstants.LAST_NAME_REGEX)
    @Length(max = 30)
    @Column(name = "last_name")
    private String lastName;

    /**
     * User email
     */
    @NotNull
    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = RegexConstants.EMAIL_REGEX, message = "Please provide a valid email address")
    @Column
    private String email;

    /**
     * User birthdate
     */
    @NotNull(message = "Birthdate cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    @Past
    private LocalDate birthdate;

    /**
     * User phone number
     */
    @NotNull(message = "Phone number cannot be null")
    @Digits(integer = 9, fraction = 0)
    private BigInteger phoneNumber;

    /**
     * User address
     */
    @NotNull(message = "Customer address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user_address", nullable = false)
    private Address address;

    /**
     * Username in case when user is a customer
     */
    @Length(min = 5, max = 15, message = "Username should be at least 5 chars long and maximum 15 chars long")
    @Column(unique = true)
    private String username;

    /**
     * Discount in case when user is a VIP customer
     */
    @Min(0)
    @Max(50)
    private Integer discount;

    /**
     * Access level of an Admin user
     */
    @Enumerated(EnumType.STRING)
    @Column
    private AdminAccessLevel adminAccessLevel;

    /**
     * Pesel number of a user Employee
     */
    @Length(min = 10, max = 10)
    @Column(unique = true)
    private String pesel;

    /**
     * Rental location where the user that is an Employee works at
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rental_location", nullable = true)
    private RentalLocation workplace;

    /**
     * Types that the User can be
     */
    @ElementCollection
    @CollectionTable(name = "user_type", joinColumns = @JoinColumn(name = "id_user"))
    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull
    private Set<UserTypeEnum> userType = new HashSet<>();

    /**
     * Reservations that the user of type Customer made
     */
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

    /**
     * @return age of a user
     */
    public Integer getAge() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }

    /**
     * checks if a customer is old enough to make a reservation
     *
     * @return flag whether user is old enough to reserve a car
     */
    public Boolean isOldEnough() {
        return MIN_RESERVATION_CUSTOMER_YEAR - this.getAge() <= 0;
    }

}
