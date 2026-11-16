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
    private String _name;
    private int _id;
    private List<SuppliedProduct> _suppliedProducts;

    // constructor class
    public Company(String name) {
        _name = name;
        _id = _nextId;
        _nextId++;
        _suppliedProducts = new ArrayList<>();
    }

    // getter methods, add when needed
    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public List<SuppliedProduct> getSuppliedProducts() {
        return _suppliedProducts;
    }

    // all class methods
    public String toString() {
        return getId() + " - " + getName();
    }

    public boolean changeInventory(Product product, int quantity, double factor) {

        // first case, product is the one given and exists
        for (SuppliedProduct sp: _suppliedProducts) {
            if (sp.getId() == product.getId()) {
                return sp.update(quantity, factor);
            }
        }

        // product doesn't exist yet, check if all positive
        if (quantity < 0 || factor < 0) {
            return false;
        }

        // creates a new product, updates and adds to the list
        SuppliedProduct newSp = new SuppliedProduct(this, quantity, factor);
        newSp.update(quantity, factor);
        _suppliedProducts.add(newSp);
        return true;
    }

    // auxiliar functions

    /* Assures that in case ids are already defined, we can easily just
       update the nextId counter to match them */
    public static void setNextId(int nextId) {
        _nextId = nextId;
    }
}
