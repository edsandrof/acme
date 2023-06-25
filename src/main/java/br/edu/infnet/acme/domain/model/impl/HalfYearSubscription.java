package br.edu.infnet.acme.domain.model.impl;

import br.edu.infnet.acme.domain.model.SubscriptionType;

import java.math.BigDecimal;

public class HalfYearSubscription implements SubscriptionType {
    private static final BigDecimal SUBSCRIPTION_FEE = new BigDecimal("3");

    @Override
    public BigDecimal getSubscriptionFee(BigDecimal subscriptionTotalCost) {

        return subscriptionTotalCost
                .multiply(SUBSCRIPTION_FEE)
                .divide(TOTAL_PERCENT, ROUNDING_MODE);
    }
}
