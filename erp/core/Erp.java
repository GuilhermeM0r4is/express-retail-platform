package erp.core;

import erp.core.exception.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
// FIXME maybe import classes

/**
 * The whole ERP system: holds all the state and the domain logic.
 */
class Erp implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202608261200L;

    // class attributes

    /* we decided to use hashmaps, as most other methods look for ids too, so we can save
       up time, using O(1) searches rather than O(n) during the search loops */
    private Map<Integer, Company> _companies = new HashMap<>();
    private Map<Integer, Product> _products = new HashMap<>();

    //FIXME maybe implement constructor
    // <----- could this be an empty constructor as all important things are predefined?

    /**
    * Read text input file at the beginning of the program and populates the
    * the state of this application with the domain entities represented in the text file.
    *
    * @param filename name of the text input file to process
    * @throws UnrecognizedEntryException if some entry is not correct
    * @throws IOException if there is an IO erro while processing the text file
    **/
    void importFile(String filename) throws UnrecognizedEntryException, IOException
        /* FIXME maybe other exceptions */ {

        Parser parser = new Parser(this);
        parser.parseFile(filename);


    // all class implementation methods
    public void registerCompany(String name) {
        Company c = new Company(name);
        _companies.put(c.getId(), c);
    }

    public void registerImportedProduct(String name, int precoBase, ProductCategory category,
                                        int baseDeliveryTime, String country, double tax) {

        ImportedProduct impP = new ImportedProduct(name, precoBase, category,
                                                   baseDeliveryTime, country, tax);
        _products.put(impP.getId(), impP;  // <--- assure that its for products
                                           // and not for a specific hashmap different
    }

    public void registerNationalProduct(String name, int precoBase,
                                        ProductCategory category, int baseDeliveryTime) {

        NationalProduct natP = new NationalProduct(name, precoBase, category, baseDeliveryTime);
        _products.put(natP.getId(), natP);
    }

    public void registerClient() {
        //FIXME implement logic here
    }

    public void updateStock() {
        //FIXME implement logic here
    }

    public void registeShippingRate() {
        //FIXME implement logic here
    }
}
