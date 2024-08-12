package src.domain;

import java.util.List;

import src.application.FilteredResult;
import src.application.controllers.LotteryRequest;
import src.application.controllers.LotteryResponse;
import src.application.filters.LotteryFilter;

public interface LotteryService {

    FilteredResult applyFilterCombinations(Lottery lottery, List<List<Integer>> groups, List<LotteryFilter> filters);

    LotteryResponse lotteryPlay(LotteryRequest request);
}
