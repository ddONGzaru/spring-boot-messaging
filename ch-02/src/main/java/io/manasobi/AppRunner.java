package io.manasobi;

import java.util.Date;

import io.manasobi.domain.Rate;
import io.manasobi.repo.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppRunner implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class, args);
	}

	@Autowired
	private RateRepo rateRepo;

	@Override
	public void run(ApplicationArguments args) {

		rateRepo.save(new Rate("EUR",0.88857F,new Date()));
		rateRepo.save(new Rate("JPY",102.17F,new Date()));
		rateRepo.save(new Rate("MXN",19.232F,new Date()));
		rateRepo.save(new Rate("GBP",0.75705F,new Date()));
	}
	
}



