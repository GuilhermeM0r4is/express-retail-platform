package erp.app.company;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * Shows what a company supplies: product, units, price factor and selling price.
 */
class DoShowCompanyProducts extends Command<ErpManager> {

    DoShowCompanyProducts(ErpManager receiver) {
        super(Label.SHOW_COMPANY_CLIENTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        int companyId = Form.requestInteger(Prompt.companyId());

        try {
            for (String productStr : _receiver.showCompanyProducts(companyId)) {
                _display.addLine(productStr);
            }
            _display.display();
        } catch (UnknownCompanyException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
