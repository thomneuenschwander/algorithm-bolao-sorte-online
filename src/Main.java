package src;

import src.application.LotteryServiceImpl;
import src.application.controllers.LotteryRequest;
import src.application.controllers.LotteryResponse;
import src.application.controllers.cli.CommandLineParser;
import src.domain.LotteryService;

public class Main {
    public static void main(String[] args) {

        if (args == null || args.length == 0){
            CommandLineParser.showUsage();
            System.exit(0);
        }

        LotteryRequest request = CommandLineParser.parseArguments(args);

        LotteryService service = new LotteryServiceImpl();

        LotteryResponse response = service.lotteryPlay(request);

        System.out.println(response);
    }

}
