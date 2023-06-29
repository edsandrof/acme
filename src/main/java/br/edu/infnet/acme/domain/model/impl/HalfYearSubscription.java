package br.edu.infnet.acme.domain.model.impl;

import br.edu.infnet.acme.domain.model.SubscriptionType;
import br.edu.infnet.acme.infrastructure.util.Constants;

import java.math.BigDecimal;

public class HalfYearSubscription implements SubscriptionType {

    @Override
    public BigDecimal getSubscriptionFeePercent() {
        return new BigDecimal("0.03");
    }

    @Override
    public long getDurationMonths() {
        return Constants.SIX_MONTHS;
    }
}
