package br.edu.infnet.acme.domain.model.impl;

import br.edu.infnet.acme.domain.model.SubscriptionType;

import java.math.BigDecimal;

public class QuarterlySubscription implements SubscriptionType {
    private static final BigDecimal SUBSCRIPTION_FEE = new BigDecimal("5");

    @Override
    public BigDecimal getSubscriptionFee(BigDecimal subscriptionTotalCost) {

        return subscriptionTotalCost
                .multiply(SUBSCRIPTION_FEE)
                .divide(TOTAL_PERCENT, ROUNDING_MODE);
    }
}
