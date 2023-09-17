package Exceptions;

import ToyData.Toy;

public class ToyTypePresentInShop extends Exception{
    private final Toy toy;

    public ToyTypePresentInShop(String message, Toy toy) {
        super(message);
        this.toy = toy;
    }

    public Toy getToy() {
        return toy;
    }
}
