package io.manasobi.messaging.listener;

import io.manasobi.messaging.event.CurrencyConversionEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CurrencyConversionEventListener implements ApplicationListener<CurrencyConversionEvent> {

	private static final String DASH_LINE = "===================================";
	private static final String NEXT_LINE = "\n";

	@Override
	public void onApplicationEvent(CurrencyConversionEvent event) {

		Object obj = event.getSource();

		StringBuilder str = new StringBuilder(NEXT_LINE);
		str.append(DASH_LINE);
		str.append(NEXT_LINE);
		str.append("  Class: " + obj.getClass().getSimpleName());
		str.append(NEXT_LINE);
		str.append("Message: " + event.getMessage());
		str.append(NEXT_LINE);
		str.append("  Value: " + event.getConversion());
		str.append(NEXT_LINE);
		str.append(DASH_LINE);

		log.error(str.toString());
	}

}
