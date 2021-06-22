package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pjatk.mas.MAS.model.dto.ElectricCarImpl;

import java.util.List;

public interface ElectricCarRepository extends JpaRepository<ElectricCarImpl, Long> {

    @Query("from ElectricCarImpl")
    List<ElectricCarImpl> findAllElectric();
}
