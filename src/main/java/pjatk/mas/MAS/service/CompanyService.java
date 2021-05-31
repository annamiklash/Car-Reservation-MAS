package pjatk.mas.MAS.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Company;
import pjatk.mas.MAS.repository.CompanyRepository;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void save(Company company) {
        companyRepository.save(company);
    }
}
