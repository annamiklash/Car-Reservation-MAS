package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Address;
import pjatk.mas.MAS.repository.AddressRepository;

import java.util.List;

/**
 * Business logic layer for entity Address
 */
@Service
@AllArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    /**
     * get all addresses
     *
     * @return list of all addresses
     */
    public ImmutableList<Address> findAll() {
        final List<Address> addresses = addressRepository.findAll();
        return ImmutableList.copyOf(addresses);
    }

    /**
     * save address to DB
     *
     * @param address address to be save to db
     */
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }
}
