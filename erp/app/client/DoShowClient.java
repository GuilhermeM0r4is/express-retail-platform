package erp.app.client;

import erp.app.client.Prompt;
import erp.app.exception.UnknownClientKeyException;
import erp.core.ErpManager;
import erp.core.exception.UnknownClientException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show a specific client. Asks the id of the client to show, and then show it to the user.
 * 
 * @throws UnknownClientKeyException if the client id is not found in the system.
 */
class DoShowClient extends Command<ErpManager> {

    DoShowClient(ErpManager receiver) {
        super(Label.SHOW_CLIENT, receiver);
    addStringField("id", Prompt.clientId());
    }

    @Override
    protected final void execute() throws CommandException {
        String id = stringField("id");
        try {
            _display.popup(_receiver.showClient(id));
        } catch (UnknownClientException e) {
            throw new UnknownClientKeyException(id);
        }
    }
}
