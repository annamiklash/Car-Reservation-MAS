package pjatk.mas.MAS.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Bill;
import pjatk.mas.MAS.repository.BillRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class BillService {

    private final BillRepository billRepository;

    private final ReservationService reservationService;

    private final UserService userService;

    @Transactional
    public Bill generateBill(UUID reservationId) {
//        final Reservation reservation = reservationService.findById(reservationId);
//
//        final LocalDate dateFrom = reservation.getDateFrom();
//        final LocalDate dateTo = reservation.getDateTo();
//
//        final long days = DAYS.between(dateFrom, dateTo);
//        final Integer carDailyRentCost = reservation.getCar().getDailyRentCost();
//        final Integer carReservationCost = Math.toIntExact(days * carDailyRentCost);
//
//        final Set<Insurance> insurances = reservation.getInsurances();
//        final int carInsuranceCost = insurances.stream()
//                .mapToInt(insurance -> (int) (insurance.getCostPerDay() * days))
//                .sum();
//
//        final Integer totalCost = carInsuranceCost + carReservationCost;
//        final Bill bill = Bill.builder()
//                .id(reservationId)
//                .isPayed(false)
//                .totalCost(totalCost).build();
//
//        final User user = reservation.getUser();
//
//        if (customerService.isVIPCustomer(user.getId())) {
//            final Integer discountAmount = (totalCost * ((VIPUser) user).getDiscount()) / 100;
//            bill.setDiscountAmount(discountAmount);
//            bill.setAmountAfterDiscount(totalCost - discountAmount);
//
//            saveBill(bill);
//            return bill;
//        }
//
//        bill.setDiscountAmount(0);
//        bill.setAmountAfterDiscount(totalCost);
//        saveBill(bill);
//
//        return bill;
        return null;
    }

    public void saveBill(Bill bill) {
        billRepository.save(bill);
    }
}
