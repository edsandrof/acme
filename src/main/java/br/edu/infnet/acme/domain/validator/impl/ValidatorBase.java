package br.edu.infnet.acme.domain.validator.impl;

import br.edu.infnet.acme.domain.model.CarBuyer;
import br.edu.infnet.acme.domain.validator.Validator;

public abstract class ValidatorBase implements Validator {

    private Validator next;

    public void setNext(Validator validator) {
        this.next = validator;
    }

    protected boolean validateNext(CarBuyer carBuyer) {
        if (next != null) {
            return next.validate(carBuyer);
        }

        return true;
    }
}
