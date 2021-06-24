package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Insurance;
import pjatk.mas.MAS.model.exceptions.CustomErrorException;
import pjatk.mas.MAS.repository.InsuranceRepository;
import pjatk.mas.MAS.validator.model.Error;

import java.util.List;
import java.util.Optional;

/**
 * Business logic layer for entity Insurance
 */
@Service
@AllArgsConstructor
@Slf4j
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;


    /**
     * @return list of all insurances stored in DB
     */
    public ImmutableList<Insurance> findAllInsurances() {
        final List<Insurance> insurances = insuranceRepository.findAll();
        return ImmutableList.copyOf(insurances);
    }

    /**
     * @param id insurance id
     * @return insurance object with id specified in param
     */
    public Insurance findById(Long id) {
        final Optional<Insurance> optionalInsurance = insuranceRepository.findById(id);
        if (optionalInsurance.isEmpty()) {
            throw new CustomErrorException(Error.builder()
                    .field("insurance")
                    .value("null")
                    .description("Insurance list null or empty")
                    .build());
        }
        return optionalInsurance.get();
    }


    /**
     * @param insurance save insurance to DB
     */
    public void saveInsurance(Insurance insurance) {
        insuranceRepository.save(insurance);
    }
}
