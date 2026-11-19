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

    /**
     * Class constructor.
     *
     * @param client the client that owns this basket.
     * @param company the company associated with this basket.
     */
    public Basket(Client client, Company company) {
        _client = client;
        _company = company;
    }

    /**
     * Checks whether this basket is eligible for checkout.
     *
     * @return {@code true} if checkout is allowed, {@code false} otherwise.
     */
    public boolean canCheckout() { 
        return false; 
    }

    /**
     * Returns a string representation of the basket information.
     *
     * @return the formatted string representation of the basket.
     */
    @Override
    public String toString() {
        return _client.getEmail() + " - " + _company.getId() + " - UNICA - SEM_TRANSPORTE - " + 0;
    }
}