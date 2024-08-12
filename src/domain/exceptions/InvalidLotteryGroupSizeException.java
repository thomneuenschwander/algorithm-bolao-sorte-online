package src.domain.exceptions;

public class InvalidLotteryGroupSizeException extends RuntimeException {
    public InvalidLotteryGroupSizeException(String message) {
        super(message);
    }
}
