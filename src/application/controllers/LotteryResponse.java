package src.application.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import src.application.FilteredResult;

public class LotteryResponse {
    private final String lotteryName;
    private final FilteredResult filteredResult;
    private final LocalDateTime timestamp;
    private final boolean showFilteredCombinations;
    private final List<Set<Integer>> randomSelectedCombinations;  

    private LotteryResponse(Builder builder) {
        this.lotteryName = builder.lotteryName;
        this.filteredResult = builder.filteredResult;
        this.timestamp = builder.timestamp;
        this.showFilteredCombinations = builder.showFilteredCombinations;
        this.randomSelectedCombinations = builder.randomSelectedCombinations;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public FilteredResult getFilteredResult() {
        return filteredResult;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isShowFilteredCombinations() {
        return showFilteredCombinations;
    }

    public List<Set<Integer>> getRandomSelectedCombinations() {
        return randomSelectedCombinations;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        StringBuilder response = new StringBuilder();
        response.append("Lottery Name: ").append(lotteryName).append("\n");
        response.append("Timestamp: ").append(timestamp.format(formatter)).append("\n");
        response.append("Number of all generated combinations: ").append(filteredResult.totalCombinations())
                .append("\n");
        response.append("Number of filtered combinations: ").append(filteredResult.getFilteredCombinations())
                .append("\n");
        response.append("Number of remaining combinations: ").append(filteredResult.getRemainingCombinations())
                .append("\n");

        if (showFilteredCombinations && filteredResult.remainingCombinationsList() != null
                && !filteredResult.remainingCombinationsList().isEmpty()) {
            response.append("Remaining combinations:\n");
            filteredResult.remainingCombinationsList()
                    .forEach(combination -> response.append(combination).append("\n"));
        }

        if (randomSelectedCombinations != null && !randomSelectedCombinations.isEmpty()) {
            response.append("Randomly Selected Combinations:\n");
            randomSelectedCombinations.forEach(combination -> response.append(combination).append("\n"));
        }

        return response.toString();
    }

    public static class Builder {
        private String lotteryName;
        private FilteredResult filteredResult;
        private LocalDateTime timestamp;
        private boolean showFilteredCombinations;
        private List<Set<Integer>> randomSelectedCombinations;  

        public Builder lotteryName(String lotteryName) {
            this.lotteryName = lotteryName;
            return this;
        }

        public Builder filteredResult(FilteredResult filteredResult) {
            this.filteredResult = filteredResult;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder showFilteredCombinations(boolean showFilteredCombinations) {
            this.showFilteredCombinations = showFilteredCombinations;
            return this;
        }

        public Builder randomSelectedCombinations(List<Set<Integer>> randomSelectedCombinations) {
            this.randomSelectedCombinations = randomSelectedCombinations;
            return this;
        }

        public LotteryResponse build() {
            return new LotteryResponse(this);
        }
    }
}
