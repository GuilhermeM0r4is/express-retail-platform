package erp.core.exception;

/**
 * This exception assures that when creating a company, there's an exception that
 * the problem is in the construction of the company class, while creating and
 * validating it. For example some ilogical parameters.
 */
public class InvalidCompanyException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "Erro ao validar os parametros da empresa: ";

    public InvalidCompanyException(String name) {
        super(ERROR_MESSAGE + name);
    }
}
