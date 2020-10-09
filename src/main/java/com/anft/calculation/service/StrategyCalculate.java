package com.anft.calculation.service;

import java.math.BigDecimal;
import java.util.Date;

public interface StrategyCalculate {

    BigDecimal doCalculate(String rule, Date startDate, Date endDate);
}
