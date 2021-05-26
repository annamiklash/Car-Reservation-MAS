package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.repository.ReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ImmutableList<Reservation> findAll() {
        final List<Reservation> reservations = reservationRepository.findAll();
        return ImmutableList.copyOf(reservations);
    }

    public void saveReservation(Reservation reservation) {
        reservationRepository.saveAndFlush(reservation);
    }
}
