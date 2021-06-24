package pjatk.mas.MAS.model.containers;

import lombok.Data;
import pjatk.mas.MAS.model.dto.Insurance;

import java.util.List;

/**
 * Wrapper class for list of Insurance objects to use in controller
 */
@Data
public class InsuranceListContainer {

    /**
     * List of selected insurances by a Customer
     */
    private List<Insurance> insuranceList;
}
