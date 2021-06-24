package pjatk.mas.MAS.model.dto.interfaces.location;

import java.time.LocalDateTime;

/**
 * Interface containing getters and setters for fields that are required to have by an object of class RentalLocation that is a Temporarily Closed
 */
public interface TemporarilyClosedLocation {

    LocalDateTime getOpeningDateTime();

    void setOpeningDateTime(LocalDateTime openingDateTime);

}
