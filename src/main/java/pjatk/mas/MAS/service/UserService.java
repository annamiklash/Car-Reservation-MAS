package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.constants.Constants;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.model.dto.User;
import pjatk.mas.MAS.model.enums.ReservationStatusEnum;
import pjatk.mas.MAS.model.enums.UserTypeEnum;
import pjatk.mas.MAS.model.exceptions.CustomErrorException;
import pjatk.mas.MAS.repository.ReservationRepository;
import pjatk.mas.MAS.repository.UserRepository;
import pjatk.mas.MAS.validator.UserValidator;
import pjatk.mas.MAS.validator.model.Error;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Business logic layer for entity User
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;


    /**
     * @return list of all users stored in DB
     */
    public ImmutableList<User> findAll() {
        final List<User> users = userRepository.findAll();
        return ImmutableList.copyOf(users);
    }

    /**
     * @return list of all users if type Customer stored in DB
     */
    public ImmutableList<User> findAllCustomers() {
        final List<User> customers = userRepository.findAllCustomers();
        return ImmutableList.copyOf(customers);
    }

    /**
     * @return list of all users of type Employee stored in DB
     */
    public ImmutableList<User> findAllEmployees() {
        final List<User> employees = userRepository.findAllEmployees();
        return ImmutableList.copyOf(employees);
    }

    /**
     * @param user user to save in DB
     */
    public void addUser(User user) {
        final List<Error> errors = UserValidator.validateUserToCreate(user);
        if (errors.size() > 0) {
            throw new CustomErrorException(errors);
        }
        userRepository.save(user);
    }

    /**
     * finds and validates correct user type of user by id
     *
     * @param id user id
     * @return user object with id specified in param
     */
    @Transactional
    public User findCustomerById(Long id) {
        final Optional<User> optionalCustomer = userRepository.findCustomerById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NoSuchElementException("There is no customer with id " + id);
        }
        final User user = optionalCustomer.get();

        final boolean isVip = isVIPCustomer(user.getId());

        if (isVip) {
            user.setDiscount(Constants.VIP_DISCOUNT);
            user.getUserType().add(UserTypeEnum.VIP_CUSTOMER);
            addUser(user);

        } else {
            user.setDiscount(0);
            if (user.getUserType().contains(UserTypeEnum.VIP_CUSTOMER)) {
                user.getUserType().remove(UserTypeEnum.VIP_CUSTOMER);
            }
        }

        return user;
    }

    /**
     * check whether user is a VIP Customer
     *
     * @param id user ID
     * @return flag whether user is a VIP customer
     */
    public boolean isVIPCustomer(long id) {
        final Integer customerReservations = countCustomerFinishedReservations(id);
        return customerReservations >= Constants.RESERVATIONS_FOR_VIP;
    }

    /**
     * @param customerId user id
     * @return number of finished reservation in a year time from current time
     */
    private Integer countCustomerFinishedReservations(long customerId) {

        final List<Reservation> allReservations = reservationRepository.findAllByUser_Id(customerId);

        return Math.toIntExact(allReservations.stream()
                .filter(reservation -> reservation.getReservationStatus() == ReservationStatusEnum.FINISHED)
                .filter(reservation -> reservation.getDateFrom().isAfter(LocalDate.now().minusYears(1)))
                .count());
    }

}
