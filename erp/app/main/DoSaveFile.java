package erp.app.main;

import java.io.IOException;

import erp.core.ErpManager;
import erp.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form; //FIXME
import pt.tecnico.uilib.menus.Command;

/**
 * Saves the current state of the application to a file using the serialization
 * mechanism. If no file is currently associated with the application, asks the user for
 * the name of the file to save to.
 */
class DoSaveFile extends Command<ErpManager> {

    DoSaveFile(ErpManager receiver) {
        super(Label.SAVE_FILE, receiver);
    }

    @Override
    protected final void execute() {
        saveWithPrompt(_receiver);
    }

    /** Saves to the associated file; if there is none, asks for a name first. */
    static void saveWithPrompt(ErpManager manager) {
        try {
            try {
                manager.save();
            } catch (MissingFileFileAssociationException e) {
                manager.saveAs(Form.requestString(Prompt.newSaveAs()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
