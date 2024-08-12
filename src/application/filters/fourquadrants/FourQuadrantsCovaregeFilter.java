package src.application.filters.fourquadrants;

import java.util.HashSet;
import java.util.Set;

public class FourQuadrantsCovaregeFilter extends FourQuadrantFilter {
    private final int expectedQuadrants;

    public FourQuadrantsCovaregeFilter(int expectedQuadrants, Set<Set<Integer>> fourQuadrants) {
        super(fourQuadrants);
        this.expectedQuadrants = expectedQuadrants;
    }

    @Override
    public int getComplexity() {
        return 2;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        int count = 0;
        for (Set<Integer> quadrant : fourQuadrants) {

            Set<Integer> intersection = new HashSet<>(combination);
            intersection.retainAll(quadrant);
            if (!intersection.isEmpty()) 
                count++;
            
            if (count > expectedQuadrants) 
                return false; 
        }

        return count == expectedQuadrants;
    }
}
