package pjatk.mas.MAS.interfaces.user;

import pjatk.mas.MAS.model.enums.AdminAccessLevel;

public interface Admin {

    AdminAccessLevel getAdminAccessLevel();

    void setAdminAccessLevel(AdminAccessLevel adminAccessLevel);
}
