package Exceptions;

public class WrongCommand extends RuntimeException{
    private final String requiredCommand;
    private final String input;

    public String getRequiredCommand() {
        return requiredCommand;
    }

    public String getInput() {
        return input;
    }

    public WrongCommand(String requiredCommand, String input) {
        this.requiredCommand = requiredCommand;
        this.input = input;
    }
}
