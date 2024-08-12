package src.application.filters.inline;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

public class RowFilter implements LotteryFilter {

    private final int rows;
    private final int x;

    public RowFilter(int rows, int x) {
        this.rows = rows;
        this.x = x;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        Map<Integer, Integer> rowsCount = new HashMap<>();

            combination.forEach(number -> {
                int row = getRowPosition(number);
                rowsCount.put(row, rowsCount.getOrDefault(row, 0) + 1);
            });

            long count = rowsCount.values().stream()
                    .filter(v -> v == x)
                    .count();

            return count == rows;
    }

    private int getRowPosition(int number) {
        return Math.floorDiv(number, 10);
    }

    @Override
    public int getComplexity() {
        return 2;
    }
}

