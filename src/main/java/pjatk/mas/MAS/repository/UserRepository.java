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


//    @Query(nativeQuery = true,
//            value = "select u from users u " +
//                    "join user_type ut on u.id_user=ut.id_user " +
//                    "where ut.user_type='CUSTOMER' and u.id_user=:id")
//    Optional<User> findCustomerById(@Param("id") Long id);

    @Query(nativeQuery = true,
            value = "select u from users u " +
            "join user_type ut on u.id_user=ut.id_user " +
                    "where ut.user_type='CUSTOMER'")
    List<User> findAllCustomers();

    @Query(nativeQuery = true,
            value = "select u from users u " +
                    "join user_type ut on u.id_user=ut.id_user " +
                    "where ut.user_type='EMPLOYEE'")
    List<User> findAllEmployees();


}
