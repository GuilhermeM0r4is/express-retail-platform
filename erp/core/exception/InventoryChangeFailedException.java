package erp.core.exception;

/**
 * This exception assures that when updating a stock with a specific unit ammount
 * and factor, they are valid, if the updateStock() from Company method fails, returning
 * false, we can assume something went wrong with it.
 */
public class InventoryChangeFailedException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "Erro ao atualizar o stock do produto,"
                                                   + "quantidade ou fator inválidos.";

    public InventoryChangeFailedException() {
        super(ERROR_MESSAGE);
    }
}
