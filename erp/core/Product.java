package erp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The system keeps a registation of all products, they exist apart from how
 * each company uses them, meaning that they can be provided by different
 * companies with different values.
 */
abstract class Product implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;
    private static int _nextId = 1;   // assures incrementation of id

    // class attributes
    private final String _name;
    private final int _id;
    private final ProductCategory _category;
    private final int _basePrice;
    private final int _baseDeliveryTime;
    private final List<SuppliedProduct> _suppliedProducts = new ArrayList<>();

    // constructor class
    public Product(String name, ProductCategory category, int basePrice,
                int baseDeliveryTime) {

        _id = _nextId;
        _nextId++;
        _name = name;
        _category = category;
        _basePrice = basePrice;
        _baseDeliveryTime = baseDeliveryTime;
    }

    // getter methods, add when needed
    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public ProductCategory getCategory() {
        return _category;
    }

    public int getBasePrice() {
        return _basePrice;
    }

    public int getBaseDeliveryTime() {
        return _baseDeliveryTime;
    }

    // all class methods
    @Override
    public String toString() {
        return getId() + " - " + type() + " - " + getName() + " - " + getCategory()
                + " - " + getBasePrice() + " - " + recommendedPrice() + " - "
                + getBaseDeliveryTime() + " - " + totalUnits();
    }

    /**
     * Computes the total available units of this product across all given companies.
     *
     * @param companies Collection of companies to check supplied products for.
     * @return Total number of units for this product.
     */
    public int totalUnits() {
        int total = 0;
        for (SuppliedProduct sp : _suppliedProducts) {
            total += sp.getUnits();
        }
        return total;
    }   

    public abstract String type();

    abstract double recommendedPrice();

    // auxiliar functions

    /* Assures that in case ids are already defined, we can easily just
       update the nextId counter to match them */
    public static void setNextId(int nextId) {
        _nextId = nextId;
    }
}
