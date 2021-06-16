package pjatk.mas.MAS.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pjatk.mas.MAS.model.dto.Insurance;
import pjatk.mas.MAS.model.dto.Invoice;
import pjatk.mas.MAS.model.dto.Reservation;
import pjatk.mas.MAS.model.dto.User;
import pjatk.mas.MAS.repository.InvoiceRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@AllArgsConstructor
@Slf4j
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final ReservationService reservationService;

    private final UserService userService;

    @Transactional
    public Invoice generateBill(UUID reservationId) {
        final Reservation reservation = reservationService.findById(reservationId);

        final LocalDate dateFrom = reservation.getDateFrom();
        final LocalDate dateTo = reservation.getDateTo();

        final long days = DAYS.between(dateFrom, dateTo);
        final Integer carDailyRentCost = reservation.getCar().getDailyRentCost();
        final Integer carReservationCost = Math.toIntExact(days * carDailyRentCost);

        final Set<Insurance> insurances = reservation.getInsurances();
        final int carInsuranceCost = insurances.stream()
                .mapToInt(insurance -> (int) (insurance.getCostPerDay() * days))
                .sum();

        final Integer totalCost = carInsuranceCost + carReservationCost;
        final Invoice invoice = Invoice.builder()
                .id(reservationId)
                .isPayed(false)
                .totalCost(totalCost).build();

        final User user = reservation.getUser();

        if (userService.isVIPCustomer(user.getId())) {
            final Integer discountAmount = (totalCost * user.getDiscount()) / 100;
            invoice.setDiscountAmount(discountAmount);
            invoice.setAmountAfterDiscount(totalCost - discountAmount);

            saveInvoice(invoice);
            return invoice;
        }

        invoice.setDiscountAmount(0);
        invoice.setAmountAfterDiscount(totalCost);
        saveInvoice(invoice);

        return invoice;

    }

    public void saveInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
