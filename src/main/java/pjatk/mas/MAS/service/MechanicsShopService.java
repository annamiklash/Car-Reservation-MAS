package pjatk.mas.MAS.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.MechanicsShop;
import pjatk.mas.MAS.model.exceptions.CustomErrorException;
import pjatk.mas.MAS.repository.MechanicsShopRepository;
import pjatk.mas.MAS.validator.model.Error;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MechanicsShopService {

    private final MechanicsShopRepository mechanicsShopRepository;

    public MechanicsShop findById(long id) {
        final Optional<MechanicsShop> optional = mechanicsShopRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomErrorException(Error.builder()
                    .field("id")
                    .field(String.valueOf(id))
                    .description("No Mechanics shop with id " + id)
                    .build());
        }
        return optional.get();
    }

    public void save(MechanicsShop mechanicsShop) {
        mechanicsShopRepository.save(mechanicsShop);
    }
}
