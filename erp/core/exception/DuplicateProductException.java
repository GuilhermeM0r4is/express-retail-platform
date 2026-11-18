package erp.core.exception;

/**
 * We got to have a way to assure there's no duplicated product when creating one,
 * so we check if the name already exists.
 */
public class DuplicateProductException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "O produto já existe com informação: ";

    public DuplicateProductException(String name) {
        super(ERROR_MESSAGE + name);
    }

    public DuplicateProductException(String name, Exception cause) {
        super(ERROR_MESSAGE + name, cause);
    }
}
