package Program;

import Interfaces.GeneratorPrizeToyListInterface;
import ToyData.Toy;

import java.util.*;

/**
 * Класс для генерации списка призовых игрушек.
 */
public class ToyRaffle implements GeneratorPrizeToyListInterface {
    static final int MAX_PERCENT_CHANCE = 100;
    private final Random random;

    /**
     * Конструктор класса ToyRaffle.
     */
    public ToyRaffle() {
        this.random = new Random();
    }

    /**
     * Метод генерирует список призовых игрушек.
     *
     * @param toyTypesQueue очередь с игрушками
     * @param sizePrizeList размер списка призовых игрушек
     * @return список призовых игрушек
     */
    @Override
    public LinkedList<Toy> generatePrizeToyList(PriorityQueue<Toy> toyTypesQueue, int sizePrizeList) {
        LinkedList<Toy> listOfPrizeToys = new LinkedList<>();
        while (listOfPrizeToys.size() != sizePrizeList) {
            for (Toy toy : toyTypesQueue) {
                if (random.nextInt(MAX_PERCENT_CHANCE - toy.getWeight()) == 0) {
                    listOfPrizeToys.add(toy);
                }
            }
        }
        return listOfPrizeToys;
    }
}
