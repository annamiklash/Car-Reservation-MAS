package pjatk.mas.MAS.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Company;
import pjatk.mas.MAS.repository.CompanyRepository;
/**
 * Business logic layer for entity Company
 */
@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    /**
     * @param company save company to DB
     */
    public void save(Company company) {
        companyRepository.save(company);
    }
}
