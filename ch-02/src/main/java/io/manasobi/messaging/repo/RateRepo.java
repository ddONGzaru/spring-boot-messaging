package io.manasobi.messaging.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import io.manasobi.messaging.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo extends JpaRepository<Rate, String> {

	List<Rate> findByDate(LocalDate date);

	Rate findByDateAndCode(LocalDate date, String code);
}
