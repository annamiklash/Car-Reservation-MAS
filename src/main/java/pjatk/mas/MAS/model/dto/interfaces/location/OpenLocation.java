package pjatk.mas.MAS.model.dto.interfaces.location;

import pjatk.mas.MAS.model.dto.BusinessHours;

import java.util.Set;

/**
 * Interface containing getters and setters for fields that are required to have by an object of class RentalLocation that is an OpenLocation
 */
public interface OpenLocation {

    Set<BusinessHours> getBusinessHours();

    void setBusinessHours(Set<BusinessHours> businessHours);
}
