package erp.core;

import erp.core.exception.*;
import java.io.*;
import java.util.List;

/**
 * The façade of the ERP core: the single entry point through which the
 * application interacts with the domain.
 *
 * <p><b>Façade</b> pattern.</p>
 */
public class ErpManager {

    /** The object doing all the actual work. */
    private Erp _erp = new Erp();
    private String _filename;
    private boolean _changed;   // dirty flag that assures app changes

    /**
     * Class constructor, creates a ErpManager.
     * @param erp An Erp, so ErpManager can work as a façade.
     */
    public ErpManager(Erp erp) {
        _erp = erp;
        _filename = "";
        _changed = false;
    }

    /**
     * Constructor without arguments for App.java.
     */
    public ErpManager() {
        this(new Erp());
    }

    /**
    * Saves the serialized application's state into the file associated to the current Erp
    * application
    *
    * @throws FileNotFoundException if for some reason the file cannot be created or opened.
    * @throws MissingFileAssociationException if the current library does not have a file.
    * @throws IOException if there is some error while serializing the state of the network to disk.
    **/
    public void save() throws MissingFileAssociationException, IOException {
        //nothing to save
        if (!_changed) {
            return;
        }

        if (_filename == null || _filename.trim().isEmpty()) {
            throw new MissingFileAssociationException();
        }

        // assures the logic behind saving in the RAM memory
        try (ObjectOutputStream out = new ObjectOutputStream(
                                            new BufferedOutputStream(
                                            new FileOutputStream(_filename)))) {

            out.writeObject(_erp);   // writes Object Erp into disk
        }
        _changed = false;
    }


    /**
    * Saves the serialized application's state into the specified file. The current Erp
    * application is associated to this file.
    *
    * @param filename the name of the file.
    * @throws FileNotFoundException if for some reason the file cannot be created or opened.
    * @throws IOException if there is some error while serializing the state of the network to disk.
    **/
    public void saveAs(String filename) throws IOException {
        //to restore _filename if something goes wrong
        String previousFile = _filename;
        boolean previousChanged = _changed;
        _filename = filename;
        _changed = true;

        try {
            save();
        } catch (MissingFileAssociationException e) {
            _filename = previousFile;
            throw new IOException(e);
        } catch (IOException e) {
            _filename = previousFile;
            _changed = previousChanged;
            throw e;
        }
    }

    /**
    * Loads the previously serialized application's state as set it as the current Erp
    * application.
    *
    * @param filename name of the file containing the serialized application's state
    *        to load.
    * @throws UnavailableFileException if the specified file does not exist or there is
    *         an error while processing this file.
    **/
    public void load(String filename) throws UnavailableFileException {
        try (ObjectInputStream in = new ObjectInputStream(
                                    new BufferedInputStream(
                                    new FileInputStream(filename)))) {
            Erp erp = (Erp) in.readObject();
            _erp = erp;
            _erp.syncNextIds();
            _filename = filename;
            _changed = false;
        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename); //exception added
        }
    }

    /**
    * Read text input file and initializes the current application (which should be empty)
    * with the domain entities representeed in the import file.
    *
    * @param datafile name of the text input file
    * @throws ImportFileException if some error happens during the processing of the
    *         import file.
    */
    public void importFile(String filename) throws ImportFileException {
        try {
            Parser parser = new Parser(this);
            parser.parseFile(filename);
            _changed = true;
        }
        catch (IOException | UnrecognizedEntryException e) {
            throw new ImportFileException(filename, e);
        }
    }

    /**
     * Getter function to check ErpManager dirty flag to see if methods
     * changed something and now app needs saving.
     * @return true if something changed, false otherwise.
     */
    public boolean getChanged() {
        return _changed;
    }

    /**
     * Returns a Product object from the system, sends to Erp.
     *
     * @param id The id of the Product to show.
     * @throws UnknownProductException If no Product is found with given id;
     *
     * @return The Product object associated with the given id.
     */
    public String showProduct(int id) throws UnknownProductException {
        return _erp.getProduct(id).toString();
    }

    /**
     * Shows a list of all Products in the system, sorted by name, sends to Erp.
     * @return A list of all Products in the system in String format;
     */
    public List<String> showProducts() {
        return _erp.getProducts().stream().map(Product::toString).toList();
    }

    /**
     * Registers an ImportedProduct, sends to Erp.
     *
     * @param name A name/description for the product;
     * @param basePrice Base price for the product;
     * @param category A given category from ProductCategory enumerate;
     * @param baseDeliveryTime Expected base delivery time as an int;
     * @param country The country of the ImportedProduct;
     * @param tax The tax percentage of the given country.
     *
     * @return id The id of the new company ImportedProduct Registered.
     */
    public int registerImportedProduct(String name, int basePrice, ProductCategory category,
                                        int baseDeliveryTime, String country, double tax)
                                        throws InvalidProductException {

        int id = _erp.registerImportedProduct(name, basePrice, category, baseDeliveryTime, country, tax);
        _changed = true;
        return id;

    }

    /**
     * Registers a NationalProduct, sends to Erp.
     *
     * @param name A name/description for the product;
     * @param basePrice Base price for the product;
     * @param category A given category from ProductCategory enumerate;
     * @param baseDeliveryTime Expected base delivery time as an int;
     *
     * @return id The id of the new company ImportedProduct Registered.
     */
    public int registerNationalProduct(String name, int basePrice,
                                        ProductCategory category, int baseDeliveryTime)
                                        throws InvalidProductException {

        int id = _erp.registerNationalProduct(name, basePrice, category, baseDeliveryTime);
        _changed = true;
        return id;
    }

    /**
    * Performs a search for all products inside system based on criterion type and a given term,
    * sends to Erp.
    *
    * @param criterion Type of three possible criterions to search for;
    * @param term A given String that we assure it's equal to the Product criterion.
    *
    * @returns A List containing all products using the toString() showcase.
    */
    public List<String> searchProducts(String criterion, String term) {
        return _erp.searchProducts(criterion, term);
    }


    /**
     * Registers a new Company by its name, sends to Erp.
     *
     * @param name A name to give to the new company.
     *
     * @return id The new registered Company Id.
     */
    public int registerCompany(String name) throws InvalidCompanyException {

        int id = _erp.registerCompany(name);
        _changed = true;
        return id;
    }

    /**
     * Returns a Company object from the system, sends to Erp.
     *
     * @param id The identifier of the company to show.
     * @throws UnknownCompanyException If no company is found with given id;
     *
     * @return The Company object associated with the given id.
     */
    public String showCompany(int id) throws UnknownCompanyException {
        return _erp.getCompany(id).toString();
    }

    /**
     * Shows a list of all companies in the system, sorted by name, sends to Erp.
     * @return A list of all companies in the system in String format;
     */
    public List<String> showCompanies() {
        return _erp.getCompanies().stream().map(Company::toString).toList();
    }

    /**
     * Shows a list of all clients in a company, sends to Erp.
     *
     * @return A list of all clients inside a company in the system in String format;
     * @throws UnknownCompanyException if the company with given id doesn't exist in system.
     */
    public List<String> showCompanyClients(int id) throws UnknownCompanyException {
        return _erp.getCompanyClients(id).stream().map(Client::toString).toList();
    }

    /**
     * Shows a list of all products in a company, sends to Erp.
     *
     * @return A list of all products inside a company in the system;
     * @throws UnknownCompanyException if the company with given id doesn't exist in system.
     */
    public List<String> showCompanyProducts(int id) throws UnknownCompanyException {
        return _erp.getCompanyProducts(id).stream().map(SuppliedProduct::toString).toList();
    }

    /**
     * Registers a Client into the system, sends to Erp.
     *
     * @param name The name of the client to register;
     * @param email The email of the client to register;
     */
    public void registerClient(String name, String email) throws InvalidClientException,
                                DuplicateClientException {
        _erp.registerClient(name, email);
        _changed = true;
    }

    /**
     * Returns a Client object from the system, given its email, sends to Erp.
     *
     * @param email The email of the client to retrieve;
     * @return The Client object associated with the given email;
     */
    public String showClient(String email) throws UnknownClientException {
        return _erp.getClient(email).toString();
    }

    /**
     * Returns a list of all clients in the system, sorted by name and email, sends to Erp.
     * @return A list of all clients in the system;
     */
    public List<String> showClients() {
        return _erp.getClients().stream().map(Client::toString).toList();
    }

    /**
     * Returns the information that the basket currently contains about it's client and company.
     *
     * @param clientId the id of the client;
     * @param companyId the company id;
     */
    public Basket getBasket(String clientId, int companyId)
        throws UnknownClientException, UnknownCompanyException {
        boolean existed = _erp.hasBasket(clientId, companyId);
        Basket basket = _erp.getBasket(clientId, companyId);
        if (!existed) {
            _changed = true;
        }
        return basket;
    }

    /**
    * Updates a stock with the parameters, sends to Erp.
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

        _erp.updateStock(companyId, productId, units, factor);
        _changed = true;
    }

    /**
     * Returns the current date.
     */
    public int currentDate() {
        return _erp.currentDate();
    }

    /**
     * Advances N days, one day at a time, does nothing if days are negative since it can only advance.
     *
     * @param days the days to advance the current date;
     */
    public void advanceDate(int days) {
        if (_erp.advanceDate(days)) {
            _changed = true;
        }
    }

    /**
     * Registers a Shipping Rate, sends to Erp.
     *
     * @param
     */
    public void registerShippingRate() {
        //FIXME implement logic here
    }
}
