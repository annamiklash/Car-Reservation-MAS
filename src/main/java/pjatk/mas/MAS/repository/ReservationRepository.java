package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.Reservation;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query("select r from reservation r")
    List<Reservation> findAllReservations();

    List<Reservation> findAllByUser_IdOrderByDateFromDesc(long id);

    List<Reservation> findAllByUser_Id(long id);
}
