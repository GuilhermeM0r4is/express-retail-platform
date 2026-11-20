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

    /**
     * Constructor of a supplied product entry.
     * @param product Product being supplied.
     * @param company Company supplying the product.
     * @param factor Price multiplier factor.
     */
    public SuppliedProduct(Product product, Company company, double factor) {

        if (product != null) {
            product.addSuppliedProduct(this);
        }
        _product = product;
        _company = company;
        _factor = factor;
        _units = 0;
    }

    /**
     * Getter for price factor.
     * @return double factor.
     */
    public double getFactor() {
        return _factor;
    }

    /**
     * Getter for available units.
     * @return int units.
     */
    public int getUnits() {
        return _units;
    }

    /**
     * Getter for the product.
     * @return Product instance.
     */
    public Product getProduct() {
        return _product;
    }

    /**
     * Returns string representation of the supplied product.
     * @return formatted supplied product string.
     */
    @Override
    public String toString() {
        return _company.getId() + " - " + _product.getId() + " - "
                + _units + " - " + _factor + " - " + price();
    }

    /**
     * Computes unit price with the applied factor.
     * @return unit price.
     */
    double price() {
        return Math.round(_product.recommendedPrice() * _factor * 100) / 100;
    }

    /**
     * Returns base delivery time of the product.
     * @return base delivery time.
     */
    int baseDeliveryTime() {
        return _product.getBaseDeliveryTime();
    }

    /**
     * Updates units and price factor if valid.
     * @param quantity Quantity delta to apply.
     * @param factor New price factor.
     * @return true if updated, false otherwise.
     */
    boolean update(int quantity, double factor) {

        if (factor < 0 || (quantity + _units) < 0) {
            return false;
        }
        _units += quantity;
        _factor = factor;
        return true;
    }
}
