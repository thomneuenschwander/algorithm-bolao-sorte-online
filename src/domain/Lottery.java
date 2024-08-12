package src.domain;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public enum Lottery {
    QUINA("quina", 5, 80, 8, 10),
    MEGA_SENA("megasena", 6, 60, 6, 10);

    final String name;
    final int numbersPerGame;
    final int numberRange;

    final int rows;
    final int columns;
    
    final Set<Integer> edgeNumbers;
    final Set<Integer> primeNumbers;
    final Set<Set<Integer>> fourQuadrantsNumbers;
    final Map<Integer, Set<Integer>> miniQuadrantsNumbers;

    private static final Map<String, Lottery> lotteryNameMap = new HashMap<>();

    static {
        for (Lottery lottery : Lottery.values())
            lotteryNameMap.put(lottery.getName().toLowerCase(), lottery);
    }

    public static Optional<Lottery> get(String lotteryRequest) {
        return Optional.ofNullable(lotteryNameMap.get(lotteryRequest.toLowerCase()));
    }

    Lottery(String name, int numbersPerGame, int numberRange, int rows, int columns) {
        this.name = name;
        this.numbersPerGame = numbersPerGame;
        this.numberRange = numberRange;
        this.rows = rows;
        this.columns = columns;
        this.edgeNumbers = generateEdgeNumbers(numberRange);
        this.primeNumbers = generatePrimeNumbers(numberRange);
        this.fourQuadrantsNumbers = calculateFourQuadrants(rows / 2, columns);
        this.miniQuadrantsNumbers = calculateMiniQuadrants(rows, columns);
    }

    private Set<Integer> generateEdgeNumbers(final int range) {
        Set<Integer> edges = new HashSet<>();

        for (int i = 1, j = range; i <= 10; i++, j--) {
            edges.add(i);
            edges.add(j);
        }

        int leftColumn = 11, rightColumn = 20;
        while (rightColumn < range) {
            edges.add(rightColumn);
            edges.add(leftColumn);

            leftColumn += 10;
            rightColumn += 10;
        }
        return edges;
    }

    private Set<Integer> generatePrimeNumbers(int range) {
        Set<Integer> primes = new HashSet<>();
        for (int i = 2; i <= range; i++) {
            if (isPrime(i))
                primes.add(i);
        }
        return primes;
    }

    private boolean isPrime(int number) {
        if (number <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public List<Integer> generateLotteryTicket() {
        Set<Integer> lotteryTicket = new HashSet<>();
        for (int i = 1; i <= numberRange; i++)
            lotteryTicket.add(i);
        return new LinkedList<>(lotteryTicket);
    }

    private Set<Set<Integer>> calculateFourQuadrants(int midRow, int columns) {
        Set<Set<Integer>> quadrants = new LinkedHashSet<>();

        Set<Integer> q1 = new HashSet<>();
        Set<Integer> q2 = new HashSet<>();
        Set<Integer> q3 = new HashSet<>();
        Set<Integer> q4 = new HashSet<>();

        int midColumn = columns / 2;

        for (int i = 1; i <= numberRange; i++) {
            int row = (i - 1) / columns;
            int column = (i - 1) % columns;

            if (row < midRow && column < midColumn)
                q1.add(i);
            else if (row < midRow && column >= midColumn)
                q2.add(i);
            else if (row >= midRow && column < midColumn)
                q3.add(i);
            else if (row >= midRow && column >= midColumn)
                q4.add(i);
        }

        quadrants.add(q1);
        quadrants.add(q2);
        quadrants.add(q3);
        quadrants.add(q4);

        return quadrants;
    }

    private Map<Integer, Set<Integer>> calculateMiniQuadrants(int rows, int columns) {
        Map<Integer, Set<Integer>> miniQuadrants = new LinkedHashMap<>();
        int quadrantsName = 1;
        for (int row = 0; row < rows; row += 2) {
            for (int col = 0; col < columns; col += 2) {
                Set<Integer> miniQuadrant = new HashSet<>();
                miniQuadrant.add((row * columns) + col + 1);
                miniQuadrant.add((row * columns) + col + 2);
                miniQuadrant.add(((row + 1) * columns) + col + 1);
                miniQuadrant.add(((row + 1) * columns) + col + 2);
                miniQuadrants.put(quadrantsName++, miniQuadrant);
            }
        }

        return miniQuadrants;
    }

    public String getName() {
        return name;
    }

    public int getNumbersPerGame() {
        return numbersPerGame;
    }

    public int getNumberRange() {
        return numberRange;
    }

    public Set<Integer> getEdgeNumbers() {
        return edgeNumbers;
    }

    public Set<Integer> getPrimeNumbers() {
        return primeNumbers;
    }

    public Set<Set<Integer>> getFourQuadrantsNumbers() {
        return fourQuadrantsNumbers;
    }

    public Map<Integer, Set<Integer>> getMiniQuadrantsNumbers() {
        return miniQuadrantsNumbers;
    }

    public static Map<String, Lottery> getLotterynamemap() {
        return lotteryNameMap;
    }
}
