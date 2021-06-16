package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.mas.MAS.model.dto.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
