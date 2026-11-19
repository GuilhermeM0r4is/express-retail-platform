package erp.app.company;

import erp.core.ErpManager;

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
        String companyName = Form.requestString(Prompt.companyName());

        try {
            _display.popup(_receiver.showCompany(companyName));
        } catch (UnknownCompanyException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
