package Exceptions;

public class ToyTypeNoPresentInShop extends RuntimeException{
    public ToyTypeNoPresentInShop(String message) {
        super(message);
    }
}
