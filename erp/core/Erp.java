package erp.core;

import erp.core.exception.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    /* we decided to use hashmaps, as most other methods look for ids too, so we can save
       up time, using O(1) searches rather than O(n) during the search loops */
    private Map<Integer, Company> _companies = new HashMap<>();
    private Map<Integer, Product> _products = new HashMap<>();
    private final Map<String, Client> _clients = new HashMap<>();

    /**
     * Class Constructor, is and will stay empty as all the needed
     * variables are already initialized by their own.
     */
    public Erp() {}

    /**
    * Read text input file at the beginning of the program and populates the
    * the state of this application with the domain entities represented in the text file.
    *
    * @param filename name of the text input file to process
    * @throws UnrecognizedEntryException if some entry is not correct
    * @throws IOException if there is an IO erro while processing the text file
    */
    void importFile(String filename) throws UnrecognizedEntryException, IOException
        /* FIXME maybe other exceptions */ {

        Parser parser = new Parser(this);
        parser.parseFile(filename);
    }

    /**
     * Registers a new Company by its name, updating the hashmap.
     * @param name A name to give to the new company.
     */
    public void registerCompany(String name) {
        Company c = new Company(name);
        _companies.put(c.getId(), c);
    }

    /**
     * Registers an ImportedProduct from the given parameters.
     *
     * @param name A name/description for the product;
     * @param basePrice Base price for the product;
     * @param category A given category from ProductCategory enumerate;
     * @param baseDeliveryTime Expected base delivery time as an int;
     * @param country The country of the ImportedProduct;
     * @param tax The tax percentage of the given country.
     */
    public void registerImportedProduct(String name, int basePrice, ProductCategory category,
                                        int baseDeliveryTime, String country, double tax) {

        ImportedProduct impP = new ImportedProduct(name, basePrice, category, baseDeliveryTime,
                                                   country, tax);
        _products.put(impP.getId(), impP);
    }

    /**
     * Registers a NationalProduct from the given parameters.
     *
     * @param name A name/description for the product;
     * @param basePrice Base price for the product;
     * @param category A given category from ProductCategory enumerate;
     * @param baseDeliveryTime Expected base delivery time as an int;
     */
    public void registerNationalProduct(String name, int basePrice, ProductCategory category,
                                        int baseDeliveryTime) {

        NationalProduct natP = new NationalProduct(name, basePrice, category, baseDeliveryTime);
        _products.put(natP.getId(), natP);
    }

    /**
     * Registers a Client into the system.
     *  
     * @param name The name of the client to register;
     * @param email The email of the client to register;
     * //FIXME altera o que for preciso da exception
     * @throws InvalidClientException If the name or email is invalid, or if a client with the same email already exists;
     */
    public void registerClient(String name, String email) throws InvalidClientException {
        if (name == null || name.isEmpty() || email == null || email.isEmpty()
            || _clients.containsKey(email)) {
        throw new InvalidClientException(name, email); //FIXME usa este nome para esta exception
        } 
        _clients.put(email, new Client(name, email))
    }

    /**
     * Returns a Client object from the system, given its email.
     *
     * @param email The email of the client to retrieve;
     * @return The Client object associated with the given email;
     * //FIXME altera o que for preciso da exception
     * @throws UnknownClientException If no client is found with the given email;
     */
    public Client getClient(String email) throws UnknownClientException {
        Client c = _clients.get(email);
        if (c == null) {
            throw new UnknownClientException(email); //FIXME usa este nome para esta exception
            } 
        return c;
    }

    /**
     * Returns a list of all clients in the system, sorted by name and email.
     *
     * @return A list of all clients in the system;
     */
    public List<Client> getClients() {
    return _clients.values().stream()
            .sorted(Comparator
                    .comparing(Client::getName, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(Client::getEmail, String.CASE_INSENSITIVE_ORDER))
            .toList();
    }

    /**
     * Updates a stock with the parameters.
     *
     * @param
     */
    public void updateStock() {
        //FIXME implement logic here
    }

    /**
     * Registers a Shipping Rate from the given parameters.
     *
     * @param
     */
    public void registeShippingRate() {
        //FIXME implement logic here
    }
}
