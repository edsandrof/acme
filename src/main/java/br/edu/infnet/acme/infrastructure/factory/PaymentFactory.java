package br.edu.infnet.acme.infrastructure.factory;

import br.edu.infnet.acme.domain.model.Customer;
import br.edu.infnet.acme.domain.model.Payment;
import br.edu.infnet.acme.domain.model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentFactory {

    public static List<Payment> getPayments(List<Product> products, List<Customer> customers) {
        LocalDateTime today = LocalDateTime.now();

        return List.of(
                new Payment(products.subList(0, 3), today, customers.get(0)),
                new Payment(products.subList(4, 7), today.minusDays(1L), customers.get(1)),
                new Payment(products.subList(4, 7), today.minusMonths(1), customers.get(2))
        );
    }
}
