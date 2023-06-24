package br.edu.infnet.acme;

import br.edu.infnet.acme.model.Subscription;
import br.edu.infnet.acme.model.Customer;
import br.edu.infnet.acme.model.Payment;
import br.edu.infnet.acme.model.Product;
import br.edu.infnet.acme.service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        LocalDateTime hoje = LocalDateTime.now();

        List<Payment> payments = Arrays.asList(
                new Payment(list1, hoje, customer1),
                new Payment(list2, hoje.minusDays(1L), customer2),
                new Payment(list2, hoje.minusMonths(1), customer2));

        BigDecimal custoAssinatura = new BigDecimal("99.98");

        Collection<Subscription> subscriptions = Arrays.asList(
                new Subscription(custoAssinatura, hoje.minusMonths(3), customer1),
                new Subscription(custoAssinatura, hoje.minusMonths(6L), hoje.minusMonths(2L), customer2),
                new Subscription(custoAssinatura, hoje.minusMonths(8L), hoje.minusMonths(3L), customer3)
        );


        PaymentService paymentService = new PaymentService(payments);

        System.out.println("2 - Ordene e imprima os pagamentos pela data de compra:");
        paymentService.sortAndPrint(Comparator.comparing(Payment::getPurchaseDate));

        System.out.println("3 (a) - Calcule e Imprima a soma dos valores de um pagamento com optional:");
        System.out.println("\t> Total: " + paymentService.getOptionalPaymentSum(0).orElse(BigDecimal.ZERO));

        System.out.println("3 (b) - Calcule e Imprima a soma dos valores de um pagamento com double:");
        System.out.println("\t> Total: " + paymentService.getDoublePaymentSum(0));

        System.out.println("4 - Calcule o Valor de todos os pagamentos da Lista de pagamentos:");
        System.out.println("\t> Total: " + paymentService.getAllPaymentSum());

        imprimirQuantidadeCadaProdutoVendido(payments); // 5 OK
        criandoMapClienteProduto(payments); // 6 OK
        qualClienteGastouMais(payments); // 7 OK
        quantoFoiFaturadoNoMes(payments); // 8 OK
        imprimirTempoEmMesesAssinauturaAtiva(subscriptions); // 10 OK
        imprimirTempoEntreBeginEndAssinaturas(subscriptions); // 11 OK
        calcularValorPagoEmCadaAssinaturaAteAgora(subscriptions); // 12 ok
    }

    private static void imprimirTempoEntreBeginEndAssinaturas(Collection<Subscription> subscriptions) {
        System.out.println("11 - Imprima o tempo de meses entre o start e end de todas assinaturas:");

        LocalDateTime hoje = LocalDateTime.now();

        subscriptions.stream()
                .map(subscription -> ChronoUnit.MONTHS.between(subscription.getBegin(), subscription.getEnd().orElse(hoje)))
                .forEach(meses -> System.out.println("\t> Assinatura tem " + meses + " meses"));
    }

    private static void calcularValorPagoEmCadaAssinaturaAteAgora(Collection<Subscription> subscriptions) {
        System.out.println("12 - Calcule o valor pago em cada assinatura até o momento:");

        LocalDateTime hoje = LocalDateTime.now();

        subscriptions.stream()
                .map(subscription -> subscription.getMonthlyCost().multiply(
                        new BigDecimal(ChronoUnit.MONTHS.between(subscription.getBegin(), subscription.getEnd().orElse(hoje)))))
                .forEach(total -> System.out.println("\t> Assinatura custou " + total + " até o momento"));
    }

    private static void imprimirTempoEmMesesAssinauturaAtiva(Collection<Subscription> subscriptions) {
        System.out.println("10 - Imprima o tempo em meses de alguma assinatura ainda ativa:");
        LocalDateTime hoje = LocalDateTime.now();

        long meses = subscriptions.stream()
                .filter(subscription -> subscription.getEnd().isEmpty())
                .map(a -> ChronoUnit.MONTHS.between(a.getBegin(), a.getEnd().orElse(hoje)))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Assinatura ativa não encontrada!"));

        System.out.printf("\t> Assinatura ativa há %d meses%n", meses);
    }

    private static void quantoFoiFaturadoNoMes(Collection<Payment> payments) {
        System.out.println("8 - Quanto foi faturado em um determinado mês?");

        LocalDateTime hoje = LocalDateTime.now();

        Month mes = hoje.getMonth().minus(1);
        int ano = hoje.getYear();

        BigDecimal faturamentoMes = payments.stream()
                .filter(payment -> payment.getPurchaseDate().getYear() == ano)
                .filter(payment -> payment.getPurchaseDate().getMonth().equals(mes))
                .flatMap(payment -> payment.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.printf("\t> Hoje: %s, faturamento no mês %d/%d: %.2f%n", hoje.toLocalDate(), mes.getValue(), ano, faturamentoMes);

    }

    private static void qualClienteGastouMais(Collection<Payment> payments) {
        System.out.println("7 - Qual cliente gastou mais?");

        Function<Payment, BigDecimal> reducing = payment -> payment.getProducts().stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<Customer, BigDecimal> topClientes = payments.stream()
                .collect(Collectors.groupingBy(Payment::getCustomer,
                        Collectors.reducing(BigDecimal.ZERO, reducing, BigDecimal::add)));

        Map.Entry<Customer, BigDecimal> clientEntry = topClientes.entrySet().stream()
                .max(Map.Entry.comparingByValue()).get();

        System.out.println("\t> " + clientEntry.getKey() + ", gastou " + clientEntry.getValue());
    }

    private static void criandoMapClienteProduto(Collection<Payment> payments) {
        System.out.println("6 - Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente:");

        Map<String, List<Product>> mapClienteProduto = payments.stream()
                .collect(Collectors.groupingBy(
                        payment -> payment.getCustomer().getName(),
                        Collectors.mapping(Payment::getProducts,
                                Collectors.flatMapping(List::stream, Collectors.toList())
                        )
                ));

        mapClienteProduto.entrySet().stream()
                .map(cp -> "\t> " + cp.getKey() + ": " + cp.getValue())
                .forEach(System.out::println);
    }

    private static void imprimirQuantidadeCadaProdutoVendido(Collection<Payment> payments) {
        System.out.println("5 - Imprima a quantidade de cada Produto vendido:");

        payments.stream()
                .flatMap(payment -> payment.getProducts().stream())
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()))
                .forEach((key, value) -> System.out.println("\t> produto: " + key.getName() + ", qtde: " + value));
    }

    private static void calcularEImprimiSomaValoresDeUmPagamentoComOptinal(Payment payment) {
        System.out.println("3 (a) - Calcule e Imprima a soma dos valores de um pagamento com optional:");
        Optional<BigDecimal> optional = payment.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add);
        System.out.println("\t> Total: " + optional.orElse(BigDecimal.ZERO));
    }

    private static void calcularEImprimiSomaValoresDeUmPagamentoComDouble(Payment payment) {
        System.out.println("3 (b) - Calcule e Imprima a soma dos valores de um pagamento com double:");
        double value = payment.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
        System.out.println("\t> Total: " + value);
    }

    private static void calcularEImprimirValorTodosPagamentos(Collection<Payment> payments) {
        System.out.println("4 - Calcule o Valor de todos os pagamentos da Lista de pagamentos:");

        BigDecimal total = payments.stream()
                .flatMap(payment -> payment.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\t> Total: " + total);
    }

    private static void ordernarEImprimirPagamentoPelaDataCompra(Collection<Payment> payments) {

        System.out.println("2 - Ordene e imprima os pagamentos pela data de compra:");
        payments
                .stream()
                .sorted(Comparator.comparing(Payment::getPurchaseDate))
                .forEach(System.out::println);
    }
}