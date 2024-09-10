package src.application.filters.inline;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

/**
 * This class defines a filter to check for anchor numbers in a lottery
 * combination.
 * An anchor number is defined as a number that simultaneously forms a pair with
 * another number
 * in both the same row and the same column.
 * 
 * The filter allows you to specify the number of anchor numbers that should be
 * present in a combination.
 * 
 * If the combination contains exactly the specified number of anchor numbers,
 * the combination is accepted.
 * Otherwise, the combination is rejected.
 */
public class AnchorNumberFilter implements LotteryFilter {
    /**
     * The amount of anchor numbers allowed in a combination.
     */
    private final int anchorNumberAmount;

    public AnchorNumberFilter(int anchorNumberAmount) {
        this.anchorNumberAmount = anchorNumberAmount;
    }

    /**
     * Tests whether the given combination contains exactly the specified number of
     * anchor numbers.
     * 
     * An anchor number is defined as a number that forms a pair with another number
     * in both the same row and the same column. If the number of anchor numbers
     * matches
     * the specified {@code anchorNumberAmount}, the combination is accepted.
     * Otherwise, it is rejected.
     * 
     * @param combination A set of integers representing a lottery combination.
     * @return {@code true} if the number of anchor numbers equals
     *         {@code anchorNumberAmount},
     *         {@code false} otherwise.
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

        int count = 0;
        for (Integer number : combination) {
            int row = getRowPosition(number);
            int column = getColumnPosition(number);

            if (rowMap.get(row).size() > 1 && colMap.get(column).size() > 1)
                count++;

            if (count > anchorNumberAmount)
                return false;

        }
        return count == anchorNumberAmount;
    }

    private int getRowPosition(int number) {
        return Math.floorDiv(number, 10);
    }

    private int getColumnPosition(int number) {
        return number % 10;
    }

    /**
     * Returns the complexity level of this filter.
     * 
     * @Complexity O(n), where n is the number of elements in the combination set.
     * 
     *             This complexity arises because the filter processes each element
     *             of the combination once to
     *             group numbers by row and column, and then performs a
     *             constant-time lookup for each number.
     * 
     * @return An integer representing the complexity level.
     */
    @Override
    public int getComplexity() {
        return 3;
    }
}
