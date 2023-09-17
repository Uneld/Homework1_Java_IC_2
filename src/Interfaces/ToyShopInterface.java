package Interfaces;

import Exceptions.ListOfPrizeToysIsEmpty;
import Exceptions.ToyTypeNoPresentInShop;
import Exceptions.ToyTypePresentInShop;
import Exceptions.WrongInputStringToy;
import ToyData.Toy;

/**
 * Интерфейс ToyShopInterface определяет методы для работы с магазином игрушек.
 */
public interface ToyShopInterface {
    /**
     * Метод добавляет игрушку в магазин.
     *
     * @param input строка с данными о новой игрушке
     * @throws WrongInputStringToy  если входная строка некорректна
     * @throws ToyTypePresentInShop если тип игрушки уже есть в магазине
     */
    void put(String input) throws WrongInputStringToy, ToyTypePresentInShop;

    /**
     * Метод возвращает случайную игрушку из списка призовых игрушек.
     *
     * @return игрушка из списка призовых игрушек
     * @throws ListOfPrizeToysIsEmpty если список призовых игрушек пуст
     */
    Toy get() throws ListOfPrizeToysIsEmpty;

    /**
     * Метод обновляет вес типа игрушки в магазине.
     *
     * @param id     идентификатор типа игрушки
     * @param weight новый вес типа игрушки
     * @throws ToyTypeNoPresentInShop если тип игрушки не найден в магазине
     */
    void updateToyTypeWeight(int id, int weight) throws ToyTypeNoPresentInShop;

    /**
     * Метод возвращает строку с информацией о призовых игрушках.
     *
     * @return строка с информацией о призовых игрушках
     */
    String getStringPrizeToys();
}
