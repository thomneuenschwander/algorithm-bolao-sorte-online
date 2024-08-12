package src.application.filters.miniquadrants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExclusiveMiniQuadrantsFilter extends MiniQuadrantsFilter {

    public ExclusiveMiniQuadrantsFilter(Map<Integer, Set<Integer>> miniQuadrants) {
        super(miniQuadrants);
    }

    @Override
    public int getComplexity() {
        return 2;
    }

    @Override
    public boolean test(Set<Integer> combination) {
        Map<Integer, Integer> quadrantCount = new HashMap<>();
        for (Integer number : combination) {
            for (Map.Entry<Integer, Set<Integer>> entry : miniQuadrants.entrySet()) {
                if (entry.getValue().contains(number)) {
                    int quadrant = entry.getKey();
                    quadrantCount.put(quadrant, quadrantCount.getOrDefault(quadrant, 0) + 1);

                    if (quadrantCount.get(quadrant) > 1)
                        return false;
                }
            }
        }

        return true;
    }
    
}
