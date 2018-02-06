package io.manasobi.messaging.controller;

import io.geronimo.DateUtils;
import io.manasobi.messaging.domain.CurrencyConversion;
import io.manasobi.messaging.domain.CurrencyExchange;
import io.manasobi.messaging.domain.Rate;
import io.manasobi.messaging.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	private CurrencyConversionService currencyConversionService;
	
	@GetMapping("/latest")
	public ResponseEntity<CurrencyExchange> getLatest(@RequestParam(name = "base", defaultValue = CurrencyExchange.BASE_CODE) String base) throws Exception {

		return new ResponseEntity(findCurrencyExchange(base, LocalDate.now()), HttpStatus.OK);
	}
	
	@GetMapping("/{date}")
	public ResponseEntity<CurrencyExchange> getByDate(@RequestParam(name = "base", defaultValue = CurrencyExchange.BASE_CODE) String base,
			@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date) throws Exception {

		return new ResponseEntity(findCurrencyExchange(base, date), HttpStatus.OK);
	}

	private CurrencyExchange findCurrencyExchange(String base, LocalDate date) throws Exception {

		Rate[] rates = currencyConversionService.calculateByCode(base, date);

		return CurrencyExchange.of("base", DateUtils.now(DateUtils.Pattern.DATE), rates);
	}
	
	@GetMapping("/{amount}/{base}/to/{code}")
	public ResponseEntity<CurrencyConversion> conversion(@PathVariable("amount") Float amount, @PathVariable("base") String base, @PathVariable("code") String code) throws Exception {

		CurrencyConversion conversionResult = currencyConversionService.convertFromTo(base, code, amount);

		return new ResponseEntity(conversionResult,HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<CurrencyExchange> addNewRates(@RequestBody CurrencyExchange currencyExchange) throws Exception {


	    LocalDate date = DateUtils.toLocalDate(currencyExchange.getDate(), DateUtils.Pattern.DATE);

        currencyConversionService.saveRates(currencyExchange.getRates(), date);

		try{
			final Rate[] rates = currencyExchange.getRates();
		}catch(Exception ex){

			log.error(ex.getMessage());
			throw ex;
		}

		return new ResponseEntity(HttpStatus.CREATED);
	}
	
}
