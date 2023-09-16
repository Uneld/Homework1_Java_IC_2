import Exceptions.ErrorWriteFileException;
import Exceptions.ListOfPrizeToysIsEmpty;
import Exceptions.ToyTypePresentInShop;
import Exceptions.WrongInputStringToy;
import Interfaces.GeneratorPrizeToyListInterface;
import ToyData.Toy;

public class Main {
    public static void main(String[] args) {

        ToyShopFileHandler toyShopFileHandler = new ToyShopFileHandler("list.txt", "WonToys");
        GeneratorPrizeToyListInterface generator = new ToyRaffle();
        ToyShop toyShop = new ToyShop(generator);

        try {
            toyShop.put("1 20 конструктор ");
            toyShop.put("2 20 робот");
            toyShop.put("3 60 кукла");

            System.out.println(toyShop.getStringPrizeToys());

            for (int i = 0; i < 10; i++) {
                Toy tempToy = toyShop.get();
                toyShopFileHandler.writeToFile(tempToy);
            }

        } catch (WrongInputStringToy e) {
            System.out.println(e.getMessage() + " Введенная строка: '" + e.getInputString() + '\'');
        } catch (ToyTypePresentInShop e) {
            System.out.println(e.getMessage() + " Введенный тип: '" + e.getToy().toString() + '\'');
        } catch (NumberFormatException | ListOfPrizeToysIsEmpty e) {
            System.out.println(e.getMessage());
        } catch (ErrorWriteFileException e) {
            throw new RuntimeException(e);
        }

    }
}
