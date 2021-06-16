package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Car;
import pjatk.mas.MAS.model.dto.Insurance;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.model.dto.User;
import pjatk.mas.MAS.model.enums.ReservationStatusEnum;
import pjatk.mas.MAS.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {

    private final CarService carService;
    private final ReservationRepository reservationRepository;
    private final UserService userService;

    public ImmutableList<Reservation> findAll() {
        final List<Reservation> reservations = reservationRepository.findAllReservations();
        return ImmutableList.copyOf(reservations);
    }

    public Reservation createReservation(long customerId, long carId, LocalDate from, LocalDate to) {
        final User user = userService.findCustomerById(customerId);
        if (user.getAge() < User.MIN_RESERVATION_CUSTOMER_YEAR) {
            throw new RuntimeException("Customer under " + User.MIN_RESERVATION_CUSTOMER_YEAR + " cannot reserve a car");
        }
        final Car car = carService.findById(carId);

        final Reservation reservation = Reservation.builder()
                .id(UUID.randomUUID())
                .car(car)
                .user(user)
                .dateFrom(from)
                .dateTo(to)
                .reservationStatus(ReservationStatusEnum.INCOMPLETE)
                .build();

        saveReservation(reservation);
        return reservation;
    }

    public Reservation addReservationInsurance(List<Insurance> insurances, UUID reservationId) {
        final Reservation reservation = findById(reservationId);
        reservation.setInsurances(new HashSet<>(insurances));
        reservation.setReservationStatus(ReservationStatusEnum.COMPLETE);
        saveReservation(reservation);
        return reservation;
    }


    public void saveReservation(Reservation reservation) {
        reservationRepository.saveAndFlush(reservation);
    }

    public ImmutableList<Reservation> findAllByCustomerId(long customerId) {
        final List<Reservation> reservationsList = reservationRepository.findAllByUser_IdOrderByDateFromDesc(customerId);
        return ImmutableList.copyOf(reservationsList);
    }

    public Reservation findById(UUID id) {
        final Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isEmpty()) {
            throw new RuntimeException("Reservation with id " + id + " does not exist");
        }
        return optionalReservation.get();
    }
}
