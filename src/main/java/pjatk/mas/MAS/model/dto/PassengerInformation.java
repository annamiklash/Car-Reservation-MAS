package pjatk.mas.MAS.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PassengerInformation {

    private String firstName;

    private String lastName;

    private String insuranceNumber;
}
