package com.b139.foodmate;
//Unit names
public enum QuantityUnit {
	GRAMS("g"), MILLILITRES("ml"), PIECES("pcs");
	private final String UNIT_TEXT;

	private QuantityUnit(String s)
	{
		UNIT_TEXT = s;
	}

	@Override
	public String toString() {
		return this.UNIT_TEXT;
	}
}
