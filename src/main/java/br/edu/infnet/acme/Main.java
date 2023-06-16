package br.edu.infnet.acme;

import br.edu.infnet.acme.model.Assinatura;
import br.edu.infnet.acme.model.Cliente;
import br.edu.infnet.acme.model.Pagamento;
import br.edu.infnet.acme.model.Produto;
import br.edu.infnet.acme.service.FormatadorPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Produto> list1 = Arrays.asList(
                new Produto(1, "Teste 1", null, new BigDecimal("10.0")),
                new Produto(2, "Teste 2", null, new BigDecimal("20.0")),
                new Produto(3, "Teste 2", null, new BigDecimal("30.0")),
                new Produto(4, "Teste 3", null, new BigDecimal("40.0")));

        List<Produto> list2 = Arrays.asList(
                new Produto(5, "Teste 1", null, new BigDecimal("5.0")),
                new Produto(6, "Teste 2", null, new BigDecimal("6.0")),
                new Produto(7, "Teste 2", null, new BigDecimal("7.0")),
                new Produto(8, "Teste 3", null, new BigDecimal("8.0")));


        Cliente cliente1 = new Cliente(1, "Cliente de Teste");
        Cliente cliente2 = new Cliente(2, "Consumidor de Teste");

        List<Pagamento> pagamentos = Arrays.asList(
                new Pagamento(1, list1, LocalDateTime.now(), cliente1),
                new Pagamento(2, list2, LocalDateTime.now().minusDays(1L), cliente2),
                new Pagamento(3, list2, LocalDateTime.now().minusMonths(1), cliente2));

        // 1 OK

        ordernarEImprimirPagamentoPelaDataCompra(pagamentos); // 2 OK
        calcularEImprimiSomaValoresDeUmPagamentoComOptinal(pagamentos.get(0)); //3 OK
        calcularEImprimiSomaValoresDeUmPagamentoComDouble(pagamentos.get(0)); // 3 OK
        calcularEImprimirValorTodosPagamentos(pagamentos); // 4 OK
        imprimirQuantidadeCadaProdutoVendido(pagamentos); // 5
//        criandoMapClienteProduto(pagamentos); // 6 OK
//        qualClienteGastouMais(pagamentos); // 7
//        quantoFoiFaturadoNoMes(pagamentos, LocalDateTime.now()); // 8
//        criar3AssinaturasDe99_98Sendo2Encerradas(); // 9 OK
//        imprimirTempoEmMesesAssinauturaAtiva(assinaturas); // 10 OK
//        imprimirTempoEntreBeginEndAssinaturas(assinaturas);// 11
//        calcularValorPagoEmCadaAssinaturaAteAgora(assinaturas); // 12
    }

    private static void imprimirTempoEntreBeginEndAssinaturas(Collection<Assinatura> assinaturas) {
    }

    private static void calcularValorPagoEmCadaAssinaturaAteAgora(Collection<Assinatura> assinaturas) {
    }

    private static void imprimirTempoEmMesesAssinauturaAtiva(Collection<Assinatura> assinaturas) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++ Tempo Assinatura Ativa ++++++++++++++");
        assinaturas.stream().filter(assinatura -> assinatura.getEnd() == null).limit(1).forEach(a -> {
            long months = ChronoUnit.MONTHS.between(a.getBegin(), LocalDateTime.now());
            System.out.println(String.format(Locale.getDefault(), "Tempo em meses da assinatura ativa: %d", months));
        });
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static Collection<Assinatura> criar3AssinaturasDe99_98Sendo2Encerradas() {
        return Arrays.asList(
                new Assinatura(1, new BigDecimal("99.98"), LocalDateTime.now(), null),
                new Assinatura(2, new BigDecimal("99.98"), LocalDateTime.now(), LocalDateTime.now(), null),
                new Assinatura(3, new BigDecimal("99.98"), LocalDateTime.now(), LocalDateTime.now(), null));
    }

    private static void quantoFoiFaturadoNoMes(Collection<Pagamento> pagamentos, LocalDateTime now) {
    }

    private static void qualClienteGastouMais(Collection<Pagamento> pagamentos) {
    }

    private static void criandoMapClienteProduto(Collection<Pagamento> pagamentos) {
        Map<String, List<Produto>> mapClienteProdutos = new HashMap<String, List<Produto>>();
        pagamentos.forEach(pag -> {
            mapClienteProdutos.put(pag.getCliente().getNome(), pag.getProdutos().stream().toList());
        });
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("++++++++++++ MAP CLIENTE/PRODUT ++++++++++++++");
        mapClienteProdutos.forEach((cliente, produtos) -> {
            System.out.println(cliente);
            produtos.forEach(System.out::println);
        });
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
    }

    private static void imprimirQuantidadeCadaProdutoVendido(Collection<Pagamento> pagamentos) {
//        pagamentos.stream().flatMap(List::stream).collect(Collectors.groupingBy(produto -> produto, Collectors.counting()));
    }

    private static void calcularEImprimiSomaValoresDeUmPagamentoComOptinal(Pagamento pagamento) {
        System.out.println("3 (a) - Calcule e Imprima a soma dos valores de um pagamento com optional:");
        Optional<BigDecimal> optional = pagamento.getProdutos()
                .stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal::add);
        System.out.println("\t> Total: " + optional.orElse(BigDecimal.ZERO));
    }

    private static void calcularEImprimiSomaValoresDeUmPagamentoComDouble(Pagamento pagamento) {
        System.out.println("3 (b) - Calcule e Imprima a soma dos valores de um pagamento com double:");
        double value = pagamento.getProdutos()
                .stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
        System.out.println("\t> Total: " + value);
    }

    private static void calcularEImprimirValorTodosPagamentos(Collection<Pagamento> pagamentos) {
        System.out.println("4 - Calcule o Valor de todos os pagamentos da Lista de pagamentos:");

        BigDecimal total = pagamentos.stream()
                .flatMap(pagamento -> pagamento.getProdutos().stream())
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\t> Total: " + total);
    }

    private static void ordernarEImprimirPagamentoPelaDataCompra(Collection<Pagamento> pagamentos) {
        FormatadorPagamento formatador = new FormatadorPagamento(new Locale("pt", "BR"));

        System.out.println("2 - Ordene e imprima os pagamentos pela data de compra:");
        pagamentos
                .stream()
                .sorted(Comparator.comparing(Pagamento::getDataCompra))
                .forEach(payment -> System.out.println(formatador.format(payment)));
    }
}