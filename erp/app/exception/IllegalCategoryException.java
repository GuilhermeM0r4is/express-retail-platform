package erp.app.exception;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Represents an error indicating that there was an error while
 * choosing a Product category.
 */
public class IllegalCategoryException extends CommandException {

  @java.io.Serial
  private static final long serialVersionUID = 202607021200L;

  private static final String ERROR_MESSAGE = "Escolhe uma de três categorias: ALIMENTAR, ELECTRONICA, VESTUARIO";

  public IllegalCategoryException() {
    super(ERROR_MESSAGE);
  }
}
