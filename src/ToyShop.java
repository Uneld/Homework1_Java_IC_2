import Exceptions.ListOfPrizeToysIsEmpty;
import Exceptions.ToyTypeNoPresentInShop;
import Exceptions.ToyTypePresentInShop;
import Exceptions.WrongInputStringToy;
import Interfaces.GeneratorPrizeToyListInterface;
import ToyData.Toy;

import java.util.*;

public class ToyShop {
    static final int MAX_TYPES_OF_TOYS = 10;
    static final int NUMBER_OF_PRIZE_TOYS = 10;
    static int countAddedTypeToys = 0;

    private final PriorityQueue<Toy> toyTypesQueue;

    private LinkedList<Toy> listOfPrizeToys;

    private final GeneratorPrizeToyListInterface generator;

    private final int[] weights;

    private final int[] ids;

    private final String[] names;

    public ToyShop(GeneratorPrizeToyListInterface generator) {
        this.toyTypesQueue = new PriorityQueue<>();
        this.weights = new int[MAX_TYPES_OF_TOYS];
        this.ids = new int[MAX_TYPES_OF_TOYS];
        this.names = new String[MAX_TYPES_OF_TOYS];

        this.generator = generator;
    }

    public void put(String input) throws WrongInputStringToy, NumberFormatException, ToyTypePresentInShop {
        String[] toyFieldArray = input.strip().split(" ");
        if (toyFieldArray.length > 3) {
            throw new WrongInputStringToy("Не верно введены параметры игрушки.", input);
        }
        ids[countAddedTypeToys] = Integer.parseInt(toyFieldArray[0]);
        weights[countAddedTypeToys] = Integer.parseInt(toyFieldArray[1]);
        names[countAddedTypeToys] = toyFieldArray[2];

        Toy newToyType = new Toy(ids[countAddedTypeToys], names[countAddedTypeToys], weights[countAddedTypeToys]);

        if (toyTypesQueue.contains(newToyType)) {
            throw new ToyTypePresentInShop("Такой тип игрушки уже присутствует в магазине", newToyType);
        } else {
            countAddedTypeToys++;
        }

        toyTypesQueue.offer(newToyType);

        listOfPrizeToys = generator.generatePrizeToyList(toyTypesQueue, NUMBER_OF_PRIZE_TOYS);
    }

    public Toy get() throws ListOfPrizeToysIsEmpty {
        if (listOfPrizeToys.isEmpty()) {
            throw new ListOfPrizeToysIsEmpty("Список призовых игрушек пуст");
        }
        return listOfPrizeToys.pollFirst();
    }


    public void updateToyTypeWeight(int id, int weight) {
        for (Toy toy : toyTypesQueue) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                return;
            }
        }
        throw new ToyTypeNoPresentInShop("Такого типа игрушки не существует");
    }

    public String getStringPrizeToys() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < countAddedTypeToys; i++) {
            builder.append(names[i]).append("= ").append(weights[i]).append("%\n");
        }

        return builder.toString();
    }
}
