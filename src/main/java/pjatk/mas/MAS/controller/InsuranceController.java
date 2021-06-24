package pjatk.mas.MAS.controller;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pjatk.mas.MAS.model.containers.InsuranceListContainer;
import pjatk.mas.MAS.model.dto.Insurance;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.service.InsuranceService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("mas/insurance")
public class InsuranceController {

    /**
     * Business logic layer for entity Insurance
     */
    private final InsuranceService insuranceService;

    /**
     * @param model   interface that defines a holder for model attribute
     * @param request http request that stores session attributes
     * @return html with list of insurances to choose from
     */
    @GetMapping("/all")
    public String getAllInsurances(Model model, HttpServletRequest request) {
        final ImmutableList<Insurance> insuranceList = insuranceService.findAllInsurances();

        final Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        model.addAttribute("car", reservation.getCar());
        model.addAttribute("insurances", insuranceList);
        model.addAttribute("insuranceIdList", new ArrayList<>());

        return "insuranceList";
    }


    /**
     * @param insIds  IDs of selected by customer insurances
     * @param model   interface that defines a holder for model attribute
     * @param request http request that stores session attributes
     * @return html with summary of a reservation with information about car, customer, dates, amount of people and selected insurances
     */
    @GetMapping("{insIds}")
    public String showReservationSummaryForm(@PathVariable List<Integer> insIds, Model model, HttpServletRequest request) {

        final List<Insurance> selectedInsurances = insIds.stream()
                .map(insuranceId -> insuranceService.findById(Long.valueOf(insuranceId)))
                .collect(Collectors.toList());

        final InsuranceListContainer insurances = new InsuranceListContainer();
        insurances.setInsuranceList(selectedInsurances);

        final Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        reservation.setInsurances(new HashSet<>(insurances.getInsuranceList()));

        request.getSession().setAttribute("reservation", reservation);

        final long days = DAYS.between(reservation.getDateFrom(), reservation.getDateTo());
        final Integer capacity = reservation.getCar().getPassengerCapacity();

        model.addAttribute("reservation", reservation);
        model.addAttribute("days", days);
        model.addAttribute("maxPassengers", capacity);

        return "reservationSummary";
    }

}
