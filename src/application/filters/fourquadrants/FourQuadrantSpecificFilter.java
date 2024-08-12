package src.application.filters.fourquadrants;

import java.util.Set;
import java.util.HashSet;

public class FourQuadrantSpecificFilter extends FourQuadrantFilter {
    private final Set<Integer> requiredQuadrants;


    public FourQuadrantSpecificFilter(Set<Set<Integer>> fourQuadrants, Set<Integer> requiredQuadrants) {
        super(fourQuadrants);
        this.requiredQuadrants = requiredQuadrants;
    }



    @Override
    public int getComplexity() {
        return 3;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        Set<Integer> activeQuadrants = new HashSet<>();
        for (Set<Integer> quadrant : fourQuadrants) {
            if (!quadrantIndices.containsKey(quadrant)) 
                throw new IllegalArgumentException("Quadrant index not found.");
            
            for (Integer num : quadrant) {
                if (combination.contains(num)) {
                    activeQuadrants.add(quadrantIndices.get(quadrant));
                    break;
                }
            }
        }

        return activeQuadrants.equals(requiredQuadrants);
    }
}
