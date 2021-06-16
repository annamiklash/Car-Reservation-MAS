package pjatk.mas.MAS.model.dto;


import lombok.*;
import pjatk.mas.MAS.constants.RegexConstants;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "mechanics_shop")
public class MechanicsShop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mechanics_shop")
    private Long id;

    @NotBlank(message = "Company name cannot be null or empty")
    @Pattern(regexp = RegexConstants.NAME_REGEX)
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Please provide a valid email address")
    @Column
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @Column(name = "phone_number")
    @Digits(integer = 9, fraction = 0)
    private BigInteger phoneNumber;

    @NotNull(message = "Mechanics shop address cannot be null")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_mechanics_shop_address", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "mechanicsShop", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Car> cars = new HashSet<>();

    public void addCar(@NotNull Car car) {
        cars.add(car);
    }

    public void removeCar(@NotNull Car car) {
        cars.remove(car);
    }
}
