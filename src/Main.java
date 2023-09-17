import Program.*;

public class Main {

    /*
    Файл при вводе данных:
    Введите сколько типов вы хотите добавить:
    3
    Вводите данные в формате 'id вес имя', где id > 0, 0 < weight < 100
    1 20 конструктор
    2 20 робот
    3 60 кукла
     */
    public static void main(String[] args) {
        ToyShopIOView ioView = new ToyShopIOView();
        ToyShop toyShop = new ToyShop(new ToyRaffle());
        ToyShopFileHandler toyShopFileHandler = new ToyShopFileHandler("list.txt", "WonToys");

        // количество вызовов get 10 по заданию
        ToyShopWorker worker = new ToyShopWorker(ioView, toyShop, toyShopFileHandler, 10);
        worker.process();
    }
}
