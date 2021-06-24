package pjatk.mas.MAS.model.dto.interfaces.user;

import pjatk.mas.MAS.model.enums.AdminAccessLevel;

/**
 * Interface containing getters and setters for fields that are required to have by an object of class User that is an Admin
 */
public interface Admin {

    AdminAccessLevel getAdminAccessLevel();

    void setAdminAccessLevel(AdminAccessLevel adminAccessLevel);
}
