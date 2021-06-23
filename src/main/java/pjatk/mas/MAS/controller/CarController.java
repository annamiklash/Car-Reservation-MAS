package pjatk.mas.MAS.controller;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pjatk.mas.MAS.model.DateRange;
import pjatk.mas.MAS.model.dto.Car;
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.model.dto.User;
import pjatk.mas.MAS.service.CarService;
import pjatk.mas.MAS.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("mas/car")
public class CarController {

    private final CarService carService;
    private final UserService userService;


    @PostMapping("/all")
    public String getAllAvailableCarsAtLocationForDates(HttpServletRequest request, @ModelAttribute DateRange dateRange, Model model) {

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

    @GetMapping
    public String getCarDetails(@RequestParam Long id, Model model, HttpServletRequest request) {
        final Car car = carService.findById(id);

        final Reservation reservation = (Reservation) request.getSession().getAttribute("reservation");
        System.out.println("DATE FROM " + reservation.getDateFrom());

        final User customer = userService.findCustomerById(1L);
        reservation.setUser(customer);

        reservation.setCar(car);
        request.getSession().setAttribute("reservation", reservation);
        model.addAttribute("car", car);

        return "carDetails";
    }
}
