package pjatk.mas.MAS.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import pjatk.mas.MAS.model.enums.CarFeatureEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Feature of a car that is a part of it
 */
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "car_feature")
public class CarFeature {

    /**
     * Unique ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_car_feature")
    private Long id;

    /**
     * Name of the car feature
     */
    @NotNull(message = "Car feature cannot be null")
    @Enumerated(EnumType.STRING)
    @Column
    private CarFeatureEnum carFeatureEnum;

    /**
     * Description/value of a specific car feature
     */
    @NotBlank(message = "Feature value cannot be null or empty")
    private String value;

    /**
     * A car that the car feature belongs to
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_owner", nullable = false, updatable = false)
    private Car owner;


}
