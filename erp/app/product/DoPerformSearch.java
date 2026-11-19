package erp.app.product;

import java.util.List;

import erp.core.ErpManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;

/**
 * Searches the product registry by one of the criteria the core offers.
 */
class DoPerformSearch extends Command<ErpManager> {

    DoPerformSearch(ErpManager receiver) {
        super(Label.PERFORM_SEARCH, receiver);
    }

    @Override
    protected final void execute() {

        String criterion = Form.requestString(Prompt.searchCriterion());
        String term = Form.requestString(Prompt.searchTerm());

        List<String> results = _receiver.searchProducts(criterion, term);
        if (!results.isEmpty()) {
            for (String line : results) {
                _display.addLine(line);
            }
            _display.display();
        }
    }
}