package io.manasobi.service;

import io.manasobi.annotation.ToUpper;
import io.manasobi.domain.CurrencyConversion;
import io.manasobi.domain.CurrencyExchange;
import io.manasobi.domain.Rate;
import io.manasobi.repo.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CurrencyConversionService {
	
	@Autowired
	private RateRepo rateRepo;
	
	public CurrencyConversion convertFromTo(@ToUpper String base, @ToUpper String code, Float amount) throws Exception {

		Rate baseRate = new Rate(CurrencyExchange.BASE_CODE, 1.0F, new Date());
		Rate codeRate = new Rate(CurrencyExchange.BASE_CODE, 1.0F, new Date());
		
		if (!CurrencyExchange.BASE_CODE.equals(base)) {
            baseRate = rateRepo.findByDateAndCode(new Date(), base);
        }

		if (!CurrencyExchange.BASE_CODE.equals(code)) {
            codeRate = rateRepo.findByDateAndCode(new Date(), code);
        }

		if(null == codeRate || null == baseRate)
			throw new Exception("Bad Code Base.");
		
		return new CurrencyConversion(base, code, amount, (codeRate.getRate()/baseRate.getRate()) * amount);
	}
	
	public Rate[] calculateByCode(@ToUpper String code, Date date) throws Exception {

		List<Rate> rates = rateRepo.findByDate(date);

		if (code.equals(CurrencyExchange.BASE_CODE)) {
            return rates.toArray(new Rate[0]);
        }

		Rate baseRate = rates.stream()
			                 .filter(rate -> rate.getCode().equals(code))
                             .findFirst().orElse(null);

		if (null == baseRate) {
            throw new Exception("Bad Base Code");
        }

		return Stream.concat(
                    rates.stream()
			             .filter(n -> !n.getCode().equals(code))
			             .map(n -> new Rate(n.getCode(), n.getRate() / baseRate.getRate(), date)),
                    Stream.of(new Rate(CurrencyExchange.BASE_CODE, 1 / baseRate.getRate(), date))
                ).toArray(size -> new Rate[size]);
	}
	
	public void saveRates(Rate[] rates, Date date) {
		Arrays.stream(rates)
              .forEach(rate -> rateRepo.save(new Rate(rate.getCode(), rate.getRate(), date)));
	}
	
}
