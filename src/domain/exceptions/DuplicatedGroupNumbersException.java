package src.domain.exceptions;

public class DuplicatedGroupNumbersException extends RuntimeException {
    public DuplicatedGroupNumbersException(int number) {
        super("Duplicate number found in group: " + number);
    }
}
