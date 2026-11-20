package erp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

    /**
     * Class constructor.
     *
     * @param name the String _name of the product;
     * @param category the ProductCategory of the product, can be "eletronica", "alimentar" or "vestuario";
     * @param basePrice the int _basePrice of the product;
     * @param baseDeliveryTime the int _baseDeliveryTime of the product;
    */
    public Product(String name, ProductCategory category, int basePrice,
                int baseDeliveryTime) {

        _id = _nextId;
        _nextId++;
        _name = name;
        _category = category;
        _basePrice = basePrice;
        _baseDeliveryTime = baseDeliveryTime;
    }

    /**
     * Getter for the product Id.
     *
     * @return int _id private method for usage;
     */
    public int getId() {
        return _id;
    }

    /**
     * Getter for the product name.
     *
     * @return String _name private method for usage.
     */
    public String getName() {
        return _name;
    }

    /**
     * Getter for the product category.
     *
     * @return the category;
     */
    public ProductCategory getCategory() {
        return _category;
    }

    /**
     * Getter for the product base price.
     *
     * @return int _basePrice private method for usage;
     */
    public int getBasePrice() {
        return _basePrice;
    }

    /**
     * Getter for the product base delivery time.
     *
     * @return int _baseDeliveryTime private method for usage;
     */
    public int getBaseDeliveryTime() {
        return _baseDeliveryTime;
    }

    /**
     * Returns a string representation of the purchase formatted as:
     * id - type - name - category - base price - recommended price - base delivery time - total units.
     *
     * @return the formatted string representation of the product.
     */
    @Override
    public String toString() {
        return getId() + " - " + type() + " - " + getName() + " - " + getCategory()
                    + " - " + String.format(Locale.ROOT, "%.2f", (double) getBasePrice())
                    + " - " + String.format(Locale.ROOT, "%.2f", recommendedPrice())
                    + " - " + getBaseDeliveryTime() + " - " + totalUnits();
        }

    /**
     * Computes the total available units of this product across all given companies.
     *
     * @return Total number of units for this product.
     */
    public int totalUnits() {
        int total = 0;
        for (SuppliedProduct sp : _suppliedProducts) {
            total += sp.getUnits();
        }
        return total;
    }

    // abstract attributes to help.
    public abstract String type();

    abstract double recommendedPrice();

    // auxiliar functions

    /* Assures that in case ids are already defined, we can easily just
       update the nextId counter to match them */
    public static void setNextId(int nextId) {
        _nextId = nextId;
    }

    /**
     * Registers a supplied product entry for this product if not already present.
     * @param sp SuppliedProduct instance associated with this product.
     */
    void addSuppliedProduct(SuppliedProduct sp) {
        if (sp != null && !_suppliedProducts.contains(sp)) {
            _suppliedProducts.add(sp);
        }
    }
}
