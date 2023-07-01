package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.domain.model.Customer;
import br.edu.infnet.acme.domain.model.Payment;
import br.edu.infnet.acme.domain.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;


class PaymentServiceTest {

    private PaymentService paymentService;
    private static List<Payment> payments;

    @BeforeAll
    static void start() {
        LocalDateTime now = LocalDateTime.of(2023, 7, 1, 0, 30);

        List<Product> products = Arrays.asList(
                new Product("Music", null, new BigDecimal("5.0")),
                new Product("Movie", null, new BigDecimal("10.0"))
        );

        Customer maria = new Customer("Maria");
        Customer jose = new Customer("Jose");

        payments = Arrays.asList(
                new Payment(products, now, maria),
                new Payment(products.subList(0, 1), now.minusHours(5), jose)
        );
    }

    @BeforeEach
    void setUp() {
        this.paymentService = new PaymentService(payments);
    }

    @Test
    void testGetOptionalPaymentSum() {
        Optional<BigDecimal> result = paymentService.getOptionalPaymentSum(0);
        Assertions.assertEquals(Optional.of(new BigDecimal("15.0")), result);
    }

    @Test
    void testGetDoublePaymentSum() {
        double result = paymentService.getDoublePaymentSum(0);
        Assertions.assertEquals(15, result);
    }

    @Test
    void testGetAllPaymentSum() {
        BigDecimal result = paymentService.getAllPaymentSum();
        Assertions.assertEquals(new BigDecimal("20.0"), result);
    }

    @Test
    void testGetAmountProductSold() {
        Product product = new Product("Music", null, new BigDecimal("5.0"));

        Map<Product, Long> result = paymentService.getAmountProductSold();

        Assertions.assertTrue(result.containsKey(product));
        Assertions.assertEquals(2L, result.get(product));
    }

    @Test
    void testGetMapCustomerProducts() {
        String customerExpected = "Jose";
        List<Product> productsExpected = List.of(new Product("Music", null, new BigDecimal("5.0")));

        Map<String, List<Product>> result = paymentService.getMapCustomerProducts();

        Assertions.assertTrue(result.containsKey(customerExpected));
        Assertions.assertEquals(productsExpected, result.get(customerExpected));
    }

    @Test
    void testHowMuchBilledMonth() {
        BigDecimal result = paymentService.howMuchBilledMonth(Month.JUNE, 2023);
        Assertions.assertEquals(new BigDecimal("5.0"), result);
    }
}
