package erp.core;

import java.io.Serializable;

/**
 * Here in SuppliedProduct we have the correlation between products and
 * companies, as a company can hold multiple products and each product
 * can be in multiple companies.
 */
public class SuppliedProduct implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    // class attributes
    private Company _company;
    private Product _product;
    private double _factor;
    private int _units;

    // constructor class
    public SuppliedProduct(Product product, double factor) {
        _product = product;
        _factor = factor;
        _units = 0;
    }

    // getter methods, add when needed
    public double getFactor() {
        return _factor;
    }

    public int getUnits() {
        return _units;
    }

    // all class methods
    @Override
    public String toString() {
        return _company.getId() + " - " + _product.getId() + " - "
                + _units + " - " + _factor + " - " + price();
    }

    double price() {
        return _product.getBasePrice() * _factor;
    }

    int baseDeliveryTime() {
        return _product.getBaseDeliveryTime();
    }

    boolean update(int quantity, double factor) {

        if (factor < 0 || (quantity + _units) < 0) {
            return false;
        }
        _units += quantity;
        _factor = factor;
        return true;
    }
}
