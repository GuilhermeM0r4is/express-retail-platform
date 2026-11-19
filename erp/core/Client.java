package erp.core;

import java.io.Serializable;

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
     * returns 
     */
    public String getName() {
        return _name;
    }

    //getter for email
    public String getEmail() {
        return _email;
    }

    //getter for total spent
    public double getTotalSpent() {
        return _totalSpent;
    }

    //toString method to display client information
    @Override
    public String toString() {
        return _email + " - " + _name + " - " + _totalSpent;
    }

    //FIXME AI asked to had this to Client, weird
    boolean hasBasketFor(Company company) { 
        return _baskets.containsKey(company.getId()); 
    }

    //addSpent, MAYBE-TO-ADD
}