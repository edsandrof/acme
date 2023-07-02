package br.edu.infnet.acme.domain.model;

import java.time.LocalDate;
import java.util.Optional;

public class DriveLicense {
    private final String id;
    private final LocalDate issueDate;
    private final LocalDate validity;
    private final String state;
    private Optional<Integer> violationPoints;

    public DriveLicense(String id, LocalDate issueDate, LocalDate validity, String state) {
        this.id = id;
        this.issueDate = issueDate;
        this.validity = validity;
        this.state = state;
        this.violationPoints = Optional.empty();
    }

    public String getId() {
        return id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getValidity() {
        return validity;
    }

    public String getState() {
        return state;
    }

    public Optional<Integer> getViolationPoints() {
        return violationPoints;
    }

    public void setViolationPoints(int violationPoints) {
        this.violationPoints = Optional.of(violationPoints);
    }

    @Override
    public String toString() {
        return "DriveLicense id: " + id + ", issueDate=" + issueDate + ", validity=" + validity + ", state='" + state;
    }
}
