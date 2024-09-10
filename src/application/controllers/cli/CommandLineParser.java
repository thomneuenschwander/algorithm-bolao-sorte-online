package src.application.controllers.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import src.application.controllers.LotteryRequest;

public class CommandLineParser {

    public static LotteryRequest parseArguments(String[] args) {
        String lotteryName = null;
        List<List<Integer>> groups = new ArrayList<>();
        Boolean printRemaining = false;
        Integer randomChoice = null;

        Integer edgeNumber = null;
        Boolean removePrimes = null;
        Boolean exclusivesMiniQuadrants = null;
        Boolean sharedMiniquadrants = null;

        Integer coveregeQuadrantNumber = null;
        Set<Integer> specificFourQuadrants = null;
        Map<Integer, Integer> quadrantOccurrenceMap = new HashMap<>();

        Integer columns = null, y = null;
        Integer rows = null, x = null;

        Integer consecutiveAnchorNumberAmount = null;
        Integer sequenceLengthColumn = null;
        Integer sequenceLengthRow = null;
        Integer anchorNumberAmount = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-n":
                case "--name":
                    lotteryName = args[++i];
                    break;

                case "-g":
                case "--groups":
                    String[] groupsArray = args[++i].split(";");
                    for (String group : groupsArray) {
                        List<Integer> groupList = Arrays.stream(group.split(","))
                                .map(Integer::parseInt)
                                .collect(Collectors.toList());
                        groups.add(groupList);
                    }
                    break;

                case "-e":
                case "--edge":
                    edgeNumber = Integer.parseInt(args[++i]);
                    break;

                case "-p":
                case "--primes":
                    removePrimes = Boolean.parseBoolean(args[++i]);
                    break;

                case "-r":
                case "--print-remaining":
                    printRemaining = true;
                    break;

                case "-rc":
                case "--random-choice":
                    randomChoice = Integer.parseInt(args[++i]);
                    break;

                case "-emq":
                case "--exclusives-mini-quadrants":
                    exclusivesMiniQuadrants = Boolean.parseBoolean(args[++i]);
                    break;

                case "-smq":
                case "--shared-miniquadrants":
                    sharedMiniquadrants = Boolean.parseBoolean(args[++i]);
                    break;

                case "-cqn":
                case "--coverage-quadrant-number":
                    coveregeQuadrantNumber = Integer.parseInt(args[++i]);
                    break;

                case "-sfq":
                case "--specific-four-quadrants":
                    specificFourQuadrants = new HashSet<>(Arrays.asList(args[++i].split(",")))
                            .stream()
                            .map(Integer::parseInt)
                            .collect(Collectors.toSet());
                    break;

                case "-qom":
                case "--quadrant-occurrence-map":
                    String[] mapEntries = args[++i].split(";");
                    for (String entry : mapEntries) {
                        String[] keyVal = entry.split(",");
                        quadrantOccurrenceMap.put(Integer.parseInt(keyVal[0]), Integer.parseInt(keyVal[1]));
                    }
                    break;

                case "-col":
                case "--columns":
                    columns = Integer.parseInt(args[++i]);
                    break;

                case "-y":
                    y = Integer.parseInt(args[++i]);
                    break;

                case "-row":
                case "--rows":
                    rows = Integer.parseInt(args[++i]);
                    break;

                case "-x":
                    x = Integer.parseInt(args[++i]);
                    break;

                case "-can":
                case "--consecutive-anchor-number":
                    consecutiveAnchorNumberAmount = Integer.parseInt(args[++i]);
                    break;

                case "-scol":
                case "--sequence-length-column":
                    sequenceLengthColumn = Integer.parseInt(args[++i]);
                    break;

                case "-srow":
                case "--sequence-length-row":
                    sequenceLengthRow = Integer.parseInt(args[++i]);
                    break;

                case "-an":
                case "--anchor-number-amount":
                    anchorNumberAmount = Integer.parseInt(args[++i]);
                    break;

                default:
                    System.out.println("Unknown argument: " + args[i]);
                    break;
            }
        }

        return new LotteryRequest.Builder()
                .lotteryName(lotteryName)
                .groups(groups)
                .printRemaining(printRemaining)
                .randomChoice(randomChoice)

                .edgeNumber(edgeNumber)
                .removePrimes(removePrimes)

                .removeExclusivesMiniQuadrants(exclusivesMiniQuadrants)
                .removeSharedMiniQuadrants(sharedMiniquadrants)

                .coveregeQuadrantNumber(coveregeQuadrantNumber)
                .specificFourQuadrants(specificFourQuadrants)
                .quadrantOccurrenceMap(quadrantOccurrenceMap)

                .columns(columns, y)
                .rows(rows, x)

                .consecutiveAnchorNumberAmount(consecutiveAnchorNumberAmount)
                .sequenceLengthColumn(sequenceLengthColumn)
                .sequenceLengthRow(sequenceLengthRow)
                .anchorNumberAmount(anchorNumberAmount)

                .build();
    }

    public static void showUsage() {
        System.out.println("Usage:");
        System.out.println("java -jar filename.jar \\");
        System.out.println("\t-n <lotteryName> \\");
        System.out.println("\t-g <groups> \\");
        System.out.println("\t-e <edgeNumber> \\");
        System.out.println("\t-p <removePrimes> \\");
        System.out.println("\t-r <printRemaining> \\");
        System.out.println("\t-rc <randomChoice> \\");
        System.out.println("\t-emq <exclusivesMiniQuadrants> \\");
        System.out.println("\t-smq <sharedMiniQuadrants> \\");
        System.out.println("\t-cqn <coveregeQuadrantNumber> \\");
        System.out.println("\t-sfq <specificFourQuadrants> \\");
        System.out.println("\t-qom <quadrantOccurrenceMap> \\");
        System.out.println("\t-col <columns> \\");
        System.out.println("\t-y <y> \\");
        System.out.println("\t-row <rows> \\");
        System.out.println("\t-x <x> \\");
        System.out.println("\t-can <consecutiveAnchorNumberAmount> \\");
        System.out.println("\t-scol <sequenceLengthColumn> \\");
        System.out.println("\t-srow <sequenceLengthRow> \\");
        System.out.println("\t-an <anchorNumberAmount>");
    }
}
