package pjatk.mas.MAS.controller;


import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pjatk.mas.MAS.model.DateRange;
import pjatk.mas.MAS.model.containers.InsuranceListContainer;
import pjatk.mas.MAS.model.dto.*;
import pjatk.mas.MAS.service.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@AllArgsConstructor
@Controller
//@SessionAttributes({"reservation", "location"})
@RequestMapping("/test")
public class TestController {

    private final RentalLocationService rentalLocationService;
    private final ReservationService reservationService;
    private final CarService carService;
    private final InsuranceService insuranceService;
    private final UserService userService;

    @GetMapping
    public String getAllLocations(Model model) {
        final ImmutableList<RentalLocation> locations = rentalLocationService.findAll();

        model.addAttribute("locations", locations);

        return "index";
    }

    @GetMapping("/location")
    public String chooseDates(@RequestParam Long id, Model model, HttpServletRequest request) {
        log.info("getting next page for id {}", id);

        final RentalLocation location = rentalLocationService.findById(id);
        request.getSession().setAttribute("location", location);

        model.addAttribute("dateRange", new DateRange());

        return "result";
    }


    @PostMapping("/cars")
    public String processCarSelection(HttpServletRequest request, @ModelAttribute DateRange dateRange, Model model) {

        final Reservation reservation = new Reservation();
        reservation.setId(UUID.randomUUID());
        reservation.setDateFrom(LocalDate.parse(dateRange.getFrom()));
        reservation.setDateTo(LocalDate.parse(dateRange.getTo()));

        request.getSession().setAttribute("reservation", reservation);

        final RentalLocation location = (RentalLocation) request.getSession().getAttribute("location");
        System.out.println(location);

        final ImmutableList<Car> cars = carService.findAllAvailableCarsByRentalLocationId(location.getId(), reservation.getDateFrom(), reservation.getDateTo());

        System.out.println(cars);
        model.addAttribute("cars", cars);

        return "carList";
    }

    @GetMapping("/car")
    public String getCarDetails(@RequestParam Long id, Model model, HttpServletRequest request) {
        System.out.println(id);
        final Car car = carService.findById(id);

        final Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        System.out.println("DATE FROM " + reservation.getDateFrom());

        reservation.setCar(car);
        request.getSession().setAttribute("reservation", reservation);

        model.addAttribute("car", car);

        return "carDetails";
    }

    @GetMapping("/insurances")
    public String findAllInsurances(Model model, HttpServletRequest request) {
        final ImmutableList<Insurance> insuranceList = insuranceService.findAll();

        final Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");

        model.addAttribute("car", reservation.getCar());
        model.addAttribute("insurances", insuranceList);
        model.addAttribute("insuranceIdList", new ArrayList<>());

        return "insuranceList";
    }

    @GetMapping( "/reservation/{insIds}")
    public String showReservationSummaryForm(@PathVariable List<Integer> insIds, Model model, HttpServletRequest request) {

        final List<Insurance> selectedInsurances = insIds.stream()
                .map(insuranceId -> insuranceService.findById(Long.valueOf(insuranceId)))
                .collect(Collectors.toList());

        final InsuranceListContainer insurances = new InsuranceListContainer();
        insurances.setInsuranceList(selectedInsurances);

        final User customer = userService.findCustomerById(1L);

        final Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        reservation.setUser(customer);
        reservation.setInsurances(new HashSet<>(insurances.getInsuranceList()));

        request.getSession().setAttribute("reservation", reservation);

        final long days = DAYS.between(reservation.getDateFrom(), reservation.getDateTo());
        final Integer capacity = reservation.getCar().getPassengerCapacity();

        model.addAttribute("reservation", reservation);
        model.addAttribute("days", days);
        model.addAttribute("maxPassengers", capacity);

        return "reservationSummary";
    }

    //TODO: refactor arguments!!
    @PostMapping("/reservation")
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
