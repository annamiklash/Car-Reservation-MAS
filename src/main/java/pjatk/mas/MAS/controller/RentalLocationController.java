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
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.service.RentalLocationService;

@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("api/location")
public class RentalLocationController {

    private final RentalLocationService rentalLocationService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImmutableList<RentalLocation>> findAll() {
        log.info("GET ALL LOCATIONS");
        return ResponseEntity.ok(rentalLocationService.findAll());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalLocation> getLocationById(@RequestParam("id") long locationId) {
        log.info("GET LOCATION");
        return ResponseEntity.ok(rentalLocationService.findById(locationId));
    }


}
