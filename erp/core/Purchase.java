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
    // FIXME last instalment: private final List<Order> _orders;

    //constructor
    Purchase(int id, int date, Client client, Company company, List<PurchaseLine> lines) {
        _id = id;
        _date = date;
        _client = client;
        _company = company;
        _lines = new ArrayList<>(lines);
    }

    //getter for id
    public int getId() { 
        return _id; 
    }

    //getter for date
    public int getDate() { 
        return _date; 
    }

    //getter for client
    public Client getClient() { 
        return _client; 
    }

    //getter for company
    public Company getCompany() { 
        return _company; 
    }

    //total cost of lines 
    public double getItemsCost() {
        double total = 0;
        for (PurchaseLine line : _lines)
            total += line.getCost();
        return total;
    }

    //total cost of orders FIXME for last instalment: somar o custo de cada Order
    public double getOrdersCost() { 
        return 0.0; 
    }

    //getter for totalcost
    public double getTotalCost() { 
        return getItemsCost() + getOrdersCost(); 
    }

    // Formate: id - email - idEmpresa - data - total itens - custo encomendas - custo total 
    @Override
    public String toString() {
        return _id + " - " + _client.getEmail() + " - " + _company.getId() + " - " + _date
                + " - " + getItemsCost()
                + " - " + getOrdersCost()
                + " - " + getTotalCost();
    }
}