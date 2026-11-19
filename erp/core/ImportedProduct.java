package erp.core;

/**
 * The system keeps a registation of all products, they exist apart from how
 * each company uses them, meaning that they can be provided by different
 * companies with different values.
 *
 * A Product can be have different types, imported is one of them, an imported
 * product by itself has some specifications, including a tax and a given country.
 */
public class ImportedProduct extends Product {

    // class attributes
    private double _tax;
    private String _country;

    // constructor of the class
    public ImportedProduct(String name, ProductCategory category, int basePrice,
                            int baseDeliveryTime, double tax, String country) {

        super(name, category, basePrice, baseDeliveryTime);
        _tax = tax;
        _country = country;
    }

    // the getters, add all needed here
    public double getTax() {
        return _tax;
    }

    public String getCountry() {
        return _country;
    }

    // all class methods
    @Override
    public String toString() {
        return super.toString() + " - " + _country() + " - " + _tax;
    }

    @Override
    public double getRecommendedPrice() {
        return getBasePrice() * (100.0 + _tax) / 100.0;
    }

    @Override
    String type() {
        return "IMPORTADO";
    }
}
