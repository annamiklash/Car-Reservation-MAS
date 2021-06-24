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

    /**
     * Business logic layer for entity Reservation
     */
    private final ReservationService reservationService;

    /**
     * @param reservation reservation object that will be created once customer confirms it
     * @param model       interface that defines a holder for model attribute
     * @param request     http request that stores session attributes
     * @return html with success message and reservation id
     */
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
