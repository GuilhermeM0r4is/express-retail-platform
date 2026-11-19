package erp.core.exception;

/**
 * We got to have a way to assure there's no duplicated company when creating one,
 * so we check if the name already exists.
 */
public class DuplicateCompanyException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "A empresa já existe com informação: ";

    public DuplicateCompanyException(String name) {
        super(ERROR_MESSAGE + name);
    }

    public DuplicateCompanyException(String name, Exception cause) {
        super(ERROR_MESSAGE + name, cause);
    }
}
