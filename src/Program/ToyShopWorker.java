package Program;

import Exceptions.*;
import Interfaces.ToyShopFileHandlerInterface;
import Interfaces.ToyShopIOViewInterface;
import Interfaces.ToyShopInterface;
import ToyData.Toy;

import java.util.Arrays;

public class ToyShopWorker {

    ToyShopIOViewInterface ioView;
    ToyShopInterface toyShop;
    ToyShopFileHandlerInterface fileHandler;

    public ToyShopWorker(ToyShopIOViewInterface ioView, ToyShopInterface toyShop, ToyShopFileHandlerInterface fileHandler) {
        this.ioView = ioView;
        this.toyShop = toyShop;
        this.fileHandler = fileHandler;
    }

    public void process() {
        try {


            int numberToyTypes = requestNumberToyTypes();

            requestToyTypes(numberToyTypes);
            showPrizeList();

            processChangeToyTypeWeight();

            writeFilePrizeToysSequence(10); // по заданию
        } catch (ScannerOperationErrorException e) {
            ioView.showError(Arrays.toString(e.getStackTrace()));
        }
    }

    private void showPrizeList() {
        ioView.showMessage("******Призовой лист******");
        ioView.showMessage(toyShop.getStringPrizeToys());
    }

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
                ioView.showError("Не верный ввод. Введено: " + input);
            } catch (ExceededNumberToyTypes e) {
                ioView.showError(e.getMessage());
            }
        }
    }

    private void requestToyTypes(int numberRequest) throws ScannerOperationErrorException {
        int countRequest = 0;

        while (countRequest < numberRequest) {
            try {
                ioView.showMessage(countRequest + 1 + ". Вводите данные в формате \'id вес имя\'," +
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

    private void writeFilePrizeToysSequence(int numberTries) {
        try {
            for (int i = 0; i < numberTries; i++) {
                Toy tempToy = toyShop.get();
                fileHandler.writeToFile(tempToy);
            }
        } catch (ListOfPrizeToysIsEmpty e) {
            ioView.showError(e.getMessage());
        } catch (ErrorWriteFileException e) {
            ioView.showError(String.format("%s, Абсолютный путь: %s\n%n", e.getMessage() + Arrays.toString(e.getStackTrace()), e.getFileName()));
        }
    }

    private boolean requestChangeToyTypeWeight() throws WrongCommand, WrongInputStringIdWeight, ScannerOperationErrorException {
        ioView.showMessage("Вы хотите изменить веса игрушек? y/n");
        String input = ioView.inputRequest();
        char command;
        try {
            command = input.charAt(0);
        } catch (Exception e) {
            throw new WrongCommand("y/n", input);
        }
        if (command != 'y' && command != 'n') {
            throw new WrongCommand("y/n", input);
        }

        return command == 'y';
    }

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

    private void processChangeToyTypeWeight() throws ScannerOperationErrorException {
        boolean flagChangeToyTypeWeight;
        while (true) {
            try {
                flagChangeToyTypeWeight = requestChangeToyTypeWeight();
                break;
            } catch (WrongCommand e) {
                ioView.showError(String.format("Не верно введена команда, требуется: %s, введенная: %s", e.getRequiredCommand(), e.getInput()));
            } catch (WrongInputStringIdWeight e) {
                ioView.showError(e.getMessage());
            }
        }

        if (flagChangeToyTypeWeight) {
            while (true) {
                toyShop.getStringPrizeToys();
                ioView.showMessage("Введите id и новый вес игрушки через пробел или q для выхода");
                try {
                    String inputString = ioView.inputRequest();
                    if(inputString.equals("q")){
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
