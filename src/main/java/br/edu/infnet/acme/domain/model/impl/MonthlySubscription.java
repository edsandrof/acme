package br.edu.infnet.acme.domain.model.impl;

import br.edu.infnet.acme.domain.model.SubscriptionType;
import br.edu.infnet.acme.infrastructure.util.Constants;

import java.math.BigDecimal;

public class MonthlySubscription implements SubscriptionType {

    @Override
    public BigDecimal getSubscriptionFeePercent() {
        return BigDecimal.ZERO;
    }

    @Override
    public long getDurationMonths() {
        return Constants.ONE_MONTH;
    }
}
