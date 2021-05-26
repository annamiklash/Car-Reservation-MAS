package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Customer;
import pjatk.mas.MAS.repository.CustomerRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private CustomerRepository customerRepository;

    public ImmutableList<Customer> findAll() {
        final List<Customer> customers = customerRepository.findAll();
        return ImmutableList.copyOf(customers);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        final Optional<Customer> optionalCustomer = customerRepository.findCustomerById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        }
        throw new NoSuchElementException("There is no customer with id " + id);
    }
}
