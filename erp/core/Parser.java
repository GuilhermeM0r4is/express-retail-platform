package erp.core;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import erp.core.exception.UnrecognizedEntryException;
import erp.core.exception.UnknownProductException;
import erp.core.exception.DuplicateProductException;
import erp.core.exception.UnknownCompanyException;
import erp.core.exception.DuplicateCompanyException;
import erp.core.exception.InvalidClientException;
import erp.core.exception.UnknownClientException;

class Parser {

    private ErpManager _manager;

    Parser(ErpManager manager) {
        _manager = manager;
    }

    void parseFile(String fileName) throws IOException, UnrecognizedEntryException {
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = in.readLine()) != null)
            parseLine(line);
        }
    }

    private void parseLine(String line) throws UnrecognizedEntryException {
        String[] components = line.split(":");

        try {
            switch(components[0]) {
                case "COMPANY":
                    parseCompany(components, line);
                    break;
                case "IMPORTED":
                    parseImported(components, line);
                    break;
                case "NATIONAL":
                    parseNational(components, line);
                    break;
                case "CLIENT":
                    parseClient(components, line);
                    break;
                case "SUPPLY":
                    parseSupply(components, line);
                    break;
                case "RATE":
                    parseRate(components, line);
                    break;
                default:
                    throw new UnrecognizedEntryException("Entrada inválida: " + line);
            }
        }
        // list of all exceptions that can be used during parsing
        catch (NumberFormatException | IllegalArgumentException |
                UnknownProductException | DuplicateProductException |
                UnknownCompanyException | DuplicateCompanyException |
                InvalidClientException | UnknownClientException e) {

            throw new UnrecognizedEntryException("Erro a processar linha: " + line " Erro = " + e.getMessage());
        }
    }

    private void checkCorrectNumberOfFields(String[] components, int expectedSize, String line)
        throws UnrecognizedEntryException {
            if (components.length != expectedSize)
            throw new UnrecognizedEntryException("Número inválido de campos (" + components.length + ") na linha: " + line);
    }

    // COMPANY:nome
    private void parseCompany(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 2, line);
        _manager.registerCompany(components[1]);
    }

    // IMPORTED:nome:precoBase:categoria:prazo:paisOrigem:taxa
    private void parseImported(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 7, line);
        _manager.registerImportedProduct(components[1], Integer.valueOf(components[2]), components[3],
                                        Integer.valueOf(components[4]), components[5], Double.valueOf(components[6]));
    }


    // NATIONAL:nome:precoBase:categoria:prazo
    private void parseNational(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 5, line);
        _manager.registerNationalProduct(components[1], Integer.valueOf(components[2]), components[3],
                                        Integer.valueOf(components[4]));
    }

    // CLIENT:nome:email
    private void parseClient(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 3, line);
        _manager.registerClient(components[1], components[2]);
    }

    // SUPPLY:idEmpresa:idProduto:unidades:factor
    private void parseSupply(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 5, line);
        _manager.updateStock(Integer.valueOf(components[1]), Integer.valueOf(components[2]),
                            Integer.valueOf(components[3]), Double.valueOf(components[4]));
    }

    // RATE:modalidade:custo:dias;
    private void parseRate(String[] components, String line) throws UnrecognizedEntryException {
        checkCorrectNumberOfFields(components, 4, line);
        _manager.registeShippingRate(components[1], Integer.valueOf(components[2]), Integer.valueOf(components[3]));
    }
}
