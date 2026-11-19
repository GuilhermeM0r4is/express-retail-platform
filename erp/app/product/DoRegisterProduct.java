package erp.app.product;

import erp.core.ErpManager;
import erp.core.ProductCategory;

import pt.tecnico.uilib.menus.DisplayCommandException;
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
        ProductCategory category = ProductCategory.valueOf(Form.requestString(Prompt.productCategory()));
        int delay = Form.requestInteger(Prompt.baseDelay());

        try {
            if ("IMPORTADO".equals(type)) {
                String country = Form.requestString(Prompt.originCountry());
                double tax = Form.requestDouble(Prompt.customsTax());
                _receiver.registerImportedProduct(name, price, category, delay, country, tax);
            } else {
                _receiver.registerNationalProduct(name, price, category, delay);
            }
            _display.popup(Message.registrationSuccessful());
        }
        catch (ProductRegistrationFailedException e) {
            throw new DisplayCommandException(e.getMessage());
        }
    }
}
