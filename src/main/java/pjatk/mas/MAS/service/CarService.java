package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Car;
import pjatk.mas.MAS.model.dto.MechanicsShop;
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.model.enums.CarAvailabilityEnum;
import pjatk.mas.MAS.repository.CarRepository;
import pjatk.mas.MAS.validator.CarValidator;
import pjatk.mas.MAS.validator.model.Error;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CarService {

    private final CarRepository carRepository;
    private final MechanicsShopService mechanicsShopService;
    private final RentalLocationService rentalLocationService;

    public ImmutableList<Car> findAll() {
        final List<Car> carList = carRepository.findAll();
        return ImmutableList.copyOf(carList);
    }

    public ImmutableList<Car> findAllAvailableForReservationByLocationId(long id, String from, String to) {
        final List<Car> cars = carRepository.findAllByRentalLocation_Id(id, LocalDate.parse(from), LocalDate.parse(to));
        return ImmutableList.copyOf(cars);
    }

    public void saveCar(Car car) {
        final List<Error> errorList = CarValidator.validateCarToCreate(car);

        if (errorList.size() > 0) {
            throw new RuntimeException(errorList.toString());
        }

        carRepository.save(car);
    }


    public Car findById(long id) {
        final Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new RuntimeException("No car with id " + id);
        }
        return optionalCar.get();
    }


//    public ImmutableList<ElectricCarImpl> findAllElectric() {
//        final List<ElectricCarImpl> all = electricCarRepository.findAllElectricCars();
//        return ImmutableList.copyOf(all);
//    }

    public ImmutableList<Car> findAllAvailableCarsByRentalLocationId(Long id, LocalDate from, LocalDate to) {
        List<Car> carsList = carRepository.findAllByRentalLocation_Id(id, from, to);
        carsList = carsList.stream()
                .filter(car -> car.getAvailability() == CarAvailabilityEnum.AVAILABLE)
                .collect(Collectors.toList());

        return ImmutableList.copyOf(carsList);
    }

    public ImmutableList<Car> findAllCarsAtMechanicsShopByShopId(Long id) {
        final List<Car> carsList = carRepository.findAllByMechanicsShop_Id(id);
        return ImmutableList.copyOf(carsList);
    }

    public void moveCarToMechanicsShop(long carId, long mechanicsShopId) {
        final Car car = findById(carId);
        final MechanicsShop mechanicsShop = mechanicsShopService.findById(mechanicsShopId);

        mechanicsShop.addCar(car);

        car.setRentalLocation(null);
        car.setAvailability(CarAvailabilityEnum.NOT_AVAILABLE);
        car.setMechanicsShop(mechanicsShop);

        saveCar(car);
        mechanicsShopService.save(mechanicsShop);
    }

    public void moveCarToRentalLocation(long carId, long rentalLocId) {
        final Car car = findById(carId);
        final RentalLocation rentalLocation = rentalLocationService.findById(rentalLocId);

        rentalLocation.addCar(car);

        car.setMechanicsShop(null);
        car.setAvailability(CarAvailabilityEnum.AVAILABLE);
        car.setRentalLocation(rentalLocation);

        saveCar(car);
        rentalLocationService.saveLocation(rentalLocation);
    }

    public Integer getYearsSinceManufactureYear(long carId) {
        final Car car = findById(carId);
        return LocalDate.now().getYear() - car.getManufactureYear();
    }
}
