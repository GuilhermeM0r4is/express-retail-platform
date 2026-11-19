package erp.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * The system keeps a registation of all companies, they can display various
 * types of products, the same can be applied otherwise. They are able to
 * have different prices than the recommended ones for products.
 */
public class Company implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;
    private static int _nextId = 1;   // assures incrementation of id

    // class attributes
    private final String _name;
    private final int _id;
    private final List<SuppliedProduct> _suppliedProducts = new ArrayList<>();
    private final List<Client> _clients = new ArrayList<>();

    /**
     * Constructor class of a product.
     * @param name String containing the company name.
     */
    public Company(String name) {
        _name = name;
        _id = _nextId;
        _nextId++;
    }

    /**
     * Getter method for class attribute.
     * @return int _id private method for usage.
     */
    public int getId() {
        return _id;
    }

    /**
     * Getter method for class attribute.
     * @return String _name private method for usage.
     */
    public String getName() {
        return _name;
    }

    /**
     * Getter method for class attribute.
     * @return List of SuppliedProduct.
     */
    public List<SuppliedProduct> getSuppliedProducts() {
        return _suppliedProducts;
    }

    /**
     * Getter method for class attribute.
     * @return List of Clients.
     */
    public List<Client> getClients() {
        return _clients;
    }

    /**
     * Shows a company with the specific expected output as String.
     * @return String of a company showcase.
     */
    public String toString() {
        return getId() + " - " + getName();
    }

    /**
     * Changes an inventory value and factor for a specific company product, updating the
     * quantity of the product and giving it a new factor value.
     *
     * @param product Product to search for in company;
     * @param quantity A value to update the Product quantity;
     * @param factor A new factor to give into the Product.
     *
     * @return true in case of storage update, false otherwise.
     */
    public boolean changeInventory(Product product, int quantity, double factor) {

        // first case, product is the one given and exists
        for (SuppliedProduct sp: _suppliedProducts) {
            Product p = sp.getProduct();
            if (p.getId() == product.getId()) {
                return sp.update(quantity, factor);
            }
        }

        // product doesn't exist yet, check if all positive
        if (quantity < 0 || factor <= 0) {
            return false;
        }

        // creates a new product, updates and adds to the list
        SuppliedProduct newSp = new SuppliedProduct(product, this, factor);
        newSp.update(quantity, factor);
        _suppliedProducts.add(newSp);
        return true;
    }

    /**
     * Assures that in case ids are already defined, we can easily just update the nextId
     * counter to match them, keeping the app flow going.
     *
     * @param nextId A given id that we have to use and update the current one.
     */
    public static void setNextId(int nextId) {
        _nextId = nextId;
    }
}
