package br.edu.infnet.acme.domain.validator;

import br.edu.infnet.acme.domain.model.CarBuyer;
import br.edu.infnet.acme.domain.validator.impl.ValidatorBase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LicenseNullValidator extends ValidatorBase {

    @Override
    public boolean validate(CarBuyer carBuyer) {
        if (carBuyer.getLicence() == null) {
            log.error("missing license info");
            return false;
        }

        return validateNext(carBuyer);
    }
}
