package com.albumreviews.model;

import java.math.BigDecimal;

@FunctionalInterface
public interface RatingCalculator {

	public BigDecimal calculate(Integer[] ratings);
}
