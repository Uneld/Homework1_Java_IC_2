package Exceptions;

/**
 * Исключение, которое возникает при ошибке записи в файл.
 */
public class ErrorWriteFileException extends Exception {
    /**
     * Getter абсолютного пути файла, в котором произошла ошибка.
     *
     * @return абсолютный путь
     */
    public String getFileName() {
        return path;
    }

    private final String path;

    /**
     * Конструктор исключения.
     *
     * @param message сообщение об ошибке
     * @param path    абсолютный путь файла, в котором произошла ошибка
     */
    public ErrorWriteFileException(String message, String path) {
        super(message);
        this.path = path;
    }
}
