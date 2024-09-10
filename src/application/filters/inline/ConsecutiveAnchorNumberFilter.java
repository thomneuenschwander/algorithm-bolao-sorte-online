package src.application.filters.inline;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

/**
 * This class defines a filter that checks for consecutive anchor numbers in a lottery combination.
 * An anchor number is defined as a number that forms a pair with another number in both the same row
 * and the same column. This filter checks for numbers that are consecutive in the same row and 
 * have pairs in the same column, or are consecutive in the same column.
 * 
 * The filter returns true if it finds the exact number of consecutive anchor numbers as specified 
 * by consecutiveAnchorNumberAmount in the combination.
 */
public class ConsecutiveAnchorNumberFilter implements LotteryFilter {

    private final int consecutiveAnchorNumberAmount;

    public ConsecutiveAnchorNumberFilter(int consecutiveAnchorNumberAmount) {
        this.consecutiveAnchorNumberAmount = consecutiveAnchorNumberAmount;
    }

    /**
     * Tests whether the given combination contains the exact amount of consecutive anchor numbers.
     * 
     * The filter checks if there are any consecutive numbers in the same row and verifies 
     * if those numbers have a pair in the same column. Additionally, it checks if there 
     * are any consecutive numbers in the same column.
     * 
     * @param combination A set of integers representing a lottery combination.
     * @return {@code true} if there are exactly the specified amount of consecutive anchor numbers, {@code false} otherwise.
     */
    @Override
    public boolean test(Set<Integer> combination) {
        Map<Integer, List<Integer>> rowMap = new HashMap<>();
        Map<Integer, List<Integer>> colMap = new HashMap<>();

        combination.forEach(number -> {
            int row = getRowPosition(number);
            int column = getColumnPosition(number);

            rowMap.computeIfAbsent(row, k -> new LinkedList<>()).add(number);
            colMap.computeIfAbsent(column, k -> new LinkedList<>()).add(number);
        });

        int consecutiveCount = 0;

        for (List<Integer> rowNumbers : rowMap.values()) {
            rowNumbers.sort(Integer::compareTo);

            for (int i = 0; i < rowNumbers.size() - 1; i++) {
                if (rowNumbers.get(i) + 1 == rowNumbers.get(i + 1)) {
                    int column = getColumnPosition(rowNumbers.get(i));
                    if (colMap.get(column).size() > 1) {
                        consecutiveCount++;
                        if (consecutiveCount >= consecutiveAnchorNumberAmount) 
                            return true; 
                    }
                }
            }
        }

        for (List<Integer> colNumbers : colMap.values()) {
            colNumbers.sort(Integer::compareTo);

            for (int i = 0; i < colNumbers.size() - 1; i++) {
                if (colNumbers.get(i) + 10 == colNumbers.get(i + 1)) {
                    consecutiveCount++;
                    if (consecutiveCount >= consecutiveAnchorNumberAmount) 
                        return true; 
                }
            }
        }

        return consecutiveCount == consecutiveAnchorNumberAmount;
    }

    private int getRowPosition(int number) {
        return Math.floorDiv(number, 10);
    }

    private int getColumnPosition(int number) {
        return number % 10;
    }

    @Override
    public int getComplexity() {
        return 4;
    }
}
