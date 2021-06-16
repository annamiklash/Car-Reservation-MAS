package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from users u left join fetch u.customerReservations where u.username is not null and u.id= :id")
    Optional<User> findCustomerById(@Param("id") Long id);


    @Query("select u from users u where u.username is not null")
    List<User> findAllCustomers();

    @Query("select u from users u where u.pesel is not null")
    List<User> findAllEmployees();


}
