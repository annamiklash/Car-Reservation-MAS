package pjatk.mas.MAS.controller;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pjatk.mas.MAS.model.DateRange;
import pjatk.mas.MAS.model.dto.RentalLocation;
import pjatk.mas.MAS.service.RentalLocationService;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("mas/location")
public class RentalLocationController {

    private final RentalLocationService rentalLocationService;

    @GetMapping("/all")
    public String getAllLocations(Model model, HttpServletRequest request) {
        final ImmutableList<RentalLocation> locations = rentalLocationService.findAllLocations();

        model.addAttribute("locations", locations);

        return "locationList";
    }

    @GetMapping
    public String chooseDates(@RequestParam Long id, Model model, HttpServletRequest request) {
        final RentalLocation location = rentalLocationService.findLocationById(id);
        request.getSession().setAttribute("location", location);

        model.addAttribute("dateRange", new DateRange());

        return "pickDatesForm";
    }
}
