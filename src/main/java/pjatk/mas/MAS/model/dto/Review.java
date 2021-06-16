package pjatk.mas.MAS.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(schema = "mas", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_car", "id_customer"})
})
@Entity(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long id;

    @NotBlank
    private String title;
    
    @NotBlank
    private String content;

    @NotNull(message = "Star rating cannot be null")
    @Min(0)
    @Max(5)
    private Integer stars;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_car", nullable = false)
    @NotNull(message = "Car cannot be null")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    @NotNull(message = "Customer cannot be null")
    private User user;


}
