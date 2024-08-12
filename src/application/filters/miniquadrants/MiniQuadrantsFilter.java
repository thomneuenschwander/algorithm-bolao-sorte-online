package src.application.filters.miniquadrants;

import java.util.Map;
import java.util.Set;

import src.application.filters.LotteryFilter;

public abstract class MiniQuadrantsFilter implements LotteryFilter {
    protected final Map<Integer, Set<Integer>> miniQuadrants;

    public MiniQuadrantsFilter(Map<Integer, Set<Integer>> miniQuadrants) {
        this.miniQuadrants = miniQuadrants;
    }
}
