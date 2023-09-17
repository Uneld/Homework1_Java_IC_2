package Interfaces;

import Exceptions.ListOfPrizeToysIsEmpty;
import Exceptions.ToyTypeNoPresentInShop;
import Exceptions.ToyTypePresentInShop;
import Exceptions.WrongInputStringToy;
import ToyData.Toy;

public interface ToyShopInterface {
    void put(String input) throws WrongInputStringToy, ToyTypePresentInShop;

    Toy get() throws ListOfPrizeToysIsEmpty;

    void updateToyTypeWeight(int id, int weight) throws ToyTypeNoPresentInShop;

    String getStringPrizeToys();
}
