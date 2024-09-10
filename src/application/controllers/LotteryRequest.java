package src.application.controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LotteryRequest {
    private String lotteryName;
    private List<List<Integer>> groups;
    private Integer randomChoice;
    private Boolean printRemaining;

    private Integer edgeNumber;
    private Boolean removePrimes;

    private Boolean exclusivesMiniQuadrants;
    private Boolean sharedMiniquadrants;

    private Integer coveregeQuadrantNumber;
    private Set<Integer> specificFourQuadrants;
    private Map<Integer, Integer> quadrantOccurrenceMap;

    private Integer columns;
    private Integer y;
    private Integer rows;
    private Integer x;

    private Integer consecutiveAnchorNumberAmount;
    private Integer sequenceLengthColumn;
    private Integer sequenceLengthRow;
    private Integer anchorNumberAmount;

    private LotteryRequest(Builder builder) {
        this.lotteryName = builder.lotteryName;
        this.groups = builder.groups;
        this.randomChoice = builder.randomChoice;
        this.printRemaining = builder.printRemaining;

        this.edgeNumber = builder.edgeNumber;
        this.removePrimes = builder.removePrimes;

        this.exclusivesMiniQuadrants = builder.exclusivesMiniQuadrants;
        this.sharedMiniquadrants = builder.sharedMiniquadrants;

        this.coveregeQuadrantNumber = builder.coveregeQuadrantNumber;
        this.specificFourQuadrants = builder.specificFourQuadrants;
        this.quadrantOccurrenceMap = builder.quadrantOccurrenceMap;

        this.columns = builder.columns;
        this.y = builder.y;
        this.rows = builder.rows;
        this.x = builder.x;

        this.consecutiveAnchorNumberAmount = builder.consecutiveAnchorNumberAmount;
        this.sequenceLengthColumn = builder.sequenceLengthColumn;
        this.sequenceLengthRow = builder.sequenceLengthRow;
        this.anchorNumberAmount = builder.anchorNumberAmount;
    }

    public String getLotteryName() {
        return lotteryName;
    }

    public List<List<Integer>> getGroups() {
        return groups;
    }

    public Integer getRandomChoice() {
        return randomChoice;
    }

    public Integer getEdgeNumber() {
        return edgeNumber;
    }

    public Boolean getRemovePrimes() {
        return removePrimes;
    }

    public Boolean getPrintRemaining() {
        return printRemaining;
    }

    public Boolean getExclusivesMiniQuadrants() {
        return exclusivesMiniQuadrants;
    }

    public Boolean getSharedMiniquadrants() {
        return sharedMiniquadrants;
    }

    public Integer getCoveregeQuadrantNumber() {
        return coveregeQuadrantNumber;
    }

    public Set<Integer> getSpecificFourQuadrants() {
        return specificFourQuadrants;
    }

    public Map<Integer, Integer> getQuadrantOccurrenceMap() {
        return quadrantOccurrenceMap;
    }

    public Integer getColumns() {
        return columns;
    }

    public Integer getY() {
        return y;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getX() {
        return x;
    }
    
    public Integer getConsecutiveAnchorNumberAmount() {
        return consecutiveAnchorNumberAmount;
    }

    public Integer getSequenceLengthColumn() {
        return sequenceLengthColumn;
    }

    public Integer getSequenceLengthRow() {
        return sequenceLengthRow;
    }

    public Integer getAnchorNumberAmount() {
        return anchorNumberAmount;
    }

    public static class Builder {
        private String lotteryName;
        private List<List<Integer>> groups;
        private Integer randomChoice;
        private Boolean printRemaining = false;

        private Integer edgeNumber;
        private Boolean removePrimes;

        private Boolean exclusivesMiniQuadrants;
        private Boolean sharedMiniquadrants;

        private Integer coveregeQuadrantNumber;
        private Set<Integer> specificFourQuadrants;
        private Map<Integer, Integer> quadrantOccurrenceMap;

        private Integer columns, y;
        private Integer rows, x;

        private Integer consecutiveAnchorNumberAmount;
        private Integer sequenceLengthColumn;
        private Integer sequenceLengthRow;
        private Integer anchorNumberAmount;

        public Builder randomChoice(Integer randomChoice) {
            this.randomChoice = randomChoice;
            return this;
        }

        public Builder lotteryName(String lotteryName) {
            this.lotteryName = lotteryName;
            return this;
        }

        public Builder groups(List<List<Integer>> groups) {
            this.groups = groups;
            return this;
        }

        public Builder edgeNumber(Integer edgeNumber) {
            this.edgeNumber = edgeNumber;
            return this;
        }

        public Builder removePrimes(Boolean removePrimes) {
            this.removePrimes = removePrimes;
            return this;
        }

        public Builder removeExclusivesMiniQuadrants(Boolean exclusivesMiniQuadrants) {
            this.exclusivesMiniQuadrants = exclusivesMiniQuadrants;
            return this;
        }

        public Builder removeSharedMiniQuadrants(Boolean sharedMiniquadrants) {
            this.sharedMiniquadrants = sharedMiniquadrants;
            return this;
        }

        public Builder printRemaining(Boolean printRemaining) {
            this.printRemaining = printRemaining;
            return this;
        }

        public Builder coveregeQuadrantNumber(Integer coveregeQuadrantNumber) {
            this.coveregeQuadrantNumber = coveregeQuadrantNumber;
            return this;
        }

        public Builder specificFourQuadrants(Set<Integer> specificFourQuadrants) {
            this.specificFourQuadrants = specificFourQuadrants;
            return this;
        }

        public Builder quadrantOccurrenceMap(Map<Integer, Integer> quadrantOccurrenceMap) {
            this.quadrantOccurrenceMap = quadrantOccurrenceMap;
            return this;
        }

        public Builder columns(Integer columns, Integer numberPerColumn) {
            this.columns = columns;
            this.y = numberPerColumn;
            return this;
        }

        public Builder rows(Integer rows, Integer numberPerRow) {
            this.rows = rows;
            this.x = numberPerRow;
            return this;
        }

        public Builder consecutiveAnchorNumberAmount(Integer consecutiveAnchorNumberAmount) {
            this.consecutiveAnchorNumberAmount = consecutiveAnchorNumberAmount;
            return this;
        }

        public Builder sequenceLengthColumn(Integer sequenceLengthColumn) {
            this.sequenceLengthColumn = sequenceLengthColumn;
            return this;
        }

        public Builder sequenceLengthRow(Integer sequenceLengthRow) {
            this.sequenceLengthRow = sequenceLengthRow;
            return this;
        }

        public Builder anchorNumberAmount(Integer anchorNumberAmount) {
            this.anchorNumberAmount = anchorNumberAmount;
            return this;
        }

        public LotteryRequest build() {
            int filterCount = 0;
            if (coveregeQuadrantNumber != null)
                filterCount++;
            if (specificFourQuadrants != null && !specificFourQuadrants.isEmpty())
                filterCount++;
            if (quadrantOccurrenceMap != null && !quadrantOccurrenceMap.isEmpty())
                filterCount++;

            if (filterCount > 1)
                throw new IllegalArgumentException(
                        "You must specify only one filter type: 'coveregeQuadrantNumber', 'specificFourQuadrants', or 'quadrantOccurrenceMap'.");

            filterCount = 0;
            if (exclusivesMiniQuadrants != null)
                filterCount++;
            if (sharedMiniquadrants != null)
                filterCount++;
            if (filterCount > 1)
                throw new IllegalArgumentException(
                        "You must specify only one filter type: 'exclusivesMiniQuadrants' or 'sharedMiniquadrants'.");

            return new LotteryRequest(this);
        }

    }
}
