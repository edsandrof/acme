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
        Cliente cliente3 = new Cliente(3, "Consumidor de Produção");

        LocalDateTime hoje = LocalDateTime.now();

        List<Pagamento> pagamentos = Arrays.asList(
                new Pagamento(1, list1, hoje, cliente1),
                new Pagamento(2, list2, hoje.minusDays(1L), cliente2),
                new Pagamento(3, list2, hoje.minusMonths(1), cliente2));

        BigDecimal custoAssinatura = new BigDecimal("99.98");

        Collection<Assinatura> assinaturas = Arrays.asList(
                new Assinatura(1, custoAssinatura, hoje.minusMonths(3), cliente1),
                new Assinatura(2, custoAssinatura, hoje.minusMonths(6L), hoje.minusMonths(2L), cliente2),
                new Assinatura(3, custoAssinatura, hoje.minusMonths(8L), hoje.minusMonths(3L), cliente3)
        );

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
        imprimirTempoEmMesesAssinauturaAtiva(assinaturas); // 10 OK
        imprimirTempoEntreBeginEndAssinaturas(assinaturas); // 11 OK
//        calcularValorPagoEmCadaAssinaturaAteAgora(assinaturas); // 12
    }

    private static void imprimirTempoEntreBeginEndAssinaturas(Collection<Assinatura> assinaturas) {
        System.out.println("11 - Imprima o tempo de meses entre o start e end de todas assinaturas:");
        FormatadorPagamento formatador = new FormatadorPagamento(new Locale("pt", "BR"));

        LocalDateTime hoje = LocalDateTime.now();

        assinaturas.stream()
                .map(assinatura -> ChronoUnit.MONTHS.between(assinatura.getBegin(), assinatura.getEnd().orElse(hoje)))
                .forEach(meses -> System.out.println("\t> Assinatura tem " + meses + " meses"));
    }

    private static void calcularValorPagoEmCadaAssinaturaAteAgora(Collection<Assinatura> assinaturas) {
    }

    private static void imprimirTempoEmMesesAssinauturaAtiva(Collection<Assinatura> assinaturas) {
        System.out.println("10 - Imprima o tempo em meses de alguma assinatura ainda ativa:");
        LocalDateTime hoje = LocalDateTime.now();

        long meses = assinaturas.stream()
                .filter(assinatura -> assinatura.getEnd().isEmpty())
                .map(a -> ChronoUnit.MONTHS.between(a.getBegin(), a.getEnd().orElse(hoje)))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Assinatura ativa não encontrada!"));

        System.out.printf("\t> Assinatura ativa há %d meses%n", meses);
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