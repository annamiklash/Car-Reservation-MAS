package pjatk.mas.MAS.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pjatk.mas.MAS.model.Dimensions;
import pjatk.mas.MAS.model.enums.YachtAmenitiesEnum;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
//@TypeDef(typeClass = EnumSetType.class, name = "YachtAmenitiesEnum")
@Entity(name = "yacht")
public class Yacht extends Vehicle {

    @ElementCollection
    @Column(name = "amenity", nullable = false)
    @Enumerated(EnumType.STRING)
    @LazyCollection(LazyCollectionOption.FALSE)
    Set<YachtAmenitiesEnum> amenities;

    @Embedded
    Dimensions yachtDimensions;
}
