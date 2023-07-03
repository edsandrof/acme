package br.edu.infnet.acme.domain.validator;

import br.edu.infnet.acme.domain.model.CarBuyer;
import br.edu.infnet.acme.domain.validator.impl.ValidatorBase;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
public class AgeValidator extends ValidatorBase {

    @Override
    public boolean validate(CarBuyer carBuyer) {
        LocalDate today = LocalDate.now();

        if (ChronoUnit.YEARS.between(carBuyer.getBirthdate(), today) < 30) {
            log.error("buyer is too young");
            return false;
        }

        return validateNext(carBuyer);
    }
}
