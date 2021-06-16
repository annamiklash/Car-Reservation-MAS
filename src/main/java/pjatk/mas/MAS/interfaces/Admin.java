package pjatk.mas.MAS.interfaces;

import pjatk.mas.MAS.model.enums.AdminAccessLevel;

public interface Admin {

    AdminAccessLevel getAdminAccessLevel();

    void setAdminAccessLevel(AdminAccessLevel adminAccessLevel);
}
