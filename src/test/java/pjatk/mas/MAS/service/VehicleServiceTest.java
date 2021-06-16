//package pjatk.mas.MAS.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import pjatk.mas.MAS.model.Dimensions;
//import pjatk.mas.MAS.model.dto.Car;
//import pjatk.mas.MAS.model.dto.Yacht;
//import pjatk.mas.MAS.model.enums.VehicleAvailabilityEnum;
//import pjatk.mas.MAS.model.enums.YachtAmenitiesEnum;
//
//import javax.transaction.Transactional;
//import java.util.Arrays;
//import java.util.HashSet;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class VehicleServiceTest {
//
//    @Autowired
//    private VehicleService vehicleService;
//
//    @Autowired
//    private CarService carService;
//
//    @Autowired
//    private YachtService yachtService;
//
//    Dimensions dimensions;
//    Car car1, car2;
//    Yacht yacht1;
//
//    @BeforeEach
//    public void initializeData() {
//        car1 = Car.builder()
//                .id(1000L)
//                .make("Toyota")
//                .model("Yaris")
//                .color(Arrays.asList("Black", "White").toArray(new String[0]))
//                .availability(VehicleAvailabilityEnum.AVAILABLE)
//                .dailyRentCost(50)
//                .registrationPlate("WY12345")
//                .horsePower(80)
//                .build();
//
//        car2 = Car.builder()
//                .id(1001L)
//                .make("Audi")
//                .model("RS7")
//                .color(Arrays.asList("Grey").toArray(new String[0]))
//                .availability(VehicleAvailabilityEnum.AVAILABLE)
//                .dailyRentCost(120)
//                .registrationPlate("WA12345")
//                .horsePower(250)
//                .build();
//
//        dimensions = Dimensions.builder()
//                .length(30)
//                .width(5)
//                .height(4)
//                .build();
//
//        yacht1 = Yacht.builder()
//                .id(1002L)
//                .yachtDimensions(dimensions)
//                .amenities(new HashSet<>(Arrays.asList(YachtAmenitiesEnum.WIFI, YachtAmenitiesEnum.SAUNA)))
//                .make("Yacht")
//                .color(Arrays.asList("White").toArray(new String[0]))
//                .availability(VehicleAvailabilityEnum.AVAILABLE)
//                .dailyRentCost(900)
//                .build();
//    }
//
//    @Test
//    public void testRequiredDependencies() {
//        assertNotNull(vehicleService);
//        assertNotNull(carService);
//        assertNotNull(yachtService);
//    }
//
//    @Test
//    public void saveAll() {
//        carService.saveCar(car1);
//        carService.saveCar(car2);
//
//        yachtService.saveYacht(yacht1);
//
//        final int actual = vehicleService.countAllVehicles();
//        final int expected = 3;
//
//        assertEquals(expected, actual);
//    }
//
//
//}