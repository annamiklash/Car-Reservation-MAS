//package pjatk.mas.MAS.service;
//
//import com.google.common.collect.ImmutableList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import pjatk.mas.MAS.model.dto.*;
//import pjatk.mas.MAS.model.enums.CarAvailabilityEnum;
//import pjatk.mas.MAS.model.enums.CarBodyStyleEnum;
//import pjatk.mas.MAS.model.enums.DaysOfTheWeekEnum;
//
//import javax.transaction.Transactional;
//import javax.validation.ConstraintViolationException;
//import java.math.BigInteger;
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.datasource.jpa.hibernate.ddl-auto=validate")
//@AutoConfigureMockMvc
//public class XorValidationTest {
//
//    @Autowired
//    CompanyService companyService;
//
//    @Autowired
//    RentalLocationService rentalLocationService;
//
//    @Autowired
//    CarService carService;
//
//    @Autowired
//    MechanicsShopService mechanicsShopService;
//
//    Company c;
//    RentalLocation loc1;
//    MechanicsShop m1;
//    Address address1, address2, address3;
//    Car car;
//
//    @BeforeEach
//    public void initData() {
//        address1 = Address.builder()
//                .city("Warsaw")
//                .streetName("Tyszkiewicza")
//                .streetNumber(1)
//                .zipCode("01-157")
//                .build();
//
//        address2 = Address.builder()
//                .city("Warsaw")
//                .streetName("Tyszkiewicza")
//                .streetNumber(2)
//                .zipCode("01-157")
//                .build();
//
//        address3 = Address.builder()
//                .city("Warsaw")
//                .streetName("Tyszkiewicza")
//                .streetNumber(2)
//                .zipCode("01-157")
//                .build();
//
//        c = Company.builder()
//                .name("Name")
//                .email("email@email.com")
//                .phoneNumber(new BigInteger("123456789"))
//                .address(address1)
//                .build();
//
//        final List<BusinessHours> businessHours = Arrays.asList(
//                BusinessHours.builder()
//                        .day(DaysOfTheWeekEnum.MONDAY)
//                        .timeFrom(8)
//                        .timeTo(20)
//                        .build(),
//                BusinessHours.builder()
//                        .day(DaysOfTheWeekEnum.TUESDAY)
//                        .timeFrom(8)
//                        .timeTo(20)
//                        .build(),
//                BusinessHours.builder()
//                        .day(DaysOfTheWeekEnum.WEDNESDAY)
//                        .timeFrom(8)
//                        .timeTo(20)
//                        .build(),
//                BusinessHours.builder()
//                        .day(DaysOfTheWeekEnum.THURSDAY)
//                        .timeFrom(8)
//                        .timeTo(20)
//                        .build(),
//                BusinessHours.builder()
//                        .day(DaysOfTheWeekEnum.FRIDAY)
//                        .timeFrom(8)
//                        .timeTo(20)
//                        .build()
//        );
//
//        loc1 = OpenLocation.builder()
//                .name("Name")
//                .email("email@email.com")
//                .phoneNumber(new BigInteger("123456789"))
//                .address(address2)
//                .businessHours(new HashSet<>(businessHours))
//                .build();
//
//
//        car = Car.builder()
//                .availability(CarAvailabilityEnum.AVAILABLE)
//                .bodyStyle(CarBodyStyleEnum.HATCHBACK)
//                .color(new String[]{"Black"})
//                .dailyRentCost(80)
//                .horsePower(112)
//                .manufactureYear(2010)
//                .lastMaintenanceInfo(LastMaintenanceInfo.builder()
//                        .isSafeToDrive(true)
//                        .inspectionDate(LocalDate.of(2021, 5, 30))
//                        .build())
//                .make("BMW")
//                .model("116i")
//                .registrationPlate("WY12345")
//                .build();
//
//        m1 = MechanicsShop.builder()
//                .name("Name")
//                .email("email@email.com")
//                .phoneNumber(new BigInteger("123456789"))
//                .address(address3)
//                .build();
//
//    }
//
//    @Transactional
//    @Test
//    public void testXor() {
//        companyService.save(c);
//        loc1.setHq(c);
//        rentalLocationService.saveLocation(loc1);
//        car.setRentalLocation(loc1);
//        carService.saveCar(car);
//
//        mechanicsShopService.save(m1);
//
//        final int expectedCarsAtRentalLoc = 1;
//        final ImmutableList<Car> rentalLocationCars
//                = carService.findAllAvailableCarsByRentalLocationId(loc1.getId());
//        final int actualAtRentalLoc = rentalLocationCars.size();
//
//        assertEquals(expectedCarsAtRentalLoc, actualAtRentalLoc);
//
//        final ImmutableList<Car> carsAtMechanicsShopByShopId = carService.findAllCarsAtMechanicsShopByShopId(m1.getId());
//        final int actualCarsAtMechanicsBefore = carsAtMechanicsShopByShopId.size();
//        final int expectedCarsAtMechanicsBefore = 0;
//
//        assertEquals(expectedCarsAtMechanicsBefore, actualCarsAtMechanicsBefore);
//
//        carService.moveCarToMechanicsShop(car.getId(), m1.getId());
//
//        final ImmutableList<Car> carsAtMechanicsShopByShopIdAfter = carService.findAllCarsAtMechanicsShopByShopId(m1.getId());
//        final int actualCarsAtMechanicsAfter = carsAtMechanicsShopByShopIdAfter.size();
//        final int expectedCarsAtMechanicsAfter = 1;
//
//        assertEquals(expectedCarsAtMechanicsAfter, actualCarsAtMechanicsAfter);
//
//        final int expectedCarsAtRentalLocAfter = 0;
//        final ImmutableList<Car> rentalLocationCarsAfter
//                = carService.findAllAvailableCarsByRentalLocationId(loc1.getId());
//        final int actualAtRentalLocAfter = rentalLocationCarsAfter.size();
//
//        assertEquals(expectedCarsAtRentalLocAfter, actualAtRentalLocAfter);
//
//    }
//
//}
