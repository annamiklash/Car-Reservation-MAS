package pjatk.mas.MAS.controller;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.service.ReservationService;

@Slf4j
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImmutableList<Reservation>> findAll() {
        log.info("GET ALL RESERVATIONS");
        return ResponseEntity.ok(reservationService.findAll());
    }
}
