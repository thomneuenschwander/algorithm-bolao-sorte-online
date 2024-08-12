package src.application;

import java.util.List;
import java.util.Set;

public record FilteredResult(int totalCombinations, List<Set<Integer>> remainingCombinationsList) {

    public int getFilteredCombinations() {
        return totalCombinations - getRemainingCombinations();
    }

    public int getRemainingCombinations() {
        return remainingCombinationsList.size();
    }
}

