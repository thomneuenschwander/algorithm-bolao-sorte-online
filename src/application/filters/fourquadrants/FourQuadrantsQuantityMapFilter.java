package src.application.filters.fourquadrants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FourQuadrantsQuantityMapFilter extends FourQuadrantFilter {

    private final Map<Integer, Integer> requiredCountsByQuadrant;
    
    public FourQuadrantsQuantityMapFilter(Set<Set<Integer>> fourQuadrants, Map<Integer, Integer> requiredCountsByQuadrant) {
        super(fourQuadrants);
        this.requiredCountsByQuadrant = requiredCountsByQuadrant;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        Map<Integer, Integer> countInQuadrants = new HashMap<>();
        
        for (Map.Entry<Set<Integer>, Integer> entry : quadrantIndices.entrySet()) {
            Set<Integer> quadrant = entry.getKey();
            Integer index = entry.getValue();
            int count = 0;

            for (Integer num : combination) {
                if (quadrant.contains(num)) 
                    count++;
            }
            countInQuadrants.put(index, count);
        }

        for (Map.Entry<Integer, Integer> requirement : requiredCountsByQuadrant.entrySet()) {
            if (countInQuadrants.getOrDefault(requirement.getKey(), 0) < requirement.getValue()) 
                return false;
        }

        return true;
    }

    @Override
    public int getComplexity() {
        return 4;
    }
}
