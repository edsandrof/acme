package br.edu.infnet.acme.domain.validator;

import br.edu.infnet.acme.domain.model.CarBuyer;

public class CarBuyerValidator {
    private Validator firstValidator;

    public CarBuyerValidator() {
        buildValidationChain();
    }

    private void buildValidationChain() {
        var nameEmptyValidator = new NameEmptyValidator();
        var addressEmptyValidator = new AddressEmptyValidator();
        var licenseNullValidator = new LicenseNullValidator();
        var birthdateNullValidator = new BirthdateNullValidator();
        var ageValidator = new AgeValidator();
        var stateEmptyValidator = new StateEmptyValidator();
        var licenseValidityValidator = new LicenseValidityValidator();
        var licenseStateValidator = new LicenseStateValidator();
        var licenseViolationPointsValidator = new LicenseViolationPointsValidator();
        var licenseIssuerValidator = new LicenseIssuerValidator();

        nameEmptyValidator.setNext(addressEmptyValidator);
        addressEmptyValidator.setNext(licenseNullValidator);
        licenseNullValidator.setNext(birthdateNullValidator);
        birthdateNullValidator.setNext(ageValidator);
        ageValidator.setNext(stateEmptyValidator);
        stateEmptyValidator.setNext(licenseValidityValidator);
        stateEmptyValidator.setNext(licenseStateValidator);
        licenseStateValidator.setNext(licenseViolationPointsValidator);
        licenseViolationPointsValidator.setNext(licenseIssuerValidator);

        firstValidator = nameEmptyValidator;
    }

    public boolean validateCarBuyer(CarBuyer carBuyer) {
        return firstValidator.validate(carBuyer);
    }
}
