package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.BusinessHours;
import pjatk.mas.MAS.model.dto.OpenLocation;
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.repository.RentalLocationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RentalLocationService {

    private final RentalLocationRepository rentalLocationRepository;

    public ImmutableList<RentalLocation> findAll() {
        final List<RentalLocation> all = rentalLocationRepository.findAll();
        return ImmutableList.copyOf(all);
    }

//    public ImmutableList<RentalLocation> findAllOpen() {
//        final List<RentalLocation> all = rentalLocationRepository.findAllOpen();
//        return ImmutableList.copyOf(all);
//    }
//
//    public ImmutableList<RentalLocation> findAllClosed() {
//        final List<RentalLocation> all = rentalLocationRepository.findAllClosed();
//        return ImmutableList.copyOf(all);
//    }

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

    public void temporarilyCloseLocation(OpenLocation location, LocalDateTime openingDateTime) {
        if (location.getBusinessHours() == null) {
            throw new RuntimeException("Cannot close already closed location");
        }
        location.setBusinessHours(null);
    }

//    public void openLocation(TemporarilyClosedLocation location, Set<BusinessHours> businessHours) {
//        if (location.getOpeningDateTime() == null) {
//            throw new RuntimeException("Cannot open already open location");
//        }
//        if (!isBusinessHoursValid(businessHours)) {
//            throw new RuntimeException("Invalid business hours");
//        }
//        location.setOpeningDateTime(null);
//
//        final OpenLocation openLocation = OpenLocation.builder()
//                .id(location.getId())
//                .businessHours(businessHours)
//                .name(location.getName())
//                .email(location.getEmail())
//                .phoneNumber(location.getPhoneNumber())
//                .address(location.getAddress())
//                .hq(location.getHq())
//                .build();
//
//        rentalLocationRepository.delete(location);
//        rentalLocationRepository.save(openLocation);
//
//    }

    private boolean isBusinessHoursValid(Set<BusinessHours> businessHours) {
        if (!isCorrectSize(businessHours)) {
            return false;
        }
        return !containsDuplicates(businessHours);
    }

    private boolean containsDuplicates(Set<BusinessHours> businessHours) {
        return businessHours.stream()
                .map(BusinessHours::getDay)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))    // create a map {1=1, 2=1, 3=2, 4=2, 5=1, 7=1, 9=2}
                .entrySet()
                .stream()
                .anyMatch(m -> m.getValue() > 1);
    }

    private boolean isCorrectSize(Set<BusinessHours> businessHours) {
        return businessHours.size() >= 5 && businessHours.size() <= 7;
    }

}
