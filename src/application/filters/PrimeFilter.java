package src.application.filters;

import java.util.Set;

public class PrimeFilter implements LotteryFilter {
    private final Set<Integer> primeNumbers;

    public PrimeFilter(Set<Integer> primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        return !combination.stream().anyMatch(primeNumbers::contains);
    }

    @Override
    public int getComplexity() {
        return 1;
    }
}
