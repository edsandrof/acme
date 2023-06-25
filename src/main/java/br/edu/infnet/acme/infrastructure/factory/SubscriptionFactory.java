package br.edu.infnet.acme.infrastructure.factory;

import br.edu.infnet.acme.domain.model.Customer;
import br.edu.infnet.acme.domain.model.Subscription;
import br.edu.infnet.acme.domain.model.SubscriptionType;
import br.edu.infnet.acme.domain.model.impl.AnnualSubscription;
import br.edu.infnet.acme.domain.model.impl.HalfYearSubscription;
import br.edu.infnet.acme.domain.model.impl.QuarterlySubscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class SubscriptionFactory {
    private static final BigDecimal MONTHLY_COST = new BigDecimal("99.98");
    private static final LocalDateTime TODAY = LocalDateTime.now();

    public static List<Subscription> getSubscriptions(List<Customer> customers, SubscriptionType subscriptionType) {

        return List.of(
                new Subscription(MONTHLY_COST, TODAY.minusMonths(3L), customers.get(0), subscriptionType),
                new Subscription(MONTHLY_COST, TODAY.minusMonths(6L), TODAY.minusMonths(2L), customers.get(1), subscriptionType),
                new Subscription(MONTHLY_COST, TODAY.minusMonths(8L), TODAY.minusMonths(3L), customers.get(2), subscriptionType)
        );
    }

    public static Subscription getAnnualSubscription() {
        return new Subscription(MONTHLY_COST, TODAY.minusMonths(3L), new Customer("Maria"), new AnnualSubscription());
    }

    public static Subscription getHalfYearSubscription() {
        return new Subscription(MONTHLY_COST, TODAY.minusMonths(6L), new Customer("Jose"), new HalfYearSubscription());
    }

    public static Subscription getQuarterlySubscription() {
        return new Subscription(MONTHLY_COST, TODAY.minusMonths(8L), new Customer("Pedro"), new QuarterlySubscription());
    }

}
