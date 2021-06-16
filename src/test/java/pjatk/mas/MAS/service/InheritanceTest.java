//package pjatk.mas.MAS.service;
//
//
//import com.google.common.collect.ImmutableList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import pjatk.mas.MAS.model.Dimensions;
//import pjatk.mas.MAS.model.dto.Car;
//import pjatk.mas.MAS.model.dto.ElectricCar;
//import pjatk.mas.MAS.model.dto.LastMaintenanceInfo;
//import pjatk.mas.MAS.model.enums.VehicleAvailabilityEnum;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.datasource.jpa.hibernate.ddl-auto=validate")
//@AutoConfigureMockMvc
//class InheritanceTest {
//
//    @Autowired
//    private VehicleService vehicleService;
//
//    @Autowired
//    private CarService carService;
//
//
//    LastMaintenanceInfo lastMaintenanceInfo;
////    Dimensions dimensions;
//    Car car1, car2;
////    Yacht yacht1;
//
//    @BeforeEach
//    public void initializeData() {
//
//        lastMaintenanceInfo = LastMaintenanceInfo.builder()
//                .inspectionDate(LocalDate.of(2020, 5, 30))
//                .isSafeToDrive(true)
//                .build();
//
//        car1 = ElectricCar.builder()
//                .make("Toyota")
//                .model("Yaris")
//                .color(Arrays.asList("Black", "White").toArray(new String[0]))
//                .availability(VehicleAvailabilityEnum.AVAILABLE)
//                .dailyRentCost(50)
//                .registrationPlate("WY12345")
//                .horsePower(80)
//                .isSelfDriving(false)
//                .milesPerCharge(230)
//                .lastMaintenanceInfo(lastMaintenanceInfo)
//                .build();
//
////        car2 = Car.builder()
////                .id(1001L)
////                .make("Audi")
////                .model("RS7")
////                .color(Arrays.asList("Grey").toArray(new String[0]))
////                .availability(VehicleAvailabilityEnum.AVAILABLE)
////                .dailyRentCost(120)
////                .registrationPlate("WA12345")
////                .horsePower(250)
////                .build();
//
////        dimensions = Dimensions.builder()
////                .length(30)
////                .width(5)
////                .height(4)
////                .build();
//
//    }
//
//    @Test
//    public void testRequiredDependencies() {
//        assertNotNull(vehicleService);
//        assertNotNull(carService);
////        assertNotNull(yachtService);
//    }
//
//    @Test
//    public void saveAll() {
//        carService.saveCar(car1);
////        carService.saveCar(car2);
//
//        final ImmutableList<ElectricCar> list = carService.findAllElectric();
//        list.forEach(electricCar -> System.out.println(electricCar.toString()));
//        final int actual = list.size();
//        final int expected = 2;
//
//        assertEquals(expected, actual);
//    }
//
//
//}
