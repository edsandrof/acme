package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.domain.model.Customer;
import br.edu.infnet.acme.domain.model.Payment;
import br.edu.infnet.acme.domain.model.Product;
import br.edu.infnet.acme.domain.model.Subscription;
import br.edu.infnet.acme.domain.model.impl.MonthlySubscription;
import br.edu.infnet.acme.infrastructure.factory.CustomerFactory;
import br.edu.infnet.acme.infrastructure.factory.PaymentFactory;
import br.edu.infnet.acme.infrastructure.factory.ProductFactory;
import br.edu.infnet.acme.infrastructure.factory.SubscriptionFactory;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static br.edu.infnet.acme.infrastructure.util.Constants.OUTPUT_TOTAL_RESULT;

@Slf4j
public class ExerciseList1Service {

    private ExerciseList1Service() {
        throw new IllegalStateException("Utility class");
    }

    public static void runExerciseList1() {

        List<Customer> customers = CustomerFactory.getCustomers();
        List<Product> products = ProductFactory.getProducts();
        List<Payment> payments = PaymentFactory.getPayments(products, customers);
        List<Subscription> subscriptions = SubscriptionFactory.getSubscriptions(customers, new MonthlySubscription());

        PaymentService paymentService = new PaymentService(payments);
        SubscriptionService subscriptionService = new SubscriptionService(subscriptions);

        log.info("2 - Ordene e imprima os pagamentos pela data de compra:");
        paymentService.sortAndPrint(Comparator.comparing(Payment::getPurchaseDate));

        log.info("3 (a) - Calcule e Imprima a soma dos valores de um pagamento com optional:");
        log.info("{} {}", OUTPUT_TOTAL_RESULT, paymentService.getOptionalPaymentSum(0).orElse(BigDecimal.ZERO));

        log.info("3 (b) - Calcule e Imprima a soma dos valores de um pagamento com double:");
        log.info("{} {}", OUTPUT_TOTAL_RESULT, paymentService.getDoublePaymentSum(0));

        log.info("4 - Calcule o Valor de todos os pagamentos da Lista de pagamentos:");
        log.info("{} {}", OUTPUT_TOTAL_RESULT, paymentService.getAllPaymentSum());

        log.info("5 - Imprima a quantidade de cada Produto vendido:");
        paymentService.getAmountProductSold()
                .forEach((key, value) -> log.info("product: {}, amount: {}", key.getName(), value));

        log.info("6 - Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente:");
        paymentService.getMapCustomerProducts()
                .forEach((customer, productList) -> log.info("{}: {}", customer, productList));

        log.info("7 - Qual cliente gastou mais?");
        paymentService.getTopCustomers().entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(top -> log.info("{} spent {}", top.getKey().getName(), top.getValue()));

        log.info("8 - Quanto foi faturado em um determinado mês?");
        LocalDateTime today = LocalDateTime.now();
        Month lastMonth = today.getMonth().minus(1);
        log.info(String.format("Today: %s, billed in %d-%02d: %.2f", today.toLocalDate(), today.getYear(), lastMonth.getValue(),
                paymentService.howMuchBilledMonth(lastMonth, today.getYear())));

        log.info("10 - Imprima o tempo em meses de alguma assinatura ainda ativa:");
        log.info("Subscription active for {} months", subscriptionService.getHowManyActiveSubscriptionMonths().orElse(0L));

        log.info("11 - Imprima o tempo de meses entre o start e end de todas assinaturas:");
        subscriptionService.printCustomerSubscriptionDurations();

        log.info("12 - Calcule o valor pago em cada assinatura até o momento:");
        subscriptionService.printCustomerTotalCost();
    }
}