package erp.app.company;

import erp.core.ErpManager;
import erp.app.product.Prompt;
import erp.app.exception.UnknownCompanyKeyException;
import erp.app.exception.UnknownProductKeyException;
import erp.app.exception.NotEnoughInventoryException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoChangeProductInventory extends Command<ErpManager> {

    DoChangeProductInventory(ErpManager receiver) {
        super(Label.CHANGE_PRODUCT_INVENTORY, receiver);
    }

    @Override
    protected final void execute() throws CommandException {

        int companyId = Form.requestInteger(erp.app.company.Prompt.companyId());
        int productId = Form.requestInteger(Prompt.productId());
        int units = Form.requestInteger(erp.app.company.Prompt.amountToUpdate());
        double factor = Form.requestReal(erp.app.company.Prompt.priceFactor());

        try {
            _receiver.updateStock(companyId, productId, units, factor);
            _display.popup(Message.supplySuccessful(companyId, units));
        } catch (UnknownCompanyKeyException e) {
            throw new UnknownCompanyKeyException(companyId);
        } catch (UnknownProductKeyException e) {
            throw new UnknownProductKeyException(productId);
        } catch (NotEnoughInventoryException e) {
            throw new NotEnoughInventoryException(productId);
        }
    }
}
