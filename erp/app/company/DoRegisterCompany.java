package erp.app.company;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import erp.core.exception.DuplicateCompanyException;

/**
 * Register a new company.
 */
class DoRegisterCompany extends Command<ErpManager> {

    DoRegisterCompany(ErpManager receiver) {
        super(Label.REGISTER_COMPANY, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        int companyName = Form.requestString(Prompt.companyName());

        try {
            _receiver.registerCompany(companyName);
        } catch (DuplicateCompanyException e) {
              throw new CommandException(e.getMessage());
        }
    }
}
