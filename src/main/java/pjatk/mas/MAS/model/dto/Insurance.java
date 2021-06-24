package pjatk.mas.MAS.model.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Class represents Insurance that can be chosen for a given car reservation
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "insurance")
@Table(name = "insurance")
public class Insurance {

    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insurance")
    private Long id;

    /**
     * Insurance name
     */
    @NotNull
    @NotBlank(message = "Insurance name cannot be null or empty")
    private String name;

    /**
     * Insurance description
     */
    @NotNull
    @NotBlank(message = "Insurance description cannot be null or empty")
    private String description;

    /**
     * Insurance cost per day
     */
    @NotNull(message = "Insurance cost cannot be null")
    @Min(value = 1, message = "Insurance cost cannot be less than 1")
    private Integer costPerDay;

    /**
     * Reservations that used a given insurance
     */
    @ManyToMany(mappedBy = "insurances", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Reservation> reservations = new HashSet<>();


}
