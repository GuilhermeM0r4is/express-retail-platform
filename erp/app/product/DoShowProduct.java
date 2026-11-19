package erp.app.product;

import erp.core.ErpManager;
import erp.core.Product;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

import erp.app.exception.ProductNotFoundException;

/**
 * Show a specific product.
 */
class DoShowProduct extends Command<ErpManager> {

    DoShowProduct(ErpManager receiver) {
        super(Label.SHOW_PRODUCT, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        String productName = Form.requestString(Prompt.productName());

        try {
            Product product = _receiver.showProduct(productName);
            _display.popup(product));
        } catch (UnknownProductException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
