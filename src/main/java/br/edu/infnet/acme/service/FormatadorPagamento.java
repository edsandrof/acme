package br.edu.infnet.acme.service;

import br.edu.infnet.acme.model.Pagamento;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadorPagamento {

    private final Locale locale;

    public FormatadorPagamento(Locale locale) {
        this.locale = locale;
    }

    public String format(Pagamento pagamento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", locale);
        return String.format("\t> cliente: %s, data de compra: %s",
                pagamento.getCliente().getNome(), pagamento.getDataCompra().format(formatter));
    }
}
