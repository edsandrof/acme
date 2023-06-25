package br.edu.infnet.acme.domain.model.impl;

import br.edu.infnet.acme.domain.model.SubscriptionType;

import java.math.BigDecimal;

public class MonthlySubscription implements SubscriptionType {

    @Override
    public BigDecimal getSubscriptionFee(BigDecimal subscriptionTotalCost) {
        return null;
    }
}
