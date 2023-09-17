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
        int numberToyTypes = requestNumberToyTypes();

        requestToyTypes(numberToyTypes);
        showPrizeList();


        writeFilePrizeToysSequence(10); // по заданию
    }

    private void showPrizeList(){
        ioView.showMessage("******Призовой лист******");
        ioView.showMessage(toyShop.getStringPrizeToys());
    }

    private int requestNumberToyTypes(){
        while (true){
            ioView.showMessage("Введите сколько типов вы хотите добавить: ");
            String input = ioView.inputRequest();
            try {
                int number = Integer.parseInt(input);
                if (number > ToyShop.MAX_TYPES_OF_TOYS || number < 0){
                    throw new ExceededNumberToyTypes("Превышено количество возможных типов или введено отрицательное значение.");
                }

                return number;
            } catch (NumberFormatException e){
                ioView.showError("Не верный ввод. Введено: " + input);
            } catch (ExceededNumberToyTypes e){
                ioView.showError(e.getMessage());
            }
        }
    }

    private void requestToyTypes(int numberRequest){
        int countRequest = 0;

        while(countRequest < numberRequest) {
            try {
                ioView.showMessage(countRequest+1+". Вводите данные в фаормате \'id вес имя\'");
                toyShop.put(ioView.inputRequest());
                countRequest++;
            } catch (WrongInputStringToy e) {
                ioView.showError(e.getMessage() + " Введенная строка: '" + e.getInputString() + '\'');
            } catch (ToyTypePresentInShop e) {
                ioView.showError(e.getMessage() + " Введенный тип: '" + e.getToy().toString() + '\'');
            }
        }
    }

    private void writeFilePrizeToysSequence(int numberTries){
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
}
