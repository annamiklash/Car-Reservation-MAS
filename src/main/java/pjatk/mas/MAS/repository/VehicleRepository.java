package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.Vehicle;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("select v from vehicle v left join fetch v.vehicleReservations where v.id= :id")
    Optional<Vehicle> findVehicleById(@Param("id") Long id);
}
