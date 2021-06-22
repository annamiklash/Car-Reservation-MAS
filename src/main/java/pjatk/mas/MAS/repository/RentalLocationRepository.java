package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.RentalLocation;

import java.util.Optional;

@Repository
public interface RentalLocationRepository extends  JpaRepository<RentalLocation, Long> {

    @Override
    @Query("select l from rental_location l left join fetch l.businessHours where l.id= :aLong")
    Optional<RentalLocation> findById(Long aLong);
}
