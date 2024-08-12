package src.domain.exceptions;

public class LotteryNotSupportedException extends RuntimeException {
    public LotteryNotSupportedException(String lotteryRequest) {
        super("Lottery type '" + lotteryRequest + "' not supported");
    }
}
