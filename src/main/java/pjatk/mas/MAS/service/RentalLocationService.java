package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.BusinessHours;
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.model.enums.LocationTypeEnum;
import pjatk.mas.MAS.repository.RentalLocationRepository;
import pjatk.mas.MAS.validator.BusinessHoursValidator;
import pjatk.mas.MAS.validator.model.Error;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class RentalLocationService {

    private final RentalLocationRepository rentalLocationRepository;

    public ImmutableList<RentalLocation> findAll() {
        final List<RentalLocation> all = rentalLocationRepository.findAll();
        return ImmutableList.copyOf(all);
    }

    public RentalLocation findById(long id) {
        final Optional<RentalLocation> optionalRentalLocation = rentalLocationRepository.findById(id);
        if (optionalRentalLocation.isEmpty()) {
            throw new RuntimeException("No rental location with id " + id);
        }
        return optionalRentalLocation.get();
    }

    public void saveLocation(RentalLocation rentalLocation) {
        rentalLocationRepository.save(rentalLocation);
    }

    public void temporarilyCloseLocation(RentalLocation location, LocalDateTime openingDateTime) {
        if (location.getBusinessHours() == null || location.getLocationType() == LocationTypeEnum.CLOSED) {
            throw new RuntimeException("Cannot close already closed location");
        }
        location.setBusinessHours(null);
        location.setOpeningDateTime(openingDateTime);
        location.setLocationType(LocationTypeEnum.CLOSED);

        saveLocation(location);
    }

    public void openLocation(RentalLocation location, Set<BusinessHours> businessHours) {
        if (location.getOpeningDateTime() == null || location.getLocationType() == LocationTypeEnum.OPEN) {
            throw new RuntimeException("Cannot open already open location");
        }

        final List<Error> errors = BusinessHoursValidator.validateBusinessHoursSet(businessHours);
        if (errors.size() > 0) {
            throw new RuntimeException(errors.toString());
        }

        location.setOpeningDateTime(null);
        location.setBusinessHours(businessHours);
        location.setLocationType(LocationTypeEnum.OPEN);

        saveLocation(location);
    }
}
