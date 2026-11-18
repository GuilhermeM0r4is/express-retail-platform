package erp.core.exception;

/**
 * Exists to check and assure this client with this specific information, the system this
 * way can easily detect like ghost clients. This way if we're looking for a client with
 * a given information and it isn't on the storage then we give this error.
 */
public class UnknownClientException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "Erro ao encontrar o cliente: ";

    public UnknownClientException(String email) {
        super(ERROR_MESSAGE + email);
    }

    public UnknownClientException(String email, Exception cause) {
        super(ERROR_MESSAGE + email, cause);
    }
}
