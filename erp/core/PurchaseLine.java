package erp.core;

import java.io.Serializable;

/**
 * A line in a purchase, consisting of a product, a quantity and a unit price.
 */
class PurchaseLine implements Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    //attributes
    private final Product _product;

    private final int _quantity;

    private final double _unitPrice;

    /**
     * Class constructor.
     *
     * @param product the purchased product.
     * @param quantity the number of units purchased.
     * @param unitPrice the unit price fixed at the time of purchase.
     */
    public PurchaseLine(Product product, int quantity, double unitPrice) {
        _product = product;
        _quantity = quantity;
        _unitPrice = unitPrice;
    }

    /**
     * Getter for the purchased product.
     *
     * @return the product.
     */
    public Product getProduct() { 
        return _product; 
    }

    /**
     * Getter for the quantity of the product in this line.
     *
     * @return the quantity.
     */
    public int getQuantity() { 
        return _quantity; 
    }

    /**
     * Getter for the unit price fixed in this purchase line.
     *
     * @return the unit price.
     */
    public double getUnitPrice() { 
        return _unitPrice; 
    }

    /**
     * Computes the total cost of this purchase line (unit price multiplied by quantity).
     *
     * @return the total cost of the line.
     */
    double getCost() { 
        return _unitPrice * _quantity; 
    }

    /**
     * Returns a string representation of the purchase line using the same format
     * as {@code BasketItem.toString()}.
     *
     * @return the formatted string representation of the line.
     */
    @Override
    public String toString() {
        return _product.getId() + " - " + _product.getName() + " - " + _quantity + " - "
                + _unitPrice + " - " + getCost();
    }
}