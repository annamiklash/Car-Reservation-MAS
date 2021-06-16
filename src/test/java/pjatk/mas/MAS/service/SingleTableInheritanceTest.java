package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import pjatk.mas.MAS.model.dto.*;
import pjatk.mas.MAS.model.enums.DaysOfTheWeekEnum;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.datasource.jpa.hibernate.ddl-auto=validate")
@AutoConfigureMockMvc
public class SingleTableInheritanceTest {

    @Autowired
    RentalLocationService rentalLocationService;

    @Autowired
    CompanyService companyService;

    Company c;
    RentalLocation loc1, loc2;
    Address address1, address2;

    @BeforeEach
    public void initData() {
        address1 = Address.builder()
                .city("Warsaw")
                .streetName("Tyszkiewicza")
                .streetNumber(1)
                .zipCode("01-157")
                .build();

        address2 = Address.builder()
                .city("Warsaw")
                .streetName("Tyszkiewicza")
                .streetNumber(2)
                .zipCode("01-157")
                .build();

        c = Company.builder()
                .name("Name")
                .email("email@email.com")
                .phoneNumber(new BigInteger("123456789"))
                .address(address1)
                .build();

        address2 = Address.builder()
                .city("Warsaw")
                .streetName("Tyszkiewicza")
                .streetNumber(1)
                .zipCode("01-157")
                .build();

        final List<BusinessHours> businessHours = Arrays.asList(
                BusinessHours.builder()
                        .day(DaysOfTheWeekEnum.MONDAY)
                        .timeFrom(8)
                        .timeTo(20)
                        .build(),
                BusinessHours.builder()
                        .day(DaysOfTheWeekEnum.TUESDAY)
                        .timeFrom(8)
                        .timeTo(20)
                        .build(),
                BusinessHours.builder()
                        .day(DaysOfTheWeekEnum.WEDNESDAY)
                        .timeFrom(8)
                        .timeTo(20)
                        .build(),
                BusinessHours.builder()
                        .day(DaysOfTheWeekEnum.THURSDAY)
                        .timeFrom(8)
                        .timeTo(20)
                        .build(),
                BusinessHours.builder()
                        .day(DaysOfTheWeekEnum.FRIDAY)
                        .timeFrom(8)
                        .timeTo(20)
                        .build()
        );

        loc1 = OpenLocation.builder()
                .name("Name")
                .email("email@email.com")
                .phoneNumber(new BigInteger("123456789"))
                .address(address2)
                .businessHours(new HashSet<>(businessHours))
                .build();

        c.addLocation(loc1);
    }

    @Test
    public void saveAll() {
        companyService.save(c);
        rentalLocationService.saveLocation(loc1);
//        carService.saveCar(car2);

        final ImmutableList<RentalLocation> all = rentalLocationService.findAllOpen();
//        list.forEach(electricCar -> System.out.println(electricCar.toString()));
        final int actual = all.size();
        final int expected = 1;

        assertEquals(expected, actual);
    }
}
