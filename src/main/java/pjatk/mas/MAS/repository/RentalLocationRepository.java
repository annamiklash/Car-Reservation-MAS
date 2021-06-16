package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.RentalLocation;

@Repository
public interface RentalLocationRepository extends  JpaRepository<RentalLocation, Long> {


}
