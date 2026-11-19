//only for last instalment 
package erp.core;

import java.io.Serializable;

/**
 * A client's shopping basket at one company: what it intends to buy, and how it
 * wants the purchase shipped.
 *
 * <p>The basket menu is opened <em>over</em> a basket, so this class is the
 * receiver of every command in {@code erp.app.basket}. Its name and package are
 * fixed by that code; its content is yours to define.</p>
 */
public class BasketItem implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    private final SuppliedProduct _supplied;
    private int _quantity;

    /**
     * Class constructor.
     *
     * @param supplied the supplied product entry associated with this item.
     * @param quantity the initial quantity of the item.
     */
    public BasketItem(SuppliedProduct supplied, int quantity) {
        _supplied = supplied;
        _quantity = quantity;
    }

    /**
     * Getter for the supplied product entries.
     *
     * @return the supplied product array.
     */
    SuppliedProduct getSupplied() {
        return _supplied;
    }

    /**
     * Getter for the item quantity.
     *
     * @return the quantity.
     */
    int getQuantity() {
        return _quantity;
    }

    /**
     * Updates the item quantity by adding the given delta.
     *
     * @param q the quantity delta to add (may be positive or negative).
     * @return the updated quantity.
     */
    int update(int q) {
        _quantity += q;
        return _quantity;
    }

    /**
     * Getter for the unit price of the item.
     *
     * @return the unit price.
     */
    double getUnitPrice() { 
        return _supplied.price(); 
    }

    /**
     * Computes the total cost of this item (unit price multiplied by quantity).
     *
     * @return the total cost of the item.
     */
    double getCost() { 
        return getUnitPrice() * _quantity; 
    }

    @Override
    public String toString() {
        Product p = _supplied.getProduct();
        return p.getId() + " - " + p.getName() + " - " + _quantity + " - "
                + getUnitPrice() + " - " + getCost();
    }
}
