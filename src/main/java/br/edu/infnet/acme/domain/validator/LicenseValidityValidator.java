package br.edu.infnet.acme.domain.validator;

import br.edu.infnet.acme.domain.model.CarBuyer;
import br.edu.infnet.acme.domain.validator.impl.ValidatorBase;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
@Slf4j
public class LicenseValidityValidator extends ValidatorBase {

    @Override
    public boolean validate(CarBuyer carBuyer) {
        LocalDate today = LocalDate.now();

        if (carBuyer.getLicence().getValidity().isBefore(today)) {
            log.error("license expired");
            return false;
        }

        return validateNext(carBuyer);
    }
}
