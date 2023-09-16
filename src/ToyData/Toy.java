package ToyData;

public class Toy implements Comparable<Toy> {
    private final int id;
    private final String name;
    private int weight;

    public Toy(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Toy toy) {
            return this.id == toy.id && this.name.equals(toy.name);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Игрушка{" +
                "id='" + id + '\'' +
                ", название='" + name + '\'' +
                ", вec='" + weight + '\'' +
                '}';
    }

    @Override
    public int compareTo(Toy o) {
        return Integer.compare(this.weight, o.weight);
    }
}
