package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.mas.MAS.model.dto.Invoice;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
}
