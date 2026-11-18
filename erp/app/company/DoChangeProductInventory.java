package erp.app.company;

import erp.core.ErpManager;
import erp.core.exception.UnknownCompanyException;
import erp.core.exception.UnknownProductException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import erp.app.product.Prompt;

/**
 * Changes what a company holds of a product — the amount is added to its
 * inventory and the factor replaces the previous one — starting the supply if
 * the company did not offer the product yet.
 *
 * <p>Reaching zero units only makes the product unavailable at that company: the
 * product itself stays in the system.</p>
 */
class DoChangeProductInventory extends Command<ErpManager> {

    DoChangeProductInventory(ErpManager receiver) {
        super(Label.CHANGE_PRODUCT_INVENTORY, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        int companyId = Form.requestInteger(Prompt.companyId());
        int productId = Form.requestInteger(Prompt.productId());
        int units = Form.requestInteger(Prompt.amountToUpdate());
        double factor = Form.requestDouble(Prompt.priceFactor());

        try {
            _receiver.updateStock(companyId, productId, units, factor);
        } catch (UnknownCompanyException e) {
                throw new CommandException(e.getMessage());
        } catch (UnknownProductException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
