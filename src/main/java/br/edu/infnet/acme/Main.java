package br.edu.infnet.acme;

import br.edu.infnet.acme.model.Customer;
import br.edu.infnet.acme.model.Payment;
import br.edu.infnet.acme.model.Product;
import br.edu.infnet.acme.model.Subscription;
import br.edu.infnet.acme.service.PaymentService;
import br.edu.infnet.acme.service.SubscriptionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Product> list1 = Arrays.asList(
                new Product("Music 11111", null, new BigDecimal("10.0")),
                new Product("Video 22222", null, new BigDecimal("20.0")),
                new Product("Image 33333", null, new BigDecimal("30.0")),
                new Product("Music 44444", null, new BigDecimal("40.0")));

        List<Product> list2 = Arrays.asList(
                new Product("Video 55555", null, new BigDecimal("5.0")),
                new Product("Image 66666", null, new BigDecimal("6.0")),
                new Product("Music 77777", null, new BigDecimal("7.0")),
                new Product("Video 88888", null, new BigDecimal("8.0")));


        Customer customer1 = new Customer("Maria");
        Customer customer2 = new Customer("Jose");
        Customer customer3 = new Customer("Pedro");

        LocalDateTime today = LocalDateTime.now();

        List<Payment> payments = Arrays.asList(
                new Payment(list1, today, customer1),
                new Payment(list2, today.minusDays(1L), customer2),
                new Payment(list2, today.minusMonths(1), customer2));

        BigDecimal custoAssinatura = new BigDecimal("99.98");

        List<Subscription> subscriptions = Arrays.asList(
                new Subscription(custoAssinatura, today.minusMonths(3), customer1),
                new Subscription(custoAssinatura, today.minusMonths(6L), today.minusMonths(2L), customer2),
                new Subscription(custoAssinatura, today.minusMonths(8L), today.minusMonths(3L), customer3)
        );


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
                .forEach((customer, products) -> System.out.println("\t> " + customer + ": " + products));

        System.out.println("7 - Qual cliente gastou mais?");
        paymentService.getTopCustomers().entrySet().stream().max(Map.Entry.comparingByValue())
                .ifPresent(top -> System.out.println("\t> " + top.getKey() + ", gastou " + top.getValue()));

        System.out.println("8 - Quanto foi faturado em um determinado mês?");
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