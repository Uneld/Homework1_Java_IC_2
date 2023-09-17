package Program;

import Exceptions.*;
import Interfaces.ToyShopFileHandlerInterface;
import Interfaces.ToyShopIOViewInterface;
import Interfaces.ToyShopInterface;
import ToyData.Toy;

import java.util.Arrays;

/**
 * Класс ToyShopWorker представляет собой worker,
 * который обрабатывает запросы пользователя и взаимодействует
 * с моделью ToyShop и файловой системой ToyShopFileHandler.
 */
public class ToyShopWorker {
    private enum Command {
        YES, NO
    }

    private final int numberTries;
    ToyShopIOViewInterface ioView;
    ToyShopInterface toyShop;
    ToyShopFileHandlerInterface fileHandler;

    /**
     * Конструктор класса ToyShopWorker.
     *
     * @param ioView      объект, который реализует интерфейс ToyShopIOViewInterface
     *                    и используется для взаимодействия с пользователем.
     * @param toyShop     объект, который реализует интерфейс ToyShopInterface
     *                    и используется для работы с моделью ToyShop.
     * @param fileHandler объект, который реализует интерфейс ToyShopFileHandlerInterface
     *                    и используется для работы с файловой системой ToyShopFileHandler.
     * @param numberTries количество попыток для розыгрыша игрушек.
     */
    public ToyShopWorker(ToyShopIOViewInterface ioView, ToyShopInterface toyShop, ToyShopFileHandlerInterface fileHandler, int numberTries) {
        this.numberTries = numberTries;
        this.ioView = ioView;
        this.toyShop = toyShop;
        this.fileHandler = fileHandler;
    }

    /**
     * Метод process() обрабатывает запросы пользователя и взаимодействует
     * с моделью ToyShop и файловой системой ToyShopFileHandler.
     *
     * @throws ScannerOperationErrorException если происходит ошибка ввода-вывода при работе с консолью.
     */
    public void process() {
        try {
            int numberToyTypes = requestNumberToyTypes();

            requestToyTypes(numberToyTypes);
            showPrizeList();

            processChangeToyTypeWeight();

            writeFilePrizeToysSequence(); // по заданию
        } catch (ScannerOperationErrorException e) {
            ioView.showError(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Метод showPrizeList() выводит список призовых игрушек на экран.
     */
    private void showPrizeList() {
        ioView.showMessage("******Призовой лист******");
        ioView.showMessage(toyShop.getStringPrizeToys());
    }

    /**
     * Метод requestNumberToyTypes() запрашивает у пользователя количество типов игрушек,
     * которые он хочет добавить в магазин.
     *
     * @return количество типов игрушек.
     * @throws ScannerOperationErrorException если происходит ошибка ввода-вывода при работе с консолью.
     */
    private int requestNumberToyTypes() throws ScannerOperationErrorException {
        while (true) {
            ioView.showMessage("Введите сколько типов вы хотите добавить: ");
            String input = ioView.inputRequest();
            try {
                int number = Integer.parseInt(input);
                if (number > ToyShop.MAX_TYPES_OF_TOYS || number < 0) {
                    throw new ExceededNumberToyTypes("Превышено количество возможных типов или введено отрицательное значение.");
                }

                return number;
            } catch (NumberFormatException e) {
                ioView.showError("Не верный ввод. Введено: '" + input + '\'');
            } catch (ExceededNumberToyTypes e) {
                ioView.showError(e.getMessage());
            }
        }
    }

    /**
     * Метод requestToyTypes() запрашивает у пользователя данные о каждом типе игрушек,
     * который он хочет добавить в магазин.
     *
     * @param numberRequest количество типов игрушек, которые нужно добавить.
     * @throws ScannerOperationErrorException если происходит ошибка ввода-вывода при работе с консолью.
     */
    private void requestToyTypes(int numberRequest) throws ScannerOperationErrorException {
        int countRequest = 0;

        while (countRequest < numberRequest) {
            try {
                ioView.showMessage(countRequest + 1 + ". Вводите данные в формате 'id вес имя'," +
                        " где id > 0, 0 < weight < " + ToyRaffle.MAX_PERCENT_CHANCE);
                toyShop.put(ioView.inputRequest());
                countRequest++;
            } catch (WrongInputStringToy e) {
                ioView.showError(e.getMessage() + " Введенная строка: '" + e.getInputString() + '\'');
            } catch (ToyTypePresentInShop e) {
                ioView.showError(e.getMessage() + " Введенный тип: '" + e.getToy().toString() + '\'');
            }
        }
    }

    /**
     * Метод записывает в файл все игрушки из магазина игрушек.
     *
     * @throws ListOfPrizeToysIsEmpty  - если список игрушек приза пустой
     * @throws ErrorWriteFileException - если произошла ошибка при записи в файл
     */
    private void writeFilePrizeToysSequence() {
        try {
            ioView.showMessage("Запись в файл начата.");
            for (int i = 0; i < numberTries; i++) {
                Toy tempToy = toyShop.get();
                ioView.showMessage(tempToy.toString());
                fileHandler.writeToFile(tempToy);
            }
            ioView.showMessage("Запись в файл завершена.");
        } catch (ListOfPrizeToysIsEmpty e) {
            ioView.showError(e.getMessage());
        } catch (ErrorWriteFileException e) {
            ioView.showError(String.format("%s, Абсолютный путь: %s\n%n", e.getMessage() + Arrays.toString(e.getStackTrace()), e.getFileName()));
        }
    }

    /**
     * Метод запрашивает у пользователя команду изменения веса игрушек.
     *
     * @return команду изменения веса игрушек
     * @throws WrongCommand                   - если введена неверная команда
     * @throws ScannerOperationErrorException - если произошла ошибка при работе с объектом Scanner
     */
    private Command requestChangeToyTypeWeight() throws WrongCommand, ScannerOperationErrorException {
        ioView.showMessage("Вы хотите изменить веса игрушек? y/n");
        String input = ioView.inputRequest();
        char command;
        try {
            command = input.charAt(0);
        } catch (Exception e) {
            throw new WrongCommand("y/n", input);
        }
        if (command == 'y') {
            return Command.YES;
        } else if (command == 'n') {
            return Command.NO;
        } else {
            throw new WrongCommand("y/n", input);
        }
    }

    /**
     * Метод изменяет вес игрушки в магазине.
     *
     * @param input строка с данными для изменения веса игрушки
     * @throws WrongInputStringIdWeight - если введена неверная строка с данными для изменения веса игрушки
     * @throws ToyTypeNoPresentInShop   - если игрушка с указанным id не найдена в магазине
     */
    private void changeToyTypeWeight(String input) throws WrongInputStringIdWeight, ToyTypeNoPresentInShop {
        int id, weight;
        String[] toyFieldArray = input.strip().split(" ");

        if (toyFieldArray.length != 2) {
            throw new WrongInputStringIdWeight("Не верно введены данные.", input);
        }
        try {
            id = Integer.parseInt(toyFieldArray[0]);
            weight = Integer.parseInt(toyFieldArray[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputStringIdWeight("Не верно введены данные.", input);
        }

        toyShop.updateToyTypeWeight(id, weight);
    }

    /**
     * Метод обрабатывает запрос на изменение веса игрушек.
     *
     * @throws ScannerOperationErrorException - если произошла ошибка при работе с объектом Scanner
     */
    private void processChangeToyTypeWeight() throws ScannerOperationErrorException {
        Command command;
        while (true) {
            try {
                command = requestChangeToyTypeWeight();
                if (command == Command.NO) {
                    return;
                }
                break;
            } catch (WrongCommand e) {
                ioView.showError(String.format("Не верно введена команда, требуется: %s, введенная: %s", e.getRequiredCommand(), e.getInput()));
            } catch (WrongInputStringIdWeight e) {
                ioView.showError(e.getMessage());
            }
        }

        if (command == Command.YES) {
            while (true) {
                toyShop.getStringPrizeToys();
                ioView.showMessage("Введите id и новый вес игрушки через пробел или q для выхода");
                try {
                    String inputString = ioView.inputRequest();
                    if (inputString.equals("q")) {
                        return;
                    }
                    changeToyTypeWeight(inputString);
                    return;
                } catch (WrongInputStringIdWeight e) {
                    ioView.showError(e.getMessage() + " Введенная строка: '" + e.getInputString() + '\'');
                } catch (ToyTypeNoPresentInShop e) {
                    ioView.showError(e.getMessage());
                }
            }
        }
    }
}
