package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Insurance;
import pjatk.mas.MAS.repository.InsuranceRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;


    public ImmutableList<Insurance> findAll() {
        final List<Insurance> insurances = insuranceRepository.findAll();
        return ImmutableList.copyOf(insurances);
    }


    public void saveInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
    }
}
