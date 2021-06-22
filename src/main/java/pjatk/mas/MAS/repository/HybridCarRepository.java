package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pjatk.mas.MAS.model.dto.HybridCar;

import java.util.List;

public interface HybridCarRepository extends JpaRepository<HybridCar, Long> {

    @Query("from HybridCar")
    List<HybridCar> findAllHybrid();
}
