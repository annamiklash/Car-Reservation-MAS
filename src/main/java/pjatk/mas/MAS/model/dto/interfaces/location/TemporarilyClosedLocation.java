package pjatk.mas.MAS.model.dto.interfaces.location;

import java.time.LocalDateTime;

public interface TemporarilyClosedLocation {

    LocalDateTime getOpeningDateTime();

    void setOpeningDateTime(LocalDateTime openingDateTime);

}
