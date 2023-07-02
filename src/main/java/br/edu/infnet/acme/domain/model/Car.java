package br.edu.infnet.acme.domain.model;

import java.awt.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Car {
    private final String name;
    private final String model;
    private final int year;
    private final Color color;
    private BigDecimal sellValue;

    public Car(String name, String model, int year, Color color) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public Car(String name, String model, int year, Color color, BigDecimal sellValue) {
        this(name, model, year, color);
        this.sellValue = sellValue;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public Color getColor() {
        return color;
    }

    public BigDecimal getSellValue() {
        return sellValue;
    }

    public void setSellValue(BigDecimal sellValue) {
        this.sellValue = sellValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && Objects.equals(name, car.name) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, model, year);
    }

    @Override
    public String toString() {
        return "Car: " + name + ", model: " + model + ", year: " + year + ", color: " + color;
    }
}
