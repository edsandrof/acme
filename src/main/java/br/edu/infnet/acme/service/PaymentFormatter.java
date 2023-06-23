package br.edu.infnet.acme.service;

import br.edu.infnet.acme.model.Payment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PaymentFormatter {

    private final Locale locale;

    public PaymentFormatter(Locale locale) {
        this.locale = locale;
    }

    public String format(Payment payment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", locale);
        return String.format("\t> cliente: %s, data de compra: %s",
                payment.getCliente().getName(), payment.getDataCompra().format(formatter));
    }
}
