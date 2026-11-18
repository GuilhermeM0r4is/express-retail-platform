package erp.core.exception;

/**
 * Exists to check and assure this company with this specific Id exists, the system this
 * way can easily detect like ghost companies. This way if we're looking for a company
 * with a specific id and it doesn't exist, it gives this error.
 */
public class UnknownCompanyException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "Erro ao encontrar a empresa com ID: ";

    public UnknownCompanyException(int id) {
        super(ERROR_MESSAGE + id);
    }

    public UnknownCompanyException(int id, Exception cause) {
        super(ERROR_MESSAGE + id, cause);
    }
}
