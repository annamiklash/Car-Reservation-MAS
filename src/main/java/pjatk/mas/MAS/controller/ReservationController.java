package pjatk.mas.MAS.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.service.ReservationService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("mas/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public String showReservationConfirmation(@ModelAttribute Reservation reservation, Model model, HttpServletRequest request) {

        final Reservation sessionReservation = (Reservation) request.getSession().getAttribute("reservation");

        sessionReservation.setTotalPeopleNumber(reservation.getTotalPeopleNumber());
        reservationService.saveReservation(sessionReservation);

        request.getSession().setAttribute("reservation", sessionReservation);
        model.addAttribute("reservation", sessionReservation);

        request.removeAttribute("reservation");
        request.removeAttribute("location");

        return "reservationConfirmation";

    }
}
