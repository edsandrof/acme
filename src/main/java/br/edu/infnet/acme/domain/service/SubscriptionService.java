package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.domain.model.Subscription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
                .map(s -> String.format("\t> %s's subscription duration: %d months (%s until %s)",
                        s.getCustomer().getName(), s.getDuration(), s.getBegin().toLocalDate(), s.getEnd().orElse(today).toLocalDate()))
                .forEach(System.out::println);

    }

    public void printCustomerTotalCost() {
        subscriptions.stream()
                .map(s -> String.format("\t> %s's subscription cost %.2f (%.2f monthly)", s.getCustomer().getName(), s.getTotalCost(), s.getMonthlyCost()))
                .forEach(System.out::println);
    }
}
