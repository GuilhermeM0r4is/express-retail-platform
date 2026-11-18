package erp.app.company;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

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
        Company company = _receiver.showCompany(companyName);
        _display.popup(company.toString());
    } catch (UnknownCompanyException e) {
        throw new CommandException(e.getMessage());
    }
  }
}
