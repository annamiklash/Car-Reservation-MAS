package pjatk.mas.MAS.model.dto.interfaces.location;

import pjatk.mas.MAS.model.dto.BusinessHours;

import java.util.Set;

public interface OpenLocation {

    Set<BusinessHours> getBusinessHours();

    void setBusinessHours(Set<BusinessHours> businessHours);
}
