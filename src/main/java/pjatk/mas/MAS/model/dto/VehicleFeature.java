package pjatk.mas.MAS.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "vehicle_feature")
@Entity(name = "vehicle_feature")
public class VehicleFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle_feature")
    Long id;

    @NotBlank(message = "Feature value cannot be null or empty")
    private String value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_owner", nullable = false, updatable = false)
    private Vehicle owner;


}
