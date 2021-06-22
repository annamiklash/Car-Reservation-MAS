package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pjatk.mas.MAS.model.dto.ICECar;

import java.util.List;

public interface ICECarRepository extends JpaRepository<ICECar, Long> {

    @Query("from ICECar")
    List<ICECar> findAllFuel();
}
