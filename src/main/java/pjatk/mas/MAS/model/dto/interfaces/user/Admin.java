package pjatk.mas.MAS.model.dto.interfaces.user;

import pjatk.mas.MAS.model.enums.AdminAccessLevel;

public interface Admin {

    AdminAccessLevel getAdminAccessLevel();

    void setAdminAccessLevel(AdminAccessLevel adminAccessLevel);
}
