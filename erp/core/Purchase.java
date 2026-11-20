package erp.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The system keeps a record of all purchases. Each purchase is identified by a 
 * purchase number, which is assigned automaticallyand incrementally. A purchase is 
 * always made by a single customer from a single company on a specific date.
 */
public class Purchase implements Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    //attributes
    private final int _id;

    private final int _date;

    private final Client _client;

    private final Company _company;

    private final List<PurchaseLine> _lines;

    /** Class constructor.
     * 
     * @param id int containing the purchase id;
     * @param date int containg the date of the purchase;
     * @param company the purchase's company;
     * @param lines details of the purchase;
     */
    public Purchase(int id, int date, Client client, Company company, List<PurchaseLine> lines) {
        _id = id;
        _date = date;
        _client = client;
        _company = company;
        _lines = new ArrayList<>(lines);
    }

    /**
     * Getter for the purchase id.
     * 
     * @return int _id private method for usage;
     */
    public int getId() { 
        return _id; 
    }

    /**
     * Getter for the purchase date.
     * 
     * @return int _date private method for usage;
     */
    public int getDate() { 
        return _date; 
    }

    /**
     * Getter for the client that made the purchase.
     * 
     * @return the client;
     */
    public Client getClient() { 
        return _client; 
    }

    /**
     * Getter for the purchase's company.
     * 
     * @return the company;
     */
    public Company getCompany() { 
        return _company; 
    }

    /**
     * Getter for the total cost of the items.
     * 
     * @return the cost.
     */
    public double getItemsCost() {
        double total = 0;
        for (PurchaseLine line : _lines)
            total += line.getCost();
        return total;
    }

    /**
     * Getter for the total cost of the orders associated with this purchase.
     *
     * @return the total orders cost.
     */
    public double getOrdersCost() { 
        return 0.0; 
    }

    /**
     * Getter for the total cost of the purchase (items plus orders).
     *
     * @return the total cost of the purchase.
     */
    public double getTotalCost() { 
        return getItemsCost() + getOrdersCost(); 
    }

    /**
     * Returns a string representation of the purchase formatted as:
     * id - email - idEmpresa - data - total itens - custo encomendas - custo total.
     *
     * @return the formatted string representation of the purchase.
     */
    @Override
    public String toString() {
        return _id + " - " + _client.getEmail() + " - " + _company.getId() + " - " + _date
                + " - " + getItemsCost()
                + " - " + getOrdersCost()
                + " - " + getTotalCost();
    }
}