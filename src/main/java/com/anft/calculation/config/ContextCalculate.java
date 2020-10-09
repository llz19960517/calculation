package com.anft.calculation.config;


import com.anft.calculation.service.StrategyCalculate;

import java.math.BigDecimal;
import java.util.Date;

public class ContextCalculate {
   private StrategyCalculate strategyInvoice;
 
   public ContextCalculate(StrategyCalculate strategyInvoice){
      this.strategyInvoice = strategyInvoice;
   }

   public BigDecimal executeStrategyCalculate(String rule, Date startTime, Date endTime){
      return strategyInvoice.doCalculate(rule,startTime,endTime);
   }
}