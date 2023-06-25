package br.edu.infnet.acme.domain.model;

import java.math.BigDecimal;

public interface SubscriptionType {
    BigDecimal getSubscriptionFee(BigDecimal subscriptionTotalCost);
}
