package Exceptions;

public class WrongInputStringToy extends IllegalArgumentException {
    private final String inputString;

    public WrongInputStringToy(String s, String inputString) {
        super(s);
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }
}
