package Exceptions;

import java.util.Scanner;

/**
 * Исключение, которое возникает при ошибке в операции с объектом {@link Scanner}.
 */
public class ScannerOperationErrorException extends RuntimeException {
    /**
     * Конструктор исключения.
     *
     * @param message сообщение об ошибке
     */
    public ScannerOperationErrorException(String message) {
        super(message);
    }
}
