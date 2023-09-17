package ToyData;

/**
 * Класс Toy представляет игрушку в магазине и имеет идентификатор, название и вес.
 * Класс реализует интерфейс Comparable, чтобы можно было сортировать игрушки по весу.
 */
public class Toy implements Comparable<Toy> {
    private final int id;
    private final String name;
    private int weight;

    /**
     * Конструктор класса Toy.
     *
     * @param id     идентификатор игрушки
     * @param name   название игрушки
     * @param weight вес игрушки
     */
    public Toy(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    /**
     * Метод setWeight устанавливает вес игрушки.
     *
     * @param weight новый вес игрушки
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Метод getId возвращает идентификатор игрушки.
     *
     * @return идентификатор игрушки
     */
    public int getId() {
        return id;
    }

    /**
     * Метод getName возвращает название игрушки.
     *
     * @return название игрушки
     */
    public String getName() {
        return name;
    }

    /**
     * Метод getWeight возвращает вес игрушки.
     *
     * @return вес игрушки
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Метод equals сравнивает игрушки по идентификатору и названию.
     *
     * @param obj объект, с которым нужно сравнить игрушку
     * @return true, если объекты равны, и false в противном случае
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Toy toy) {
            return this.id == toy.id && this.name.equals(toy.name);
        }
        return false;
    }

    /**
     * Метод toString возвращает строковое представление игрушки.
     *
     * @return строковое представление игрушки
     */
    @Override
    public String toString() {
        return "Игрушка{" +
                "id='" + id + '\'' +
                ", название='" + name + '\'' +
                ", вес='" + weight + '\'' +
                '}';
    }

    /**
     * Метод compareTo сравнивает игрушки по весу.
     *
     * @param o объект, с которым нужно сравнить игрушку
     * @return отрицательное число, если текущий вес меньше веса объекта, положительное число, если текущий вес больше веса объекта, и 0, если веса равны
     */
    @Override
    public int compareTo(Toy o) {
        return Integer.compare(this.weight, o.weight);
    }
}
