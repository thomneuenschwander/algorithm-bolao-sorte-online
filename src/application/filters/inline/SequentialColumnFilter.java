package src.application.filters.inline;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

public class SequentialColumnFilter implements LotteryFilter {

    private final int sequenceLength;

    public SequentialColumnFilter(int sequenceLength) {
        this.sequenceLength = sequenceLength;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        Map<Integer, List<Integer>> possibleSequences = new HashMap<>();
        combination.forEach(number -> {
            int column = getColumnPosition(number);
            List<Integer> columnNumbers = possibleSequences.getOrDefault(column, new LinkedList<>());
            columnNumbers.add(number);
            possibleSequences.put(column, columnNumbers);
        });

        for(List<Integer> possibleSeq : possibleSequences.values()) {
            possibleSeq.sort(Integer::compareTo);

            int count = 1;
            for(int i = 0; i < possibleSeq.size() - 1; i++) 
                count = (possibleSeq.get(i) + 1 == possibleSeq.get(i + 1)) ? count + 1 : 1;

            if(count >= sequenceLength)
                return false;
        }

        return true;
    }

    private int getColumnPosition(int number) {
        return number % 10;
    }

    @Override
    public int getComplexity() {
        return 2;
    }
}
