package erp.app.client;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all clients (ordered lexicographically by name).
 * 
 * @throws UnknownClientKeyException if the client id is not found in the system.
 */
class DoShowClients extends Command<ErpManager> {

  DoShowClients(ErpManager receiver) {
    super(Label.SHOW_CLIENTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    for (String client : _receiver.showClients())
      _display.addLine(client);
    _display.display();
  }
}
