package br.edu.infnet.acme;

import br.edu.infnet.acme.domain.model.Customer;
import br.edu.infnet.acme.domain.model.Payment;
import br.edu.infnet.acme.domain.model.Product;
import br.edu.infnet.acme.domain.model.Subscription;
import br.edu.infnet.acme.domain.service.PaymentService;
import br.edu.infnet.acme.domain.service.SubscriptionService;
import br.edu.infnet.acme.infrastructure.factory.CustomerFactory;
import br.edu.infnet.acme.infrastructure.factory.PaymentFactory;
import br.edu.infnet.acme.infrastructure.factory.ProductFactory;
import br.edu.infnet.acme.infrastructure.factory.SubscriptionFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Customer> customers = CustomerFactory.getCustomers();
        List<Product> products = ProductFactory.getProducts();
        List<Payment> payments = PaymentFactory.getPayments(products, customers);
        List<Subscription> subscriptions = SubscriptionFactory.getSubscriptions(customers);

        PaymentService paymentService = new PaymentService(payments);
        SubscriptionService subscriptionService = new SubscriptionService(subscriptions);

        System.out.println("2 - Ordene e imprima os pagamentos pela data de compra:");
        paymentService.sortAndPrint(Comparator.comparing(Payment::getPurchaseDate));

        System.out.println("3 (a) - Calcule e Imprima a soma dos valores de um pagamento com optional:");
        System.out.println("\t> Total: " + paymentService.getOptionalPaymentSum(0).orElse(BigDecimal.ZERO));

        System.out.println("3 (b) - Calcule e Imprima a soma dos valores de um pagamento com double:");
        System.out.println("\t> Total: " + paymentService.getDoublePaymentSum(0));

        System.out.println("4 - Calcule o Valor de todos os pagamentos da Lista de pagamentos:");
        System.out.println("\t> Total: " + paymentService.getAllPaymentSum());

        System.out.println("5 - Imprima a quantidade de cada Produto vendido:");
        paymentService.getAmountProductSold()
                .forEach((key, value) -> System.out.println("\t> product: " + key.getName() + ", amount: " + value));

        System.out.println("6 - Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente:");
        paymentService.getMapCustomerProducts()
                .forEach((customer, productList) -> System.out.println("\t> " + customer + ": " + productList));

        System.out.println("7 - Qual cliente gastou mais?");
        paymentService.getTopCustomers().entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(top -> System.out.println("\t> " + top.getKey().getName() + " spent " + top.getValue()));

        System.out.println("8 - Quanto foi faturado em um determinado mês?");
        LocalDateTime today = LocalDateTime.now();
        Month lastMonth = today.getMonth().minus(1);
        System.out.printf("\t> Today: %s, billed in %d-%02d: %.2f%n", today.toLocalDate(), today.getYear(), lastMonth.getValue(),
                paymentService.howMuchBilledMonth(lastMonth, today.getYear()));

        System.out.println("10 - Imprima o tempo em meses de alguma assinatura ainda ativa:");
        System.out.printf("\t> Subscription active for %d months%n", subscriptionService.getHowManyActiveSubscriptionMonths().orElse(0L));

        System.out.println("11 - Imprima o tempo de meses entre o start e end de todas assinaturas:");
        subscriptionService.printCustomerSubscriptionDurations();

        System.out.println("12 - Calcule o valor pago em cada assinatura até o momento:");
        subscriptionService.printCustomerTotalCost();
    }
}