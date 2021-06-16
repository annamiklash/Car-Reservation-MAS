package pjatk.mas.MAS.controller;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pjatk.mas.MAS.model.dto.Car;
import pjatk.mas.MAS.service.CarService;

@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("api/car")
public class CarController {

    private final CarService carService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImmutableList<Car>> getAvailableCars(@RequestParam("location_id") long locationId, @RequestParam("from") String dateFrom, @RequestParam("to") String dateTo) {
        log.info("GET LOCATION");
        return ResponseEntity.ok(carService.findAllAvailableForReservationByLocationId(locationId, dateFrom, dateTo));
    }
}
