package io.manasobi.messaging.repo;

import io.manasobi.messaging.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RateRepo extends JpaRepository<Rate, String> {

	List<Rate> findByDate(LocalDate date);

	Rate findByDateAndCode(LocalDate date, String code);
}
