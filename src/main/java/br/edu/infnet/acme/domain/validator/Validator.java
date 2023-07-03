package br.edu.infnet.acme.domain.validator;

import br.edu.infnet.acme.domain.model.CarBuyer;

public interface Validator {

    boolean validate(CarBuyer carBuyer);
}
