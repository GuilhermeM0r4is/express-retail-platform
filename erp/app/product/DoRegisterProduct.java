package erp.app.product;

import erp.core.ErpManager;
import erp.core.ProductCategory;
import erp.app.exception.ProductRegistrationFailedException;
import erp.app.exception.IllegalCategoryException;
import erp.core.exception.InvalidProductException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * Registers a product in the system's global registry. The extra fields of the
 * imported type are only asked for once that type is chosen, so a new product
 * type only adds its own branch here.
 */
class DoRegisterProduct extends Command<ErpManager> {

    public DoRegisterProduct(ErpManager receiver) {
        super(Label.REGISTER_PRODUCT, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        String type = Form.requestString(Prompt.productType());
        String name = Form.requestString(Prompt.productName());
        int price = Form.requestInteger(Prompt.basePrice());

        ProductCategory category = null;
        while (category == null) {
            String categoryStr = Form.requestString(Prompt.productCategory());
            try {
                category = ProductCategory.valueOf(categoryStr);
            } catch (IllegalArgumentException e) {
                throw new IllegalCategoryException();
            }
        }

        int delay = Form.requestInteger(Prompt.baseDelay());

        if (!"NACIONAL".equals(type) && !"IMPORTADO".equals(type)) {
            throw new ProductRegistrationFailedException(name);
        }

        try {
            int id;
            if ("IMPORTADO".equals(type)) {
                String country = Form.requestString(Prompt.originCountry());
                double tax = Form.requestReal(Prompt.customsTax());
                id = _receiver.registerImportedProduct(name, price, category, delay, country, tax);
            } else {
                id = _receiver.registerNationalProduct(name, price, category, delay);
            }
            _display.popup(Message.registrationSuccessful(id));
        } catch (InvalidProductException e) {
            throw new ProductRegistrationFailedException(name);
        }
    }
}
