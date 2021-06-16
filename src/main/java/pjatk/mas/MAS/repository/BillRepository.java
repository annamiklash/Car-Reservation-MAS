package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.mas.MAS.model.dto.Bill;

import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {
}
