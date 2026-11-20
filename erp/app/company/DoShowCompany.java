package erp.app.company;

import erp.core.ErpManager;
import erp.app.exception.UnknownCompanyKeyException;
import erp.core.exception.UnknownCompanyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * Show a specific company.
 */
class DoShowCompany extends Command<ErpManager> {

    DoShowCompany(ErpManager receiver) {
        super(Label.SHOW_COMPANY, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        int id = Form.requestInteger(Prompt.companyId());

        try {
            _display.popup(_receiver.showCompany(id));
        } catch (UnknownCompanyException e) {
            throw new UnknownCompanyKeyException(id);
        }
    }
}
