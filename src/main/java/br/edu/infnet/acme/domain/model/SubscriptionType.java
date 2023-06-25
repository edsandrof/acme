package br.edu.infnet.acme.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface SubscriptionType {
    BigDecimal TOTAL_PERCENT = new BigDecimal("100");
    RoundingMode ROUNDING_MODE = RoundingMode.UP;

    BigDecimal getSubscriptionFee(BigDecimal subscriptionTotalCost);
}
