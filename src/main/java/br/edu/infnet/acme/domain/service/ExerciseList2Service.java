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

        SubscriptionService subscriptionService = new SubscriptionService(subscriptions);

        System.out.println("1 - Crie 3 tipos de assinatura, anual, semestral e trimestral e um");
        System.out.println("    método para calcular uma taxa para cada assinatura:");
        subscriptionService.printFeeCalc();




    }
}
