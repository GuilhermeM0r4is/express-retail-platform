package erp.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * The system keeps a registation of all companies, they can display various
 * types of products, the same can be applied otherwise. They are able to
 * have different prices than the recommended ones for products.
 */
abstract class Company implements Serializable {

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

    // auxiliar functions

    /* Assures that in case ids are already defined, we can easily just
       update the nextId counter to match them */
    public static void setNextId(int nextId) {
        _nextId = nextId;
    }
}
