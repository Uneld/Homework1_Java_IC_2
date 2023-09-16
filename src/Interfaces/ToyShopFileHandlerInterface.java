package Interfaces;

import Exceptions.ErrorWriteFileException;
import ToyData.Toy;

/**
 * Интерфейс для работы с файлами.
 */
public interface ToyShopFileHandlerInterface {
    /**
     * Метод для записи данных в файл.
     *
     * @param toy - данные игрушки для записи в файл.
     * @throws ErrorWriteFileException - исключение, которое выбрасывается при ошибке записи в файл.
     */
    void writeToFile(Toy toy) throws ErrorWriteFileException;
}
