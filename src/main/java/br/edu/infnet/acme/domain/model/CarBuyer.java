package br.edu.infnet.acme.domain.model;

import java.time.LocalDate;

public class CarBuyer {
    private String name;
    private DriveLicense licence;
    private LocalDate birthdate;
    private String address;
    private String state;

    public CarBuyer(String name, DriveLicense licence, LocalDate birthdate, String address, String state) {
        this.name = name;
        this.licence = licence;
        this.birthdate = birthdate;
        this.address = address;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public DriveLicense getLicence() {
        return licence;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Buyer: " + name + ", birthdate=" + birthdate +", address='" + address + ", licence: " + licence;
    }
}
