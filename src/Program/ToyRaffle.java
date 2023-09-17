package Program;

import Interfaces.GeneratorPrizeToyListInterface;
import ToyData.Toy;

import java.util.*;

public class ToyRaffle implements GeneratorPrizeToyListInterface {
    static final int MAX_PERCENT_CHANCE = 100;
    private final Random random;

    public ToyRaffle() {
        this.random = new Random();
    }

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
