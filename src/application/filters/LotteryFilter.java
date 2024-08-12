package src.application.filters;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface LotteryFilter extends Predicate<Set<Integer>> {

    int getComplexity();
    
    public static List<Set<Integer>> apply(List<Set<Integer>> combinations, List<LotteryFilter> filters) {

        filters.sort(Comparator.comparingInt(LotteryFilter::getComplexity));

        return combinations.stream()
                .filter(combination -> {
                    for (LotteryFilter filter : filters) {
                        if (!filter.test(combination))
                            return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
