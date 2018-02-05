package io.manasobi.messaging.service;

import io.manasobi.messaging.annotation.ToUpper;
import io.manasobi.messaging.domain.CurrencyConversion;
import io.manasobi.messaging.domain.CurrencyExchange;
import io.manasobi.messaging.domain.Rate;
import io.manasobi.messaging.repo.RateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Service
public class CurrencyConversionService {
	
	@Autowired
	private RateRepo rateRepo;
	
	public CurrencyConversion convertFromTo(@ToUpper String base, @ToUpper String code, Float amount) throws Exception {

	    LocalDate now = LocalDate.now();

        Rate baseRate = Rate.of(CurrencyExchange.BASE_CODE, 1.0F, now);
        Rate codeRate = Rate.of(CurrencyExchange.BASE_CODE, 1.0F, now);

		if (!CurrencyExchange.BASE_CODE.equals(base)) {
            baseRate = rateRepo.findByDateAndCode(now, base);
        }

		if (!CurrencyExchange.BASE_CODE.equals(code)) {
            codeRate = rateRepo.findByDateAndCode(now, code);
        }

		if(null == codeRate || null == baseRate)
			throw new Exception("Bad Code Base.");
		
		return new CurrencyConversion(base, code, amount, (codeRate.getRate()/baseRate.getRate()) * amount);
	}
	
	public Rate[] calculateByCode(@ToUpper String code, LocalDate date) throws Exception {

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
			             .map(n -> Rate.of(n.getCode(), n.getRate() / baseRate.getRate(), date)),
                    Stream.of(Rate.of(CurrencyExchange.BASE_CODE, 1 / baseRate.getRate(), date))
                ).toArray(size -> new Rate[size]);
	}
	
	public void saveRates(Rate[] rates, LocalDate date) {

        List<Rate> rateList = CollectionUtils.arrayToList(rates);

        rateList.forEach(rate -> rateRepo.save(Rate.of(rate.getCode(), rate.getRate(), date)));
	}
	
}
