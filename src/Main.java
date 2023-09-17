import Program.*;

public class Main {
    public static void main(String[] args) {
        ToyShopIOView ioView = new ToyShopIOView();
        ToyShop toyShop = new ToyShop(new ToyRaffle());
        ToyShopFileHandler toyShopFileHandler = new ToyShopFileHandler("list.txt", "WonToys");

        ToyShopWorker worker = new ToyShopWorker(ioView, toyShop, toyShopFileHandler);
        worker.process();
    }
}
