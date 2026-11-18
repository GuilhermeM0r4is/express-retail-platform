package erp.core;

import erp.core.exception.*;
import java.io.*;
//FIXME maybe import classes

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
    * Saves the serialized application's state into the file associated to the current Erp
    * application
    *
    * @throws FileNotFoundException if for some reason the file cannot be created or opened.
    * @throws MissingFileAssociationException if the current library does not have a file.
    * @throws IOException if there is some error while serializing the state of the network to disk.
    **/
    public void save() throws MissingFileAssociationException, IOException {

        if (_filename == null || _filename.trim().isEmpty()) {
            throw new MissingFileAssociationException();
        }

        // assures the logic behind saving in the RAM memory
        if (_changed) {
            try (ObjectOutputStream out = new ObjectOutputStream(
                                            new BufferedOutputStream(
                                            new FileOutputStream(_filename)))) {

                out.writeObject(_erp);   // writes Object Erp into disk
            }
            _changed = false;
        }
    }

    /**
    * Saves the serialized application's state into the specified file. The current Erp
    * application is associated to this file.
    *
    * @param filename the name of the file.
    * @throws FileNotFoundException if for some reason the file cannot be created or opened.
    * @throws MissingFileAssociationException if the current library does not have a file.
    * @throws IOException if there is some error while serializing the state of the network to disk.
    **/
    public void saveAs(String filename) throws MissingFileAssociationException, IOException {
        _filename = filename;
        _changed = true;

        try {
            save();
        }
        // this catch won't happen but compiler needs it
        catch (MissingFileAssociationException e) {
            throw new IOException(e);
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

        // to read an object from a file, we have to connect three pipes into eachother
        ObjectInputStream in = new ObjectInputStream(
                                new BufferedInputStream(
                                new FileInputStream(filename)));

        // reads the file and changes Erp from the current one
        _erp = (Erp) in.readObject();
        _filename = filename;
        _changed = false;
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
     * Registers an ImportedProduct, sends to Erp.
     *
     * @param name A name/description for the product;
     * @param basePrice Base price for the product;
     * @param category A given category from ProductCategory enumerate;
     * @param baseDeliveryTime Expected base delivery time as an int;
     * @param country The country of the ImportedProduct;
     * @param tax The tax percentage of the given country.
     */
    public void registerImportedProduct(String name, int basePrice, ProductCategory category,
                                        int baseDeliveryTime, String country, double tax)
                                        throws DuplicateProductException {

        _erp.registerImportedProduct(name, basePrice, category, baseDeliveryTime, country, tax);
        _changed = true;
    }

    /**
     * Registers a NationalProduct, sends to Erp.
     *
     * @param name A name/description for the product;
     * @param basePrice Base price for the product;
     * @param category A given category from ProductCategory enumerate;
     * @param baseDeliveryTime Expected base delivery time as an int;
     */
    public void registerNationalProduct(String name, int basePrice,
                                        ProductCategory category, int baseDeliveryTime)
                                        throws DuplicateProductException {

        _erp.registerNationalProduct(name, basePrice, category, baseDeliveryTime);
        _changed = true;
    }

    /**
     * Registers a new Company by its name, sends to Erp.
     * @param name A name to give to the new company.
     */
    public void registerCompany(String name) throws DuplicateCompanyException {
        _erp.registerCompany(name);
        _changed = true;
    }

    /**
     * Returns a Company object from the system, sends to Erp.
     *
     * @param name The name of the company to show.
     * @throws UnknownCompanyException If no company is found with given name;
     *
     * @return The Company object associated with the given name.
     */
    public Company showCompany(String name) throws UnknownCompanyException {
        return _erp.getCompany(name).toString();
    }

    /**
     * Returns a list of all companies in the system, sorted by name, sends to Erp.
     * @return A list of all companies in the system;
     */
    public List<String> showCompanies() {
        return _erp.getCompanies().stream().map(Company::toString).toList();
    }

    /**
     * Registers a Client into the system, sends to Erp.
     *
     * @param name The name of the client to register;
     * @param email The email of the client to register;
     */
    public void registerClient(String name, String email) throws InvalidClientException {
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
    public Basket getBasket(String clientId, int companyId) throws UnknownClientException, UnknownCompanyException {
        boolean existed = _erp.hasBasket(clientId, companyId); 
        Basket basket = _erp.getBasket(clientId, companyId);
        if (!existed) {
            _changed = true;
        } 
        return basket;
    }

    /**
     * Sends to Erp to update a stock with the parameters.
     *
     * @param
     */
    public void updateStock() {
        //FIXME implement logic here
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
