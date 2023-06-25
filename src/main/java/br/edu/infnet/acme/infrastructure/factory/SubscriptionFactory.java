package br.edu.infnet.acme.infrastructure.factory;

import br.edu.infnet.acme.domain.model.Customer;
import br.edu.infnet.acme.domain.model.Subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SubscriptionFactory {

    public static List<Subscription> getSubscriptions(List<Customer> customers) {
        LocalDateTime today = LocalDateTime.now();
        BigDecimal monthlyCost = new BigDecimal("99.98");

        return List.of(
                new Subscription(monthlyCost, today.minusMonths(3), customers.get(0)),
                new Subscription(monthlyCost, today.minusMonths(6L), today.minusMonths(2L), customers.get(1)),
                new Subscription(monthlyCost, today.minusMonths(8L), today.minusMonths(3L), customers.get(2))
        );
    }
}
