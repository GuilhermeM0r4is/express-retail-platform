package erp.core;

import java.io.Serializable;

/**
 * Here in SuppliedProduct we have the correlation between products and
 * companies, as a company can hold multiple products and each product
 * can be in multiple companies.
 */
abstract class SuppliedProduct implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    // class attributes

    // constructor class
    public SuppliedProduct() {
    }

    // getter methods, add when needed

    // all class methods
}
