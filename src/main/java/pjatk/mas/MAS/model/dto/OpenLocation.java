package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;



@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OpenLocation {

    @ElementCollection
    @CollectionTable(name = "business_hours", joinColumns = @JoinColumn(name = "id_rental_location"))
    @Builder.Default
    private Set<BusinessHours> businessHours = new HashSet<>();


}
