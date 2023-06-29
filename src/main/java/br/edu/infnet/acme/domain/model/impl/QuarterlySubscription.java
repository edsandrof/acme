package br.edu.infnet.acme.domain.model.impl;

import br.edu.infnet.acme.domain.model.SubscriptionType;
import br.edu.infnet.acme.infrastructure.util.Constants;

import java.math.BigDecimal;

public class QuarterlySubscription implements SubscriptionType {

    @Override
    public BigDecimal getSubscriptionFeePercent() {
        return new BigDecimal("0.05");
    }

    @Override
    public long getDurationMonths() {
        return Constants.THREE_MONTHS;
    }
}
