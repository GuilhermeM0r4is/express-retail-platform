package erp.app.product;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * Registers a product in the system's global registry. The extra fields of the
 * imported type are only asked for once that type is chosen, so a new product
 * type only adds its own branch here.
 */
class DoRegisterProduct extends Command<ErpManager> {

    DoRegisterProduct(ErpManager receiver) {
        super(Label.REGISTER_PRODUCT, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        String productType = Form.requestString(Prompt.productType());
        String productName = Form.requestString(Prompt.productName());

        int BasePrice = Integer.requestInt(Prompt.basePrice());
        ProductCategory productCategory = ProductCategory.valueOf(
                                                    Form.requestString(
                                                    Prompt.productCategory()));

        int BaseDeliveryTime = Integer.requestInt(Prompt.baseDelay());
        String originCountry = null;
        double customTax = 0.00;

        if (productType.equals("IMPORTADO")) {
            originCountry = Form.requestString(Prompt.originCountry());
            customTax = Double.requestDouble(Prompt.customsTax());
        }
        try {
            if ("IMPORTADO".equals(productType)) {
                _receiver.registerImportedProduct(productName, basePrice, productCategory,
                                                  baseDeliveryTime, originCountry, customsTax);
            } else {
                _receiver.registerNationalProduct(productName, basePrice, productCategory,
                                                  baseDeliveryTime);
            }
            _display.popup(Message.registrationSuccessful());
        }
        catch (ProductRegistrationFailedException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
