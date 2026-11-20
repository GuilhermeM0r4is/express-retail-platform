package erp.core.exception;

/**
 * This exception assures that when creating a product, there's an exception that
 * the problem is in the construction of the product class, while creating and
 * validating it. For example some ilogical parameters.
 */
public class InvalidProductException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "Erro ao validar os parametros do produto: ";

    public InvalidProductException(String name) {
        super(ERROR_MESSAGE + name);
    }
}
