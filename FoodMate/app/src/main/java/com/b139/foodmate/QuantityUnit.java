package com.b139.foodmate;
//Unit names
public enum QuantityUnit {
	GRAMS("g"), MILLILITRES("ml"), PIECES("pcs");
	private final String unitText;

	private QuantityUnit(String s)
	{
		unitText = s;
	}

	@Override
	public String toString() {
		return this.unitText;
	}
}
