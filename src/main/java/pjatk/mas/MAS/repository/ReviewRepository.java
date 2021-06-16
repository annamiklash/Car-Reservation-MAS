package pjatk.mas.MAS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.mas.MAS.model.dto.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
