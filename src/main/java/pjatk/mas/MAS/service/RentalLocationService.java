package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.BusinessHours;
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.model.enums.LocationTypeEnum;
import pjatk.mas.MAS.model.exceptions.CustomErrorException;
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

    public ImmutableList<RentalLocation> findAllLocations() {
        final List<RentalLocation> all = rentalLocationRepository.findAll();
        return ImmutableList.copyOf(all);
    }

    public RentalLocation findLocationById(long id) {
        final Optional<RentalLocation> optionalRentalLocation = rentalLocationRepository.findById(id);
        if (optionalRentalLocation.isEmpty()) {
            throw new CustomErrorException(Error.builder()
                    .field("id")
                    .field(String.valueOf(id))
                    .description("No rental location with id " + id)
                    .build());
        }
        return optionalRentalLocation.get();
    }

    public void saveLocation(RentalLocation rentalLocation) {
        rentalLocationRepository.save(rentalLocation);
    }

    public void temporarilyCloseLocation(RentalLocation location, LocalDateTime openingDateTime) {
        if (location.getBusinessHours() == null || location.getLocationType() == LocationTypeEnum.CLOSED) {
            throw new CustomErrorException(Error.builder()
                    .field("businessHours or locationTypeEnum")
                    .value("null or LocationTypeEnum.CLOSED")
                    .description("Cannot close already closed location")
                    .build());
        }
        location.setBusinessHours(null);
        location.setOpeningDateTime(openingDateTime);
        location.setLocationType(LocationTypeEnum.CLOSED);

        saveLocation(location);
    }

    public void openLocation(RentalLocation location, Set<BusinessHours> businessHours) {
        if (location.getOpeningDateTime() == null || location.getLocationType() == LocationTypeEnum.OPEN) {
            throw new CustomErrorException(Error.builder()
                    .field("openingDateTime or locationTypeEnum")
                    .value("null or LocationTypeEnum.OPEN")
                    .description("Cannot open already open location")
                    .build());
        }

        final List<Error> errors = BusinessHoursValidator.validateBusinessHoursSet(businessHours);
        if (errors.size() > 0) {
            throw new CustomErrorException(errors);
        }

        location.setOpeningDateTime(null);
        location.setBusinessHours(businessHours);
        location.setLocationType(LocationTypeEnum.OPEN);

        saveLocation(location);
    }
}
