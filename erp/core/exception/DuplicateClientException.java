package erp.core.exception;

/**
 * We got to have a way to assure there's no duplicated client when creating one,
 * so we check if the email already exists.
 */
public class DuplicateClientException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "O cliente já existe com informação: ";

    public DuplicateClientException(String email) {
        super(ERROR_MESSAGE + email);
    }

    public DuplicateClientException(String email, Exception cause) {
        super(ERROR_MESSAGE + email, cause);
    }
}
