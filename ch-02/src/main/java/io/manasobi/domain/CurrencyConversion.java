package io.manasobi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CurrencyConversion {

	private String base;
	private String code;
	private float amount;
	private float total;
	
	public CurrencyConversion(String base, String code, float amount, float total) {
		this.base = base;
		this.code = code;
		this.amount = amount;
		this.total = total;
	}

}
