package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Address;
import pjatk.mas.MAS.repository.AddressRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AddressService {

    private AddressRepository addressRepository;

    public ImmutableList<Address> findAll() {
        final List<Address> addresses = addressRepository.findAll();
        return ImmutableList.copyOf(addresses);
    }

    public void saveAddress(Address address) {
        addressRepository.save(address);
    }
}
