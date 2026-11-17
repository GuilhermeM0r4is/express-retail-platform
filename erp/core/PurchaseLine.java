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

    //constructor
    PurchaseLine(Product product, int quantity, double unitPrice) {
        _product = product;
        _quantity = quantity;
        _unitPrice = unitPrice;
    }

    //getter for product
    Product getProduct() { 
        return _product; 
    }

    //getter for quantity
    int getQuantity() { 
        return _quantity; 
    }

    //getter for unitPrice
    double getUnitPrice() { 
        return _unitPrice; 
    }

    //getter for cost
    double getCost() { 
        return _unitPrice * _quantity; 
    }

    //same formate as BasketItem.toString()
    @Override
    public String toString() {
        return _product.getId() + " - " + _product.getName() + " - " + _quantity + " - "
                + _unitPrice + " - " + getCost();
    }
}