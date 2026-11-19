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

    /**
     * Constructor of an imported product.
     * @param name Product name.
     * @param category Product category.
     * @param basePrice Product base price.
     * @param baseDeliveryTime Product base delivery time.
     * @param tax Customs tax value.
     * @param country Country of origin.
     */
    public ImportedProduct(String name, ProductCategory category, int basePrice,
                                int baseDeliveryTime, double tax, String country) {

        super(name, category, basePrice, baseDeliveryTime);
        _tax = tax;
        _country = country;
    }

    /**
     * Getter for customs tax.
     * @return double tax value.
     */
    public double getTax() {
        return _tax;
    }

    /**
     * Getter for country of origin.
     * @return String country name.
     */
    public String getCountry() {
        return _country;
    }

    /**
     * Returns string representation of an imported product.
     * @return formatted product string.
     */
    @Override
    public String toString() {
        return super.toString() + " - " + _country + " - " + _tax;
    }

    /**
     * Computes the recommended price including tax.
     * @return recommended price.
     */
    public double getRecommendedPrice() {
        return getBasePrice() * (100.0 + _tax) / 100.0;
    }

    /**
     * Returns the product type label.
     * @return product type string.
     */
    @Override
    public String type() {
        return "IMPORTADO";
    }
}
