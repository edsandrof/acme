package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.domain.model.Subscription;
import br.edu.infnet.acme.infrastructure.factory.SubscriptionFactory;

import java.util.List;

public class ExerciseList2Service {
    public static void runExerciseList2() {
        List<Subscription> subscriptions = List.of(
                SubscriptionFactory.getAnnualSubscription(),
                SubscriptionFactory.getHalfYearSubscription(),
                SubscriptionFactory.getQuarterlySubscription()
        );

        subscriptions.stream()
                .map(s -> String.format("\t> %s's subscription - total cost: %.2f, fee: %.2f", s.getCustomer().getName(), s.getTotalCost(), s.getFee()))
                .forEach(System.out::println);

    }
}
