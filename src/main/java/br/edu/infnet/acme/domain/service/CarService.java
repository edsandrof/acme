package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.application.exception.CarBuyerRegisterException;
import br.edu.infnet.acme.domain.model.Car;
import br.edu.infnet.acme.domain.model.CarBuyer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CarService {
    public void buyCar(Car ferrari, CarBuyer buyer) {
        System.out.println("verifying buyer data...");
        validate(buyer);

        // ...
        // continue the purchasing process
    }

    public void validate(CarBuyer buyer) {
        LocalDate today = LocalDate.now();

        if (buyer.getName().isEmpty()) {
            throw new CarBuyerRegisterException("missing name info");
        }

        if (buyer.getAddress().isEmpty()) {
            throw new CarBuyerRegisterException("missing address info");
        }

        if (buyer.getLicence() == null) {
            throw new CarBuyerRegisterException("missing license info");
        }

        if (buyer.getBirthdate() == null) {
            throw new CarBuyerRegisterException("missing birthdate info");
        }

        if (ChronoUnit.YEARS.between(buyer.getBirthdate(), today) < 30) {
            throw new CarBuyerRegisterException("buyer is too young");
        }

        if (buyer.getState().isEmpty()) {
            throw new CarBuyerRegisterException("missing state info");
        }

        if (buyer.getLicence().getValidity().isBefore(today)) {
            throw new CarBuyerRegisterException("license expired");
        }

        if (!buyer.getLicence().getState().equalsIgnoreCase(buyer.getState())) {
            throw new CarBuyerRegisterException("invalid license state");
        }

        if (buyer.getLicence().getViolationPoints().isPresent()) {
            if (buyer.getLicence().getViolationPoints().get() > 20) {
                throw new CarBuyerRegisterException("violation points exceeded");
            }
        }

        if (buyer.getLicence().getId().startsWith("I6")) {
            throw new CarBuyerRegisterException("invalid license issuer");
        }
    }
}
