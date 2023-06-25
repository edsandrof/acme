package br.edu.infnet.acme.infrastructure.factory;

import br.edu.infnet.acme.domain.model.Customer;

import java.util.List;

public class CustomerFactory {
    public static List<Customer> getCustomers() {
        return List.of(
                new Customer("Maria"),
                new Customer("Jose"),
                new Customer("Pedro")
        );
    }
}
