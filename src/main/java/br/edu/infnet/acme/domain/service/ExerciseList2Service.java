package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.application.exception.PaymentOverdueException;
import br.edu.infnet.acme.domain.model.Product;
import br.edu.infnet.acme.domain.model.Subscription;
import br.edu.infnet.acme.infrastructure.factory.ProductFactory;
import br.edu.infnet.acme.infrastructure.factory.SubscriptionFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ExerciseList2Service {

    private ExerciseList2Service() {
        throw new IllegalStateException("Utility class");
    }

    public static void runExerciseList2() {
        List<Subscription> subscriptions = List.of(
                SubscriptionFactory.getAnnualSubscription(),
                SubscriptionFactory.getHalfYearSubscription(),
                SubscriptionFactory.getQuarterlySubscription()
        );

        SubscriptionService subscriptionService = new SubscriptionService(subscriptions);

        log.info("1 - Crie 3 tipos de assinatura, anual, semestral e trimestral e um método para calcular uma taxa para cada assinatura:");
        subscriptionService.printFeeCalc();

        log.info("2 - Crie um atributo para controlar assinaturas com atraso de pagamento:");
        subscriptionService.printPaymentOverdue();
        subscriptionService.getSubscriptions().get(0).pay();
        subscriptionService.getSubscriptions().get(1).pay();
        subscriptionService.printPaymentOverdue();

        log.info("3 - Crie um mecanismo para validar clientes que tentarem fazer compras com assinatura em atraso e não deixá-los comprar.");
        List<Product> products = ProductFactory.getProducts();

        try {
            subscriptionService.getSubscriptions().get(0).rentMovie(products.get(1));
            subscriptionService.getSubscriptions().get(1).rentMovie(products.get(4));

            // exception
            subscriptionService.getSubscriptions().get(2).rentMovie(products.get(7));
        } catch (PaymentOverdueException e) {
            log.error("Error in rent a movie: {}", e.getMessage());
        }
    }
}
