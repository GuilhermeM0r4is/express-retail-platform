package erp.app.company;

import erp.core.ErpManager;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * Show all companies (ordered by id).
 */
class DoShowCompanies extends Command<ErpManager> {

    DoShowCompanies(ErpManager receiver) {
        super(Label.SHOW_COMPANIES, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        for (String company : _receiver.showCompanies()) {
            _display.addLine(company);
        }
        _display.display();
    }
}
