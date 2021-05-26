package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Vehicle;
import pjatk.mas.MAS.repository.VehicleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public ImmutableList<Vehicle> findAll() {
        final List<Vehicle> vehicles = vehicleRepository.findAll();
        return ImmutableList.copyOf(vehicles);
    }

    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public int countAllVehicles() {
        return Math.toIntExact(vehicleRepository.count());
    }

    public Vehicle findVehicleById(Long id) {
        final Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleById(id);
        if (optionalVehicle.isPresent()) {
            return optionalVehicle.get();
        }
        throw new NoSuchElementException("There is no vehicle with id " + id);
    }

}
