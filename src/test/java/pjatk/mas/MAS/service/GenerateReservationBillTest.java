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
//import java.math.BigInteger;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.UUID;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.datasource.jpa.hibernate.ddl-auto=validate")
//@AutoConfigureMockMvc
//public class GenerateReservationBillTest {
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
//    ReservationService reservationService;
//
//    @Autowired
//    BillService billService;
//
//    @Autowired
//    InsuranceService insuranceService;
//
//    @Autowired
//    UserService userService;
//
//    Company c;
//    RentalLocation loc1;
//    Address address1, address2, address3, address4;
//    Car car;
//    Reservation reservation;
//    Insurance insurance;
//    User user;
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
//        address4 = Address.builder()
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
//                .manufactureYear(2018)
//                .lastMaintenanceInfo(LastMaintenanceInfo.builder()
//                        .isSafeToDrive(true)
//                        .inspectionDate(LocalDate.of(2021, 5, 30))
//                        .build())
//                .make("BMW")
//                .model("116i")
//                .registrationPlate("WY12345")
//                .build();
//
//        user = User.builder()
//                .firstName("Hanna")
//                .lastName("Mikalsh")
//                .address(address4)
//                .email("anna@email.com")
//                .username("hanna_m")
//                .birthdate(LocalDate.of(1994, Month.JUNE, 22))
//                .phoneNumber(new BigInteger("123456789"))
//                .build();
//
//        insurance = Insurance.builder()
//                .name("Best Insurance")
//                .costPerDay(20)
//                .description("Description goes here")
//                .maxInsuredValue(1000)
//                .build();
//
//        reservation = Reservation.builder()
//                .id(UUID.randomUUID())
//                .dateFrom(LocalDate.of(2021, Month.JUNE, 22))
//                .dateTo(LocalDate.of(2021, Month.JUNE, 25))
//                .user(user)
//                .reservationInsurance(new HashSet<>(Arrays.asList(insurance)))
//                .car(car)
//                .build();
//    }
//
//
//    @Transactional
//    @Test
//    public void test() {
//        companyService.save(c);
//        loc1.setHq(c);
//        rentalLocationService.saveLocation(loc1);
//        car.setRentalLocation(loc1);
//
//        carService.saveCar(car);
//        insuranceService.saveInsurance(insurance);
//        userService.saveCustomer(user);
//        reservationService.saveReservation(reservation);
//
//        final ImmutableList<Reservation> all = reservationService.findAll();
//        final Reservation reservation = all.get(0);
//
//        final Bill bill = billService.generateBill(reservation.getId());
//        System.out.println(bill.toString());
//
//    }
//
//}
