package erp.core.exception;

/**
 * Exists to check and assure this product with this specific Id exists, the system this
 * way can easily detect like ghost products. This way if we're looking for a product
 * with a specific id and it doesn't exist, it gives this error.
 */
public class UnknownProductException extends Exception {

    @java.io.Serial
    private static final long serialVersionUID = 202607021200L;

    private static final String ERROR_MESSAGE = "Erro ao encontrar o produto: ";

    public UnknownProductException(String name) {
        super(ERROR_MESSAGE + name);
    }

    public UnknownProductException(String name, Exception cause) {
        super(ERROR_MESSAGE + name, cause);
    }
}
