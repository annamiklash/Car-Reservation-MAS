package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorValue("PERSONAL_ACCIDENT")
@Entity
public class PersonalAccidentInsurance extends Insurance {

    @ElementCollection
    @CollectionTable(name = "passenger_info", joinColumns = @JoinColumn(name = "id_insurance"))
    @Builder.Default
    Set<PassengerInformation> passengers = new HashSet<>();
}
