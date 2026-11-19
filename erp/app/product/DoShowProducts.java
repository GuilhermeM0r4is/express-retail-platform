package erp.app.product;

import erp.core.ErpManager;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;

/**
 * Show all products (ordered by id).
 */
class DoShowProducts extends Command<ErpManager> {

    DoShowProducts(ErpManager receiver) {
        super(Label.SHOW_PRODUCTS, receiver);
    }

    @Override
    protected final void execute() {
        for (String product : _receiver.showProducts()) {
            _display.addLine(product);
        }
        _display.display();
    }
}
