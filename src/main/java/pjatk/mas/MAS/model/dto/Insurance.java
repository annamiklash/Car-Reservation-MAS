package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigInteger;

@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "INSURANCE_TYPE")
@Entity(name = "insurance")
public class Insurance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insurance")
    private Long id;

    @NotBlank(message = "Insurance name cannot be null or empty")
    private String name;

    @NotBlank(message = "Insurance description cannot be null or empty")
    private String description;

    @NotNull(message = "Insurance cost cannot be null")
    @Min(value = 1, message = "Insurance cost cannot be less than 1")
    private BigInteger costPerDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation owner;

}
