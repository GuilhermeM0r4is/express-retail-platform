package erp.app.product;

import erp.core.ErpManager;

import pt.tecnico.uilib.menus.DisplayCommandException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

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
            _display.popup(_receiver.showProduct(productName));
        } catch (UnknownProductException e) {
            throw new DisplayCommandException(e.getMessage());
        }
    }
}
