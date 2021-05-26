package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Yacht;
import pjatk.mas.MAS.repository.YachtRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class YachtService {

    private YachtRepository yachtRepository;

    public ImmutableList<Yacht> findAll() {
        final List<Yacht> yachts = yachtRepository.findAll();
        return ImmutableList.copyOf(yachts);
    }

    public void saveYacht(Yacht yacht) {
        yachtRepository.save(yacht);
    }

}
