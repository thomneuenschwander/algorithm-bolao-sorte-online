package src.application;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import src.application.controllers.LotteryRequest;
import src.application.controllers.LotteryResponse;
import src.application.filters.EdgeFilter;
import src.application.filters.LotteryFilter;
import src.application.filters.PrimeFilter;
import src.application.filters.fourquadrants.FourQuadrantSpecificFilter;
import src.application.filters.fourquadrants.FourQuadrantsCovaregeFilter;
import src.application.filters.fourquadrants.FourQuadrantsQuantityMapFilter;
import src.application.filters.inline.AnchorNumberFilter;
import src.application.filters.inline.ColumnFilter;
import src.application.filters.inline.ConsecutiveAnchorNumberFilter;
import src.application.filters.inline.RowFilter;
import src.application.filters.inline.SequentialColumnFilter;
import src.application.filters.inline.SequentialRowFilter;
import src.application.filters.miniquadrants.ExclusiveMiniQuadrantsFilter;
import src.application.filters.miniquadrants.SharedMiniQuadrantsFilter;
import src.domain.Lottery;
import src.domain.LotteryService;
import src.domain.exceptions.DuplicatedGroupNumbersException;
import src.domain.exceptions.InvalidLotteryGroupSizeException;
import src.domain.exceptions.LotteryNotSupportedException;

public class LotteryServiceImpl implements LotteryService {

    @Override
    public FilteredResult applyFilterCombinations(Lottery lottery, List<List<Integer>> groups,
            List<LotteryFilter> filters) {

        validateGroups(lottery, groups);

        List<Set<Integer>> allCombinations = generateCombinations(groups);

        List<Set<Integer>> filtered = LotteryFilter.apply(allCombinations, filters);

        return new FilteredResult(allCombinations.size(), filtered);
    }

    private void validateGroups(Lottery lottery, List<List<Integer>> groups) {
        if (lottery.getNumbersPerGame() < groups.size())
            throw new InvalidLotteryGroupSizeException(
                    "Expected " + lottery.getNumbersPerGame() + " groups, but got " + groups.size());

        int maxNumbersPerGroup = lottery.getNumberRange() / lottery.getNumbersPerGame();
        groups.forEach(group -> {
            if (group.size() > maxNumbersPerGroup)
                throw new InvalidLotteryGroupSizeException(
                        "Expected up to " + maxNumbersPerGroup + " numbers per group, but got " + group.size());
        });
    }

    @Override
    public LotteryResponse lotteryPlay(LotteryRequest request) {
        var lottery = Lottery.get(request.getLotteryName())
                .orElseThrow(() -> new LotteryNotSupportedException(request.getLotteryName()));

        var filters = getRequestedFilters(lottery, request);
        FilteredResult result = applyFilterCombinations(lottery, request.getGroups(), filters);

        var randomSelectedCombinations = selectRandomCombinations(result.remainingCombinationsList(),
                request.getRandomChoice());

        return new LotteryResponse.Builder()
                .lotteryName(lottery.getName())
                .timestamp(LocalDateTime.now())
                .showFilteredCombinations(request.getPrintRemaining())
                .filteredResult(result)
                .randomSelectedCombinations(randomSelectedCombinations)
                .build();
    }

    private List<LotteryFilter> getRequestedFilters(Lottery lottery, LotteryRequest request) {
        List<LotteryFilter> filters = new LinkedList<>();

        if (request.getEdgeNumber() != null)
            filters.add(new EdgeFilter(request.getEdgeNumber(), lottery.getEdgeNumbers()));

        if (request.getRemovePrimes() != null && request.getRemovePrimes())
            filters.add(new PrimeFilter(lottery.getPrimeNumbers()));

        if (request.getExclusivesMiniQuadrants() != null && request.getExclusivesMiniQuadrants())
            filters.add(new ExclusiveMiniQuadrantsFilter(lottery.getMiniQuadrantsNumbers()));
        else if (request.getSharedMiniquadrants() != null && request.getSharedMiniquadrants())
            filters.add(new SharedMiniQuadrantsFilter(lottery.getMiniQuadrantsNumbers()));

        if (request.getCoveregeQuadrantNumber() != null)
            filters.add(new FourQuadrantsCovaregeFilter(request.getCoveregeQuadrantNumber(),
                    lottery.getFourQuadrantsNumbers()));

        else if (request.getSpecificFourQuadrants() != null)
            filters.add(new FourQuadrantSpecificFilter(lottery.getFourQuadrantsNumbers(),
                    request.getSpecificFourQuadrants()));

        else if (request.getQuadrantOccurrenceMap() != null && !request.getQuadrantOccurrenceMap().isEmpty()
                && request.getQuadrantOccurrenceMap().size() <= 4)
            filters.add(new FourQuadrantsQuantityMapFilter(lottery.getFourQuadrantsNumbers(),
                    request.getQuadrantOccurrenceMap()));

        if (request.getColumns() != null && request.getY() != null)
            filters.add(new ColumnFilter(request.getColumns(), request.getY()));
        if (request.getRows() != null && request.getX() != null)
            filters.add(new RowFilter(request.getRows(), request.getX()));

        if (request.getAnchorNumberAmount() != null)
            filters.add(new AnchorNumberFilter(request.getAnchorNumberAmount()));
        if (request.getSequenceLengthColumn() != null)
            filters.add(new SequentialColumnFilter(request.getSequenceLengthColumn()));

        if (request.getConsecutiveAnchorNumberAmount() != null)
            filters.add(new ConsecutiveAnchorNumberFilter(request.getConsecutiveAnchorNumberAmount()));
        if (request.getSequenceLengthRow() != null)
            filters.add(new SequentialRowFilter(request.getSequenceLengthRow()));

        return filters;
    }

    private List<Set<Integer>> selectRandomCombinations(List<Set<Integer>> combinations, Integer numberToSelect) {
        if (numberToSelect == null || numberToSelect <= 0 || combinations.isEmpty())
            return null;

        Collections.shuffle(combinations);
        List<Set<Integer>> selectedCombinations = combinations.subList(0,
                Math.min(numberToSelect, combinations.size()));

        return selectedCombinations;
    }

    private List<Set<Integer>> generateCombinations(List<List<Integer>> grups) {
        List<Set<Integer>> result = new ArrayList<>();
        int[] indices = new int[grups.size()];

        while (true) {
            Set<Integer> combination = new HashSet<>();
            for (int i = 0; i < indices.length; i++) {
                if (!combination.add(grups.get(i).get(indices[i])))
                    throw new DuplicatedGroupNumbersException(grups.get(i).get(indices[i]));
            }

            result.add(combination);

            int pos = indices.length - 1;
            while (pos >= 0 && indices[pos] + 1 >= grups.get(pos).size())
                pos--;

            if (pos < 0)
                break;

            indices[pos]++;
            for (int i = pos + 1; i < indices.length; i++)
                indices[i] = 0;
        }

        return result;
    }
}
