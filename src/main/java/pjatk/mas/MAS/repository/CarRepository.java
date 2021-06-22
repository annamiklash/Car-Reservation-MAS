package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.Car;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    //TODO: Finish query

    @Query("select c " +
            "from car c " +
            "left join reservation r on r.car.id = c.id  and r.dateFrom <= :from and r.dateTo >=:to " +
            "where r.id is null " +
            "and  c.rentalLocation.id=:locId")
    List<Car> findAllByRentalLocation_Id(@Param("locId") long locationId, @Param("from") LocalDate from, @Param("to") LocalDate to);

    List<Car> findAllByMechanicsShop_Id(long id);

//    @Query("select c from car c where c.engineTypeEnum='ELECTRIC'")
//    List<Car> findAllElectric();
//
//    @Query("select c from car c where c.engineTypeEnum='FUEL'")
//    List<Car> findAllFuel();
//
//    @Query("select c from car c where c.engineTypeEnum='HYBRID'")
//    List<Car> findAllHybrid();


}
