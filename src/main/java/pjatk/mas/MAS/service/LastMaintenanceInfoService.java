package pjatk.mas.MAS.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.LastMaintenanceInfo;
import pjatk.mas.MAS.repository.LastMaintenanceInfoRepository;

@Service
@AllArgsConstructor
@Slf4j
public class LastMaintenanceInfoService {

    private final LastMaintenanceInfoRepository lastMaintenanceInfoRepository;

    public void update(LastMaintenanceInfo lastMaintenanceInfo) {
        lastMaintenanceInfoRepository.save(lastMaintenanceInfo);
    }

}
