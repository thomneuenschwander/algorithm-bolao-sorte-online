package src.application.filters.inline;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

public class SequentialRowFilter implements LotteryFilter {

    private final int sequenceLength;

    public SequentialRowFilter(int sequenceLength) {
        this.sequenceLength = sequenceLength;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        Map<Integer, List<Integer>> possibleSequences = new HashMap<>();
        combination.forEach(number -> {
            int row = getRowPosition(number);
            List<Integer> rowNumbers = possibleSequences.getOrDefault(row, new LinkedList<>());
            rowNumbers.add(number);
            possibleSequences.put(row, rowNumbers);
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

    private int getRowPosition(int number) {
        return Math.floorDiv(number, 10);
    }

    @Override
    public int getComplexity() {
        return 2;
    }
}
