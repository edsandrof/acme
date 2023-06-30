package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.domain.model.Subscription;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SubscriptionService {
    private final List<Subscription> subscriptions;

    public SubscriptionService(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Optional<Long> getHowManyActiveSubscriptionMonths() {
        return subscriptions.stream()
                .filter(subscription -> subscription.getEnd().isEmpty())
                .map(Subscription::getDuration)
                .findAny();
    }

    public void printCustomerSubscriptionDurations() {
        LocalDateTime today = LocalDateTime.now();

        subscriptions.stream()
                .map(s -> String.format("%s's subscription duration: %d months (%s until %s)",
                        s.getCustomer().getName(), s.getDuration(), s.getBegin().toLocalDate(), s.getEnd().orElse(today).toLocalDate()))
                .forEach(log::info);

    }

    public void printCustomerTotalCost() {
        subscriptions.stream()
                .map(s -> String.format("%s's subscription cost %.2f (%.2f monthly)", s.getCustomer().getName(), s.getTotalCost(), s.getMonthlyCost()))
                .forEach(log::info);
    }

    public void printFeeCalc() {
        subscriptions.stream()
                .map(s -> String.format("%s's subscription - total cost: %.2f, fee cost: %.2f", s.getCustomer().getName(), s.getTotalCost(), s.getFee()))
                .forEach(log::info);
    }

    public void printPaymentOverdue() {
        subscriptions.stream()
                .map(s -> String.format("Is the %s's payment overdue? [%b]", s.getCustomer().getName(), s.isPaymentOverdue()))
                .forEach(log::info);
    }
}
