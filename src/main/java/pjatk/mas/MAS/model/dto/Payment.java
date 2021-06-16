package pjatk.mas.MAS.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment")
    private Long id;

    @NotNull
    private Boolean isPayed;

    @NotNull
    @Min(0)
    private Integer amountPayed;
}
