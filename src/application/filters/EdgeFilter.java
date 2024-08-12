package src.application.filters;

import java.util.Set;

public class EdgeFilter implements LotteryFilter {
    private final int edge;
    private final Set<Integer> edgeNumbers;

    public EdgeFilter(int edge, Set<Integer> edgeNumbers) {
        this.edge = edge;
        this.edgeNumbers = edgeNumbers;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        int edgeCount = 0;
        for (int number : combination) {
            if (edgeNumbers.contains(number))
                edgeCount++;
        }
        return edgeCount == edge;
    }

    @Override
    public int getComplexity() {
        return 1;
    }
}
