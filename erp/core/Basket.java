package erp.core;

import java.io.Serializable;
/**
 * THIS IS THE MINIMAL BASKET POSSIBLE TO COMPILE SINCE THE BASKET ITSELF IS NOT SUPPOSE
 * TO BE ADDED UNTIL THE LAST INSTALMENT, THE BasketUpdate.java ONE IS FOR THE FUTURE.
 */

public class Basket implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    //attributes
    private final Client _client;

    private final Company _company;

    //constructor
    public Basket(Client client, Company company) {
        _client = client;
        _company = company;
    }

    //getter for checkout
    public boolean canCheckout() { 
        return false; 
    }

    @Override
    //toString method to display the basket information
    public String toString() {
        return _client.getEmail() + " - " + _company.getId() + " - UNICA - SEM_TRANSPORTE - " + 0;
    }
}