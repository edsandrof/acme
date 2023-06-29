package br.edu.infnet.acme.domain.model.impl;

import br.edu.infnet.acme.domain.model.SubscriptionType;

import java.math.BigDecimal;

public class QuarterlySubscription implements SubscriptionType {

    @Override
    public BigDecimal getSubscriptionFeePercent() {
        return new BigDecimal("0.05");
    }

    }
}
