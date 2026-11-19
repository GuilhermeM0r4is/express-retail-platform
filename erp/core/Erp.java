package erp.core;

import erp.core.exception.*;
import java.io.IOException;
import java.io.Serializable;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    /* we decided to use hashmaps, as most other methods look for ids too, so we can save
       up time, using O(1) searches rather than O(n) during the search loops */
    private final Map<Integer, Company> _companies = new HashMap<>();
    private final Map<Integer, Product> _products = new HashMap<>();
    private final Map<String, Client> _clients = new HashMap<>();

    //used for advancing or showing the date
    private final Date _date = new Date();

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
    *
    * @throws UnrecognizedEntryException if some entry is not correct
    * @throws IOException if there is an IO erro while processing the text file
    */
    void importFile(String filename) throws UnrecognizedEntryException, IOException {
        //FIXME not sure if we can or should have something here, Erp isnt supposed to work with
        //      file imports, however prof left this method still here for some reason?
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
     *
     * @throws DuplicateProductException in case of duplicated Product in system.
     */
    public void registerImportedProduct(String name, int basePrice, ProductCategory category,
                                        int baseDeliveryTime, String country, double tax)
                                        throws DuplicateProductException {

        for (Product p: _products.values()) {
            if (p.getName().equals(name)) {
                throw new DuplicateProductException(name);
            }
        }
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
     *
     * @throws DuplicateProductException in case of duplicated Product in system.
     */
    public void registerNationalProduct(String name, int basePrice, ProductCategory category,
                                        int baseDeliveryTime) throws DuplicateProductException {

        for (Product p: _products.values()) {
            if (p.getName().equals(name)) {
                throw new DuplicateProductException(name);
            }
        }
        NationalProduct natP = new NationalProduct(name, basePrice, category, baseDeliveryTime);
        _products.put(natP.getId(), natP);
    }

    /**
    * Performs a search for all products inside system based on criterion type and a given term,
    * compares using all lowercase words as asked in headings. Filters products based on criterion
    * matching and sorts them, using toString() to showcase.
    *
    * @param criterion Type of three possible criterions to search for;
    * @param term A given String that we assure it's equal to the Product criterion.
    *
    * @returns A List containing all products using the toString() showcase.
    */
    public List<String> searchProducts(String criterion, String term) {
        String normalizedTerm = term.toLowerCase();

        return _products.values().stream()
            .filter(p -> matchesCriterion(p, criterion, normalizedTerm))
            .sorted(Comparator.comparing(Product::getId))
            .map(Product::toString)
            .toList();
    }

    /**
     * Returns a Product object from the system, given its name.
     *
     * @param name The name of the Product to show.
     * @return The Product object associated with the given name.
     *
     * @throws UnknownProductException If no Product is found with given name;
     */
    public Product getProduct(String name) throws UnknownProductException {
        for (Product p: _products.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        throw new UnknownProductException(name);
    }

    /**
     * Returns a list of all Products in system, orderer by id.
     * @return A list of all Products in system.
     */
    public List<Product> getProducts() {
        return _products.values().stream()
            .sorted(Comparator.comparing(Product::getId))
            .toList();
    }

    /**
    * Matches a given criterion for a Product in question, uses a switch case for the three different
    * possible criterions, and assures comparison. If ORIGEM given, searches ImportedProduct instead.
    *
    * @param p A product to check for given criterion and term of search;
    * @param criterion One of three possible options to check in switch;
    * @param normalizedTerm A lowercase term to compare with .equals() for the Product one.
    *
    * @return true, if the normalizedTerm matches with given Product, false otherwise.
    */
    private boolean matchesCriterion(Product p, String criterion, String normalizedTerm) {

        return switch (criterion.toUpperCase()) {
            case "NOME" -> p.getName() != null && p.getName().toLowerCase().equals(normalizedTerm);

            case "CATEGORIA" -> p.getCategory() != null &&
                                 p.getCategory().toString().toLowerCase().equals(normalizedTerm);

            case "ORIGEM" -> (p instanceof ImportedProduct imp) && imp.getOriginCountry() != null &&
                             imp.getOriginCountry().toLowerCase().equals(normalizedTerm);

            default -> false;
        };
    }

    /**
     * Registers a new Company by its name, updating the hashmap.
     * @param name A name to give to the new company.
     */
    public void registerCompany(String name) throws DuplicateCompanyException {

        for (Company c: _companies.values()) {
            if (c.getName().equals(name)) {
                throw new DuplicateCompanyException(name);
            }
        }
        Company c = new Company(name);
        _companies.put(c.getId(), c);
    }

    /**
     * Returns a Company object from the system, given its name.
     *
     * @param name The name of the company to show.
     * @return The Company object associated with the given name.
     *
     * @throws UnknownCompanyException If no company is found with given name;
     */
    public Company getCompany(String name) throws UnknownCompanyException {
        for (Company c: _companies.values()) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        throw new UnknownCompanyException(name);
    }

    /**
     * Returns a list of all companies in system, orderer by id.
     * @return A list of all companies in system.
     */
    public List<Company> getCompanies() {
        return _companies.values().stream()
            .sorted(Comparator.comparing(Company::getId))
            .toList();
    }

    /**
     * Shows a list of all clients in a company.
     *
     * @return A list of all clients inside a company in the system;
     * @throws UnknownCompanyException if the company with given id doesn't exist in system.
     */
    public List<Client> getCompanyClients(int id) throws UnknownCompanyException {
        Company company = _companies.get(id);
        if (company == null) {
            throw new UnknownCompanyException(id);
        }
        return company.getClients().stream()
            .sorted(Comparator.comparing(Client::getEmail))
            .toList();
    }

    /**
     * Shows a list of all products in a company.
     *
     * @return A list of all products inside a company in the system;
     * @throws UnknownCompanyException if the company with given id doesn't exist in system.
     */
    public List<Product> getCompanyProducts(int id) throws UnknownCompanyException {
        Company company = _companies.get(id);
        if (company == null) {
            throw new UnknownCompanyException(id);
        }
        return company.getProducts().stream()
            .sorted(Comparator.comparing(Product::getId))
            .toList();
    }

    /**
     * Registers a Client into the system.
     *
     * @param name The name of the client to register;
     * @param email The email of the client to register;
     *
     * @throws InvalidClientException If the name or email is invalid, or if a client with
     *         the same email already exists.
     */
    public void registerClient(String name, String email) throws InvalidClientException {

        if (name == null || name.isEmpty() || email == null || email.isEmpty() ||
            _clients.containsKey(email)) {
            throw new InvalidClientException(email);
        }
        _clients.put(email, new Client(name, email));
    }

    /**
     * Returns a Client object from the system, given its email.
     *
     * @param email The email of the client to retrieve;
     * @return The Client object associated with the given email.
     *
     * @throws UnknownClientException If no client is found with the given email;
     */
    public Client getClient(String email) throws UnknownClientException {
        Client c = _clients.get(email);
        if (c == null) {
            throw new UnknownClientException(email);
        }
        return c;
    }

    /**
     * Returns a list of all clients in the system, sorted by name and email.
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
     * Returns the basket of a client at a company, creating it empty if it does not exist yet.
     *
     * @param clientId  the id (email) of the client;
     * @param companyId the id of the company;
     *
     * @throws UnknownClientException  if there is no such client;
     * @throws UnknownCompanyException if there is no such company;
     */
    Basket getBasket(String clientId, int companyId)
            throws UnknownClientException, UnknownCompanyException {
        Client client = _clients.get(clientId);
        Company company = _companies.get(companyId); //getCompany receives a String
        return client.hasBasketFor(company);
    }

    /**
     * Returns a condition check to see if A client has a basket for a given company.
     *
     * @param clientId the id (email) of the client;
     * @param companyId the id of the company;
     *
     * @throws UnknownClientException  if there is no such client;
     * @throws UnknownCompanyException if there is no such company;
     */
    boolean hasBasket(String clientId, int companyId)
        throws UnknownClientException, UnknownCompanyException {
        return getClient(clientId).hasBasketFor(get(companyId));
    }

    /**
    * Updates a stock with the parameters, updating product quantities, and the given
    * company factor for the product sell price.
    *
    * @param companyId Id to the company we want to change stock information;
    * @param productId Id to the product we want to change the stock from;
    * @param units Amount of items to change in the given product;
    * @param factor A new factor value to give to the company for this product.
    *
    * @throws UnknownCompanyException if the company Id couldn't be found in system;
    * @throws UnknownProductException if the productId couldn't be found in system;
    * @throws InventoryChangeFailedException if something failed and changeInventory() returned false.
    */
    public void updateStock(int companyId, int productId, int units, double factor)
        throws UnknownCompanyException, UnknownProductException, InventoryChangeFailedException {

        Company company = _companies.get(companyId);
        Product product = _products.get(productId);

        if (company == null) {
            throw new UnknownCompanyException(companyId);
        }
        if (product == null) {
            throw new UnknownProductException(productId);
        }
        if (!company.changeInventory(product, units, factor)) {
            throw new InventoryChangeFailedException();
        }
    }

    /**
     * Returns the current date.
     */
    int currentDate() {
        return _date.getCurrentDate();
    }

    /** Advances N days, one day at a time, does nothing if days are negative since it can only advance.
     * Returns true if it advanced.
     *
     * @param days the days to advance the current date;
    */
    boolean advanceDate(int days) {
        if (days <= 0)
            return false;
        for (int i = 0; i < days; i++)
            nextDay();
        return true;
    }

    /**
     * Saves the next day as a new date (mainly build for later order class changes)
     */
    private void nextDay() {
        _date.advanceDate();
        // order code parts, only for the last instalment

    }

    /**
     * Registers a Shipping Rate from the given parameters.
     *
     * @param
     */
    public void registerShippingRate() {
        //FIXME implement logic here
    }
}
