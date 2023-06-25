package br.edu.infnet.acme.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Subscription {

    private final BigDecimal monthlyCost;

    private final LocalDateTime begin;

    private Optional<LocalDateTime> end;

    private final Customer customer;

    private SubscriptionType type;

    public Subscription(BigDecimal monthlyCost, LocalDateTime begin, Customer customer, SubscriptionType type) {
        this.monthlyCost = monthlyCost;
        this.begin = begin;
        this.end = Optional.empty();
        this.customer = customer;
        this.type = type;
    }

    public Subscription(BigDecimal monthlyCost, LocalDateTime begin, LocalDateTime end, Customer customer, SubscriptionType type) {
        this(monthlyCost, begin, customer, type);
        this.end = Optional.of(end);
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public Optional<LocalDateTime> getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = Optional.of(end);
    }

    public Customer getCustomer() {
        return customer;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public Long getDuration() {
        LocalDateTime today = LocalDateTime.now();
        return ChronoUnit.MONTHS.between(begin, end.orElse(today));
    }

    public BigDecimal getTotalCost() {
        return getMonthlyCost().multiply(new BigDecimal(getDuration()));
    }

    public BigDecimal getFee() {
        return type.getSubscriptionFee(getTotalCost());
    }
}
