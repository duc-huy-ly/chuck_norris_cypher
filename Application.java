package chucknorris;

import java.util.Scanner;

public class Application {
    Decoder decoder ;
    Encoder encoder ;
    Scanner scanner ;
    Option option ;

    public Application() {
        scanner = new Scanner(System.in);
        decoder = new Decoder();
        encoder = new Encoder();
        option = Option.standby;
    }

    public void start() {
        while (option != Option.exit) {
            System.out.println("Please input operation (encode/decode/exit):");
            String input = scanner.nextLine();
            setOption(input);
            switch (option) {
                case encode -> encoder.start();
                case decode -> decoder.start();
                case exit -> System.out.println("Bye!");
                case invalidOperation -> System.out.println("There is no '" + input + "' operation");
            }
        }
    }

    private void setOption(String next) {
        switch (next) {
            case "encode" -> {
                this.option = Option.encode;
            }
            case "decode" -> {
                this.option = Option.decode;
            }
            case "exit" -> {
                this.option = Option.exit;
            }
            default -> {
                this.option = Option.invalidOperation;
            }
        }
    }
}
