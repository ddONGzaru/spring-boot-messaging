package io.manasobi;

import io.manasobi.messaging.domain.Rate;
import io.manasobi.messaging.repo.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class AppRunner implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class, args);
	}

	@Autowired
	private RateRepo rateRepo;

	@Override
	public void run(ApplicationArguments args) {

		LocalDate now = LocalDate.now();

		rateRepo.save(Rate.of("EUR", 0.88857F, now));
		rateRepo.save(Rate.of("JPY", 102.17F, now));
		rateRepo.save(Rate.of("KRW", 1020.17F, now));
		rateRepo.save(Rate.of("MXN", 19.232F, now));
		rateRepo.save(Rate.of("GBP", 0.75705F, now));
	}

}



