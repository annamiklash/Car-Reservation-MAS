package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.mas.MAS.model.dto.Yacht;

@Repository
public interface YachtRepository extends JpaRepository<Yacht, Long> {
}
