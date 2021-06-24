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


    /**
     * Business logic layer for entity RentalLocation
     */
    private final RentalLocationService rentalLocationService;

    /**
     * @param model   interface that defines a holder for model attribute
     * @param request http request that stores session attributes
     * @return html with list of all available locations
     */
    @GetMapping("/all")
    public String getAllLocations(Model model, HttpServletRequest request) {
        final ImmutableList<RentalLocation> locations = rentalLocationService.findAllLocations();

        model.addAttribute("locations", locations);

        return "locationList";
    }

    /**
     * @param id      ID of a selected by customer location to choose  a car from for given dates
     * @param model   interface that defines a holder for model attribute
     * @param request http request that stores session attributes
     * @return html with from for entering pick up and drop off dates
     */
    @GetMapping
    public String chooseDates(@RequestParam Long id, Model model, HttpServletRequest request) {
        final RentalLocation location = rentalLocationService.findLocationById(id);
        request.getSession().setAttribute("location", location);

        model.addAttribute("dateRange", new DateRange());

        return "pickDatesForm";
    }
}
