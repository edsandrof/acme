package br.edu.infnet.acme.domain.service;

import br.edu.infnet.acme.application.exception.CarBuyerRegisterException;
import br.edu.infnet.acme.domain.model.Car;
import br.edu.infnet.acme.domain.model.CarBuyer;
import br.edu.infnet.acme.domain.model.DriveLicense;
import br.edu.infnet.acme.domain.validator.CarBuyerValidator;

import java.awt.*;
import java.time.LocalDate;

public class Exercise3Service {

    private Exercise3Service() {
        throw new IllegalStateException("Utility class");
    }

    public static void runExercise3() {
        LocalDate today = LocalDate.now();

        DriveLicense license = new DriveLicense("I6D84479", today.minusYears(1L), today.plusYears(5L), "Idaho");
        CarBuyer buyer = new CarBuyer("Mike Jordan", license, LocalDate.of(2000, 10, 10), "Avenue 1st, 3", "New York");
        Car ferrari = new Car("Ferrari", "HX", 2020, Color.RED);

        System.out.println("Starting buying a car...");
        CarService carService = new CarService();

        CarBuyerValidator carBuyerValidator = new CarBuyerValidator();
        boolean isValid = carBuyerValidator.validateCarBuyer(buyer);

        if (isValid) {
            carService.buyCar(ferrari, buyer);
        } else {
            throw new CarBuyerRegisterException("Error in car buyer validation");
        }
    }
}
