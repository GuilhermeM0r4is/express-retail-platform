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

    // constructor of the class
    public NationalProduct(String name, ProductCategory category, int basePrice,
                            int baseDeliveryTime) {

        super(name, category, basePrice, baseDeliveryTime);
    }

    // all class methods
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public double getRecommendedPrice() {

        int units = totalUnits();

        if (units > 10000) {
            return 90.0;
        }
        else if (units < 100) {
            return 130.0;
        }
        else {
            return getBasePrice();
        }
    }

    @Override
    String type() {
        return "NACIONAL";
    }
