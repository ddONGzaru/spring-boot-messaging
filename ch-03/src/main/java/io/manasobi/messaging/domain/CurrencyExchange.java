package io.manasobi.messaging.domain;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class CurrencyExchange {

	public static final String BASE_CODE = "USD";

	@NonNull
	private String base;

    @NonNull
	private String date;

    @NonNull
	private Rate[] rates;
	
	/*public CurrencyExchange(String base, String date, Rate[] rates) {
		super();
		this.base = base;
		this.date = date;
		this.rates = rates;
	}*/

}
