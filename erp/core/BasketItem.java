//only for last instalment 
package erp.core;

import java.io.Serializable;
//FIXME maybe import classes

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

    private final SuppliedProduct[] _supplied;
    private int _quantity;

    //constructor
    BasketItem(SuppliedProduct supplied, int quantity) {
        _supplied = supplied;
        _quantity = quantity;
    }

    //getter for SuppliedProduct
    SuppliedProduct[] getSupplied() {
        return _supplied;
    }

    //getter for quantity
    int getQuantity() {
        return _quantity;
    }

    //update quantity function
    int update(int q) {
        _quantity += q;
        return _quantity;
    }

    double getUnitPrice() { return _supplied.price(); }
    double getCost() { return getUnitPrice() * _quantity; }

    @Override
    public String toString() {
        Product p = _supplied.getProduct();
        return p.getId() + " - " + p.getName() + " - " + _quantity + " - "
                + Money.format(getUnitPrice()) + " - " + Money.format(getCost());
    }
}
