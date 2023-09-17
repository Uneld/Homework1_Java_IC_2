package Interfaces;

import ToyData.Toy;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Интерфейс для генерации списка призовых игрушек.
 */
public interface GeneratorPrizeToyListInterface {
    /**
     * Метод для генерации списка призовых игрушек.
     *
     * @param toyTypesQueue очередь с игрушками
     * @param sizePrizeList размер списка призовых игрушек
     * @return список призовых игрушек
     */
    LinkedList<Toy> generatePrizeToyList(PriorityQueue<Toy> toyTypesQueue, int sizePrizeList);
}
