package io.manasobi.repo;

import java.util.Date;
import java.util.List;

import io.manasobi.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepo extends JpaRepository<Rate,String> {

	List<Rate> findByDate(Date date);

	Rate findByDateAndCode(Date date, String code);
}
