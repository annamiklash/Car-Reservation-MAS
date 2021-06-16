package pjatk.mas.MAS.service;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.constants.Constants;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.model.dto.User;
import pjatk.mas.MAS.model.enums.ReservationStatusEnum;
import pjatk.mas.MAS.repository.ReservationRepository;
import pjatk.mas.MAS.repository.UserRepository;
import pjatk.mas.MAS.validator.Error;
import pjatk.mas.MAS.validator.UserValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;


    public ImmutableList<User> findAll() {
        final List<User> users = userRepository.findAll();
        return ImmutableList.copyOf(users);
    }

    public ImmutableList<User> findAllCustomers() {
        final List<User> customers = userRepository.findAllCustomers();
        return ImmutableList.copyOf(customers);
    }

    public ImmutableList<User> findAllEmployees() {
        final List<User> employees = userRepository.findAllEmployees();
        return ImmutableList.copyOf(employees);
    }

    public void addUser(User user) {
        final List<Error> errors = UserValidator.validateUserToCreate(user);
        if (errors.size() > 0) {
            log.info(errors.toString());
            throw new RuntimeException(errors.toString());
        }
        userRepository.save(user);
    }


    public User findCustomerById(Long id) {
        final Optional<User> optionalCustomer = userRepository.findCustomerById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NoSuchElementException("There is no customer with id " + id);
        }
        final User user = optionalCustomer.get();
        final boolean isVip = isVIPCustomer(user.getId());

        return User.builder()
                .id(user.getId())
                .birthdate(user.getBirthdate())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .address(user.getAddress())
                .customerReservations(user.getCustomerReservations())
                .discount(isVip ? Constants.VIP_DISCOUNT : null)
                .build();

    }

    public Integer countCustomerFinishedReservations(long customerId) {

        final List<Reservation> allReservations = reservationRepository.findAllByUser_Id(customerId);

        return Math.toIntExact(allReservations.stream()
                .filter(reservation -> reservation.getReservationStatus() == ReservationStatusEnum.FINISHED)
                .filter(reservation -> reservation.getDateFrom().isAfter(LocalDate.now().minusYears(1)))
                .count());
    }

    public boolean isVIPCustomer(long id) {
        final Integer customerReservations = countCustomerFinishedReservations(id);
        return customerReservations >= Constants.RESERVATIONS_FOR_VIP;
    }

}
