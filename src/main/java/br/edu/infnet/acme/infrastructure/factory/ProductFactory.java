package br.edu.infnet.acme.infrastructure.factory;

import br.edu.infnet.acme.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public class ProductFactory {

    public static List<Product> getProducts() {
        return List.of(
                new Product("Music 11111", null, new BigDecimal("10.0")),
                new Product("Video 22222", null, new BigDecimal("20.0")),
                new Product("Image 33333", null, new BigDecimal("30.0")),
                new Product("Music 44444", null, new BigDecimal("40.0")),

                new Product("Video 55555", null, new BigDecimal("5.0")),
                new Product("Image 66666", null, new BigDecimal("6.0")),
                new Product("Music 77777", null, new BigDecimal("7.0")),
                new Product("Video 88888", null, new BigDecimal("8.0"))
        );
    }
}
