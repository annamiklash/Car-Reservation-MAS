package pjatk.mas.MAS.validator;

import lombok.experimental.UtilityClass;
import pjatk.mas.MAS.model.dto.User;
import pjatk.mas.MAS.model.enums.AdminAccessLevel;
import pjatk.mas.MAS.model.enums.UserType;
import pjatk.mas.MAS.validator.model.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@UtilityClass
public class UserValidator {


    public List<Error> validateUserToCreate(User user) {
        final List<Error> errorList = new ArrayList<>();
        final Set<UserType> userType = user.getUserType();

        if (userType.contains(UserType.CUSTOMER)) {
            final String username = user.getUsername();
            if (username == null) {
                user.setUsername(username);
            } else {
                errorList.add(Error.builder()
                        .field("Username")
                        .value("Null")
                        .description("No username specified for CUSTOMER user type")
                        .build());
            }
        }
        if (userType.contains(UserType.EMPLOYEE)) {
            final String pesel = user.getPesel();
            if (pesel == null) {
                user.setPesel(pesel);
            } else {
                errorList.add(Error.builder()
                        .field("Pesel")
                        .value("Null")
                        .description("No pesel specified for EMPLOYEE user type")
                        .build());
            }
        }

        if (userType.contains(UserType.ADMIN)) {
            final AdminAccessLevel optionalAdminAccessLevel = user.getAdminAccessLevel();
            if (optionalAdminAccessLevel == null) {
                user.setAdminAccessLevel(optionalAdminAccessLevel);
            } else {
                errorList.add(Error.builder()
                        .field("adminAccessLevel")
                        .value("Null")
                        .description("No admin access level specified for ADMIN user type")
                        .build());
            }
        }
        return errorList;
    }


}
