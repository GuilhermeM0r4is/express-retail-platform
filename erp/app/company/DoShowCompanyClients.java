package erp.app.company;

import erp.core.ErpManager;
import erp.app.exception.UnknownCompanyKeyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

/**
 * 4.2. Show the clients that have interacted with a company (ordered by id).
 */
class DoShowCompanyClients extends Command<ErpManager> {

    DoShowCompanyClients(ErpManager receiver) {
        super(Label.SHOW_COMPANY_CLIENTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        int companyId = Form.requestInteger(Prompt.companyId());

        try {
            for (String clientStr : _receiver.showCompanyClients(companyId)) {
                _display.addLine(clientStr);
            }
            _display.display();
        } catch (UnknownCompanyKeyException e) {
            throw new UnknownCompanyKeyException(companyId);
        }
    }
}
