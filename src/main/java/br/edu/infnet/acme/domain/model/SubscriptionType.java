package br.edu.infnet.acme.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface SubscriptionType {

    default BigDecimal getFee(BigDecimal totalCost) {
        return totalCost
                .multiply(getSubscriptionFeePercent())
                .setScale(2, RoundingMode.UP);
    }
    BigDecimal getSubscriptionFeePercent();
    int getDurationMonths();
}
