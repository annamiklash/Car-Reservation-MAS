package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Car;
import pjatk.mas.MAS.model.dto.ElectricCarImpl;
import pjatk.mas.MAS.model.dto.MechanicsShop;
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.model.enums.CarAvailabilityEnum;
import pjatk.mas.MAS.model.exceptions.CustomErrorException;
import pjatk.mas.MAS.repository.CarRepository;
import pjatk.mas.MAS.repository.ElectricCarRepository;
import pjatk.mas.MAS.repository.HybridCarRepository;
import pjatk.mas.MAS.repository.ICECarRepository;
import pjatk.mas.MAS.validator.model.Error;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Business logic layer for entity Car
 */
@Service
@AllArgsConstructor
@Slf4j
public class CarService {

    private final CarRepository carRepository;
    private final MechanicsShopService mechanicsShopService;
    private final RentalLocationService rentalLocationService;

    private final ICECarRepository iceCarRepository;
    private final HybridCarRepository hybridCarRepository;
    private final ElectricCarRepository electricCarRepository;


    /**
     * @return list of all cars stored in DB
     */
    public ImmutableList<Car> findAll() {
        final List<Car> carList = carRepository.findAll();
        return ImmutableList.copyOf(carList);
    }

    /**
     * @param car a car to be save in db
     */
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    /**
     * @param id car ID
     * @return car with Id specified in param
     */
    public Car findById(long id) {
        final Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new CustomErrorException(Error.builder()
                    .field("id")
                    .value(String.valueOf(id))
                    .description("No car with id " + id)
                    .build());
        }
        return optionalCar.get();
    }

    /**
     * @return all electric cars stored in db
     */
    public ImmutableList<ElectricCarImpl> findAllElectric() {
        final List<ElectricCarImpl> all = electricCarRepository.findAllElectric();
        return ImmutableList.copyOf(all);
    }

    /**
     * find all available vehicles
     *
     * @param id   location id
     * @param from pick up date
     * @param to   drop off date
     * @return list of available cars for a given location id and between given dates
     */
    public ImmutableList<Car> findAllAvailableCarsByRentalLocationId(Long id, LocalDate from, LocalDate to) {
        List<Car> carsList = carRepository.findAllByRentalLocation_Id(id, from, to);
        carsList = carsList.stream()
                .filter(car -> car.getAvailability() == CarAvailabilityEnum.AVAILABLE)
                .collect(Collectors.toList());

        return ImmutableList.copyOf(carsList);
    }

    /**
     * @param id mechanics shop id
     * @return list of cars located at mechanics shop
     */
    public ImmutableList<Car> findAllCarsAtMechanicsShopByShopId(Long id) {
        final List<Car> carsList = carRepository.findAllByMechanicsShop_Id(id);
        return ImmutableList.copyOf(carsList);
    }

    /**
     * @param carId car ID
     * @param mechanicsShopId mechanics shop ID
     */
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

    /**
     * @param carId car ID
     * @param rentalLocId rental location ID
     */
    public void moveCarToRentalLocation(long carId, long rentalLocId) {
        final Car car = findById(carId);
        final RentalLocation rentalLocation = rentalLocationService.findLocationById(rentalLocId);

        rentalLocation.addCar(car);

        car.setMechanicsShop(null);
        car.setAvailability(CarAvailabilityEnum.AVAILABLE);
        car.setRentalLocation(rentalLocation);

        saveCar(car);
        rentalLocationService.saveLocation(rentalLocation);
    }

}
