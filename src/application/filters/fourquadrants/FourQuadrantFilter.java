package src.application.filters.fourquadrants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

public abstract class FourQuadrantFilter implements LotteryFilter {
    protected final Set<Set<Integer>> fourQuadrants;
    protected final Map<Set<Integer>, Integer> quadrantIndices;

    public FourQuadrantFilter(Set<Set<Integer>> fourQuadrants) {
        this.fourQuadrants = fourQuadrants;
        this.quadrantIndices = new HashMap<>(4);
        initializeQuadrantIndices();
    }

    private void initializeQuadrantIndices() {
        int index = 1;
        for (Set<Integer> quadrant : fourQuadrants) 
            quadrantIndices.put(quadrant, index++);
    }
}
