package erp.core;

/**
 * The system keeps a registation of all products, they exist apart from how
 * each company uses them, meaning that they can be provided by different
 * companies with different values.
 *
 * A Product can be have different types, national is one of them, a national
 * product by itself has some specifications.
 */
public class NationalProduct extends Product {

    /**
     * Constructor of a national product.
     * @param name Product name.
     * @param category Product category.
     * @param basePrice Product base price.
     * @param baseDeliveryTime Product base delivery time.
     */
    public NationalProduct(String name, ProductCategory category, int basePrice,
                            int baseDeliveryTime) {

        super(name, category, basePrice, baseDeliveryTime);
    }

    /**
     * Returns string representation of a national product.
     * @return formatted product string.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Computes the recommended price based on total units.
     * @return recommended price.
     */
    @Override
    public double recommendedPrice() {

        int units = totalUnits();

        if (units > 10000) {
            return 0.9;
        }
        else if (units < 100) {
            return 1.3;
        }
        else {
            return getBasePrice();
        }
    }

    /**
     * Returns the product type label.
     * @return product type string.
     */
    @Override
    public String type() {
        return "NACIONAL";
    }
}
