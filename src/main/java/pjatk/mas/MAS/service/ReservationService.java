package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.model.exceptions.CustomErrorException;
import pjatk.mas.MAS.repository.ReservationRepository;
import pjatk.mas.MAS.validator.model.Error;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Business logic layer for entity Reservation
 */
@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    /**
     * @return find all reservations stored in DB
     */
    public ImmutableList<Reservation> findAll() {
        final List<Reservation> reservations = reservationRepository.findAllReservations();
        return ImmutableList.copyOf(reservations);
    }

    /**
     * @param reservation reservation to save in DB
     */
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public ImmutableList<Reservation> findAllByCustomerId(long customerId) {
        final List<Reservation> reservationsList = reservationRepository.findAllByUser_IdOrderByDateFromDesc(customerId);
        return ImmutableList.copyOf(reservationsList);
    }

    /**
     * @param id reservation id
     * @return insurance object with id specified in param
     */
    public Reservation findById(UUID id) {
        final Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isEmpty()) {
            throw new CustomErrorException(Error.builder()
                    .field("ID")
                    .value(id.toString())
                    .description("Reservation with id " + id + " does not exist")
                    .build());
        }
        return optionalReservation.get();
    }
}
