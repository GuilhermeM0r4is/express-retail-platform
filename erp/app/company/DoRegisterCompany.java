package erp.app.company;

import erp.core.ErpManager;
import erp.app.exception.CompanyRegistrationFailedException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoRegisterCompany extends Command<ErpManager> {

    DoRegisterCompany(ErpManager receiver) {
        super(Label.REGISTER_COMPANY, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        String companyName = Form.requestString(Prompt.companyName());

        try {
            _receiver.registerCompany(companyName);
            _display.popup(Message.registrationSuccessful(companyName));
        } catch (CompanyRegistrationFailedException e) {
            throw new CompanyRegistrationFailedException(companyName);
        }
    }
}
