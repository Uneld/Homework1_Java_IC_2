package Program;

import Exceptions.ListOfPrizeToysIsEmpty;
import Exceptions.ToyTypeNoPresentInShop;
import Exceptions.ToyTypePresentInShop;
import Exceptions.WrongInputStringToy;
import Interfaces.GeneratorPrizeToyListInterface;
import Interfaces.ToyShopInterface;
import ToyData.Toy;

import java.util.*;

/**
 * Класс ToyShop представляет собой магазин игрушек, который может принимать новые типы игрушек
 * и генерировать призовые игрушки.
 */
public class ToyShop implements ToyShopInterface {
    static final int MAX_TYPES_OF_TOYS = 10;
    static final int NUMBER_OF_PRIZE_TOYS = 10;
    static int countAddedTypeToys = 0;

    private final PriorityQueue<Toy> toyTypesQueue;

    private LinkedList<Toy> listOfPrizeToys;

    private final GeneratorPrizeToyListInterface generator;

    private final int[] weights;

    private final int[] ids;

    private final String[] names;

    /**
     * Конструктор класса ToyShop.
     *
     * @param generator - генератор призовых игрушек
     */
    public ToyShop(GeneratorPrizeToyListInterface generator) {
        this.toyTypesQueue = new PriorityQueue<>();
        this.weights = new int[MAX_TYPES_OF_TOYS];
        this.ids = new int[MAX_TYPES_OF_TOYS];
        this.names = new String[MAX_TYPES_OF_TOYS];

        this.generator = generator;
    }

    /**
     * Метод put() добавляет новый тип игрушки в магазин.
     *
     * @param input - строка с параметрами нового типа игрушки
     * @throws WrongInputStringToy  - выбрасывается, если введенная строка содержит неверные параметры
     * @throws ToyTypePresentInShop - выбрасывается, если тип игрушки уже присутствует в магазине
     */
    @Override
    public void put(String input) throws WrongInputStringToy, ToyTypePresentInShop {
        Toy newToyType = parseInputString(input);

        ids[countAddedTypeToys] = newToyType.getId();
        weights[countAddedTypeToys] = newToyType.getWeight();
        names[countAddedTypeToys] = newToyType.getName();

        if (toyTypesQueue.contains(newToyType)) {
            throw new ToyTypePresentInShop("Такой тип игрушки уже присутствует в магазине", newToyType);
        } else {
            countAddedTypeToys++;
        }

        toyTypesQueue.offer(newToyType);

        listOfPrizeToys = generator.generatePrizeToyList(toyTypesQueue, NUMBER_OF_PRIZE_TOYS);
    }

    /**
     * Метод возвращает следующую призовую игрушку из списка.
     * Если список призовых игрушек пуст, то выбрасывается исключение {@link ListOfPrizeToysIsEmpty}.
     *
     * @return следующая призовая игрушка из списка
     * @throws ListOfPrizeToysIsEmpty если список призовых игрушек пуст
     */
    @Override
    public Toy get() throws ListOfPrizeToysIsEmpty {
        if (listOfPrizeToys.isEmpty()) {
            throw new ListOfPrizeToysIsEmpty("Призовые игрушки закончились.");
        }
        return listOfPrizeToys.pollFirst();
    }

    /**
     * Метод обновляет вес указанного типа игрушки в магазине.
     * Если указанный тип игрушки не найден в магазине, то выбрасывается исключение {@link ToyTypeNoPresentInShop}.
     *
     * @param id     идентификатор типа игрушки, который нужно обновить
     * @param weight новый вес типа игрушки
     * @throws ToyTypeNoPresentInShop если указанный тип игрушки не найден в магазине
     */
    @Override
    public void updateToyTypeWeight(int id, int weight) throws ToyTypeNoPresentInShop {
        for (Toy toy : toyTypesQueue) {
            if (toy.getId() == id) {
                toy.setWeight(weight);
                return;
            }
        }
        throw new ToyTypeNoPresentInShop("Такого типа игрушки не существует");
    }

    /**
     * Метод возвращает строковое представление списка призовых игрушек в магазине.
     *
     * @return строковое представление списка призовых игрушек в магазине
     */
    @Override
    public String getStringPrizeToys() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < countAddedTypeToys; i++) {
            builder.append(ids[i]).append(". ").append(names[i]).append(", шанс=").append(weights[i]).append("%\n");
        }

        return builder.toString();
    }

    /**
     * Метод для парсинга строки с данными о новом типе игрушки.
     *
     * @param input строка с данными о новом типе игрушки
     * @return объект {@link Toy}, содержащий данные о новом типе игрушки
     * @throws WrongInputStringToy если строка содержит неверные параметры игрушки
     */
    private Toy parseInputString(String input) throws WrongInputStringToy {
        int id, weight;
        String[] toyFieldArray = input.strip().split(" ");

        if (toyFieldArray.length != 3) {
            throw new WrongInputStringToy("Не верно введены параметры игрушки.", input);
        }

        try {
            id = Integer.parseInt(toyFieldArray[0]);
            weight = Integer.parseInt(toyFieldArray[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputStringToy("Не верно введены параметры игрушки.", input);
        }

        if (weight > ToyRaffle.MAX_PERCENT_CHANCE || weight < 0 || id < 0) {
            throw new WrongInputStringToy("Не верно введены параметры игрушки.", input);
        }

        return new Toy(id, toyFieldArray[2], weight);
    }
}
