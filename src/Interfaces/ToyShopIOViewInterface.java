package Interfaces;

import Exceptions.ScannerOperationErrorException;

/**
 * Интерфейс PhonebookIOViewInterface определяет методы для взаимодействия с пользователем в приложении для работы с ToySHop.
 */
public interface ToyShopIOViewInterface {
    /**
     * Метод inputRequest() запрашивает у пользователя ввод данных для добавления Program.ToyShop.
     *
     * @return строку, содержащую введенные пользователем данные
     * @throws ScannerOperationErrorException если произошла ошибка при работе с объектом Scanner
     */
    String inputRequest() throws ScannerOperationErrorException;

    /**
     * Метод showMessage() выводит сообщение для пользователя.
     *
     * @param msg строка, содержащая сообщение
     */
    void showMessage(String msg);

    /**
     * Метод showError() выводит сообщение об ошибке для пользователя.
     *
     * @param msg строка, содержащая сообщение об ошибке
     */
    void showError(String msg);
}
