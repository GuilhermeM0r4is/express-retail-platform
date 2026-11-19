package erp.core;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 * The system keeps a registation of all clients, they display thier identifier(email), their
 * name and total amount spent in purchases.
 */

public class Client implements Serializable {
    
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    //attributes 
    private final String _name;

    private final String _email;

    private final double _totalSpent = 0.0;

    private final Map<Integer, Basket> _basket = new HashMap<>();

    /**
     * Class constructor.
     * 
     * @param name String containing the name of the client;
     * @param email String containing the id of the client;
     */
    public Client(String name, String email) {
        _name = name;
        _email = email;
    }

    /**
     * Getter for the clients name.
     *
     * @return the client's name;
     */
    public String getName() {
        return _name;
    }

    /**
     * Getter for the client's email address.
     *
     * @return the client's email;
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Getter for the total amount spent by the client in purchases.
     *
     * @return the total amount spent;
     */
    public double getTotalSpent() {
        return _totalSpent;
    }

    /**
     * Returns a string representation of the client formatted as:
     * email - name - totalSpent.
     *
     * @return the formatted string representation of the client;
     */
    @Override
    public String toString() {
        return _email + " - " + _name + " - " + _totalSpent;
    }

    /**
     * Checks whether the client already has a basket associated with the given company.
     *
     * @param company the company to check for an existing basket;
     * @return {@code true} if a basket exists for the company, {@code false} otherwise;
     */
    boolean hasBasketFor(Company company) { 
        return _basket.containsKey(company.getId()); 
    } 

    //...
    Basket getBasketFor(Company company) {
        return _baskets.get(company.getId());
    }
}