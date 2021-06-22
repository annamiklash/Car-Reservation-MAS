package pjatk.mas.MAS.model.containers;

import lombok.Data;
import pjatk.mas.MAS.model.dto.Insurance;

import java.util.List;

@Data
public class InsuranceListContainer {

    private List<Insurance> insuranceList;
}
