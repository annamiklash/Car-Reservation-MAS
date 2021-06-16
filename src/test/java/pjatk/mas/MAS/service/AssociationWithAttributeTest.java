//package pjatk.mas.MAS.service;
//
//import com.google.common.collect.ImmutableList;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import pjatk.mas.MAS.model.dto.*;
//import pjatk.mas.MAS.model.enums.VehicleAvailabilityEnum;
//
//import java.math.BigInteger;
//import java.time.LocalDate;
//import java.util.Arrays;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//class AssociationWithAttributeTest {
//
//    @Autowired
//    private CustomerService customerService;
//
//
//
//    @Autowired
//    private ReservationService reservationService;
//
//    Car car1, car2;
//    Address address1, address2;
//    Customer customer1, customer2;
//
//    @Test
//    public void testRequiredDependencies() {
//        assertNotNull(customerService);
//        assertNotNull(reservationService);
//    }
//
//    @BeforeEach
//    public void initializeData() {
//        car1 = Car.builder()
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
//                .make("Audi")
//                .model("RS7")
//                .color(Arrays.asList("Grey").toArray(new String[0]))
//                .availability(VehicleAvailabilityEnum.AVAILABLE)
//                .dailyRentCost(120)
//                .registrationPlate("WA12345")
//                .horsePower(250)
//                .build();
//
//        address1 = Address.builder()
//                .city("Warsaw")
//                .streetName("Street")
//                .streetNumber(1)
//                .zipCode("01-157")
//                .build();
//
//        address2 = Address.builder()
//                .city("Warsaw")
//                .streetName("Street")
//                .streetNumber(2)
//                .zipCode("01-157")
//                .build();
//
//        customer1 = Customer.builder()
//                .firstName("Hanna")
//                .lastName("Miklash")
//                .username("HannaM")
//                .birthdate(LocalDate.parse("1994-06-22"))
//                .email("hanna@gmail.com")
//                .phoneNumber(new BigInteger("123456789"))
//                .build();
//
//        customer1.setAddress(address1);
//
//        customer2 = Customer.builder()
//                .firstName("Jane")
//                .lastName("Doe")
//                .username("JaneD")
//                .birthdate(LocalDate.parse("1994-01-01"))
//                .email("jane@gmail.com")
//                .phoneNumber(new BigInteger("123456789"))
//                .build();
//
//        customer2.setAddress(address2);
//    }
//
//    @Test
//    public void saveTest() {
//        customerService.saveCustomer(customer1);
//        customerService.saveCustomer(customer2);
//
//
//
//        final Reservation reservation1 = Reservation.builder()
//                .dateFrom(LocalDate.parse("2021-06-01"))
//                .dateTo(LocalDate.parse("2021-06-05"))
//                .build();
//
//        reservation1.setCustomer(customer1);
//        reservation1.setCar(car1);
//        reservationService.saveReservation(reservation1);
//
//        final Reservation reservation2 = Reservation.builder()
//                .dateFrom(LocalDate.parse("2021-06-01"))
//                .dateTo(LocalDate.parse("2021-06-05"))
//                .build();
//
//        reservation2.setCustomer(customer2);
//        reservation2.setCar(car2);
//        reservationService.saveReservation(reservation2);
//
//        final Customer customerFromDb = customerService.findCustomerById(customer1.getId());
//        final int actualCustomerReservation = customerFromDb.getCustomerReservations().size();
//        final int expectedCustomerReservation = 1;
//
//        final Vehicle vehicleFromDb = vehicleService.findVehicleById(car1.getId());
//        final int actualVehicleReservations = vehicleFromDb.getVehicleReservations().size();
//        final int expectedVehicleReservations = 1;
//
//        assertEquals(expectedCustomerReservation, actualCustomerReservation);
//        assertEquals(expectedVehicleReservations, actualVehicleReservations);
//
//        final ImmutableList<Reservation> reservationsList = reservationService.findAll();
//        final int actualReservationsNumber = reservationsList.size();
//        final int expectedReservationsNumber = 2;
//
//        assertEquals(expectedReservationsNumber, actualReservationsNumber);
//    }
//}