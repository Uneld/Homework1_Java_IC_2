package Program;

import Exceptions.ScannerOperationErrorException;
import Interfaces.ToyShopIOViewInterface;

import java.util.Scanner;

/**
 * Класс для работы с вводом/выводом для Program.ToyShop.
 */
public class ToyShopIOView implements ToyShopIOViewInterface {
    private final Scanner scanner;

    /**
     * Конструктор класса. Инициализирует сканер для считывания данных с консоли.
     */
    public ToyShopIOView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Метод запрашивает у пользователя данные игрушки и возвращает их в виде строки.
     * Если при считывании данных произошла ошибка, выбрасывает исключение ScannerOperationErrorException.
     *
     * @return строку, содержащую введенные пользователем данные
     * @throws ScannerOperationErrorException если при считывании данных произошла ошибка
     */
    @Override
    public String inputRequest() throws ScannerOperationErrorException {
        try {
            return scanner.nextLine();
        } catch (RuntimeException e) {
            throw new ScannerOperationErrorException("Ошибка работы сканера");
        }
    }

    /**
     * Метод выводит на экран сообщение.
     *
     * @param msg сообщение для вывода на экран
     */
    @Override
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Метод выводит на экран сообщение об ошибке.
     *
     * @param msg сообщение об ошибке для вывода на экран
     */
    @Override
    public void showError(String msg) {
        System.err.println(msg);
    }
}

