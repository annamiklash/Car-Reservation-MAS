package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.LastMaintenanceInfo;

@Repository
public interface LastMaintenanceInfoRepository extends JpaRepository<LastMaintenanceInfo, Long> {

}