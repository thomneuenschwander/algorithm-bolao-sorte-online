package src.application.filters.inline;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

public class ColumnFilter implements LotteryFilter {

    private final int columns;
    private final int y;

    public ColumnFilter(int columns, int y) {
        this.columns = columns;
        this.y = y;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        Map<Integer, Integer> columnsCount = new HashMap<>();

            combination.forEach(number -> {
                int column = getColumnPosition(number);
                columnsCount.put(column, columnsCount.getOrDefault(column, 0) + 1);
            });

            long count = columnsCount.values().stream()
                    .filter(v -> v == y)
                    .count();

            return count == columns;
    }

    private int getColumnPosition(int number) {
        return number % 10;
    }

    @Override
    public int getComplexity() {
        return 2;
    }
}
