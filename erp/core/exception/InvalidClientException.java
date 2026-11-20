package erp.core.exception;

/**
 * This exception assures that when creating a client, there's an exception that
 * the problem is in the construction of the client class, while creating and
 * validating it. For example some ilogical parameters.
 */
public class InvalidClientException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "Erro ao validar os parametros do cliente: ";

    public InvalidClientException(String email) {
        super(ERROR_MESSAGE + email);
    }
}
