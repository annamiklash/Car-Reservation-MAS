package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Car;
import pjatk.mas.MAS.repository.CarRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CarService {

    private CarRepository carRepository;

    public ImmutableList<Car> findAll() {
        final List<Car> carList = carRepository.findAll();
        return ImmutableList.copyOf(carList);
    }

    public void saveCar(Car car) {
        carRepository.save(car);
    }

}
