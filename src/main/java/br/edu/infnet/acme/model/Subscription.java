package br.edu.infnet.acme.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class Subscription {

    private BigDecimal monthlyCost;

    private LocalDateTime begin;

    private Optional<LocalDateTime> end;

    private Customer customer;

    public Subscription(BigDecimal monthlyCost, LocalDateTime begin, Customer customer) {
        this.monthlyCost = monthlyCost;
        this.begin = begin;
        this.end = Optional.empty();
        this.customer = customer;
    }

    public Subscription(BigDecimal monthlyCost, LocalDateTime begin, LocalDateTime end, Customer customer) {
        this(monthlyCost, begin, customer);
        this.end = Optional.of(end);
    }

    public BigDecimal getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(BigDecimal monthlyCost) {
        this.monthlyCost = monthlyCost;
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

    public Long getDuration() {
        LocalDateTime today = LocalDateTime.now();
        return ChronoUnit.MONTHS.between(begin, end.orElse(today));
    }
}
