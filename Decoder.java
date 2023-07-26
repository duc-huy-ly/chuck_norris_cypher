package chucknorris;

import java.util.Scanner;

public class Decoder {
    private final Scanner scanner;
    private String input;
    private String binaryInput;
    private String decodedInput;

    public Decoder() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Input encoded string:");
        this.input = scanner.nextLine();
        boolean hasError = hasErrorInput(input);
        if (hasError) {
            System.out.println("Encoded string is not valid.");
        } else {
            decodedInput = decodeBinaryToReadable(binaryInput);
            displayResult();
        }

    }

    private boolean hasErrorInput(String input) {
        boolean moreThan0AndSpaces=false, firstBlockIsNot0or00=false, numberOfBlocksIsOdd=false, lengthNotMultipleOf7=false;
        String[] decomposedInput = input.split("", input.length());
        for (String c: decomposedInput) {
            if (!c.equals("0") & !c.equals(" ")) {
                return true;
            }
        }
        if (!decomposedInput[0].equals("0") && !decomposedInput[0].equals("00")) {
            return true;
        }
        String blocks = input.split("(?=\\d \\d)").toString();
        if (blocks.length()%2==1) {
            return true;
        }
        try {
            binaryInput = decodeInputToBinary(input);
        } catch (Exception e) {
            this.binaryInput = null;
            return true;
        }
        if (binaryInput.length() % 7 != 0) {
            this.binaryInput = null;
            return true;
        }
        return false;
    }

    private String decodeBinaryToReadable(String binaryInput) {
        StringBuilder sb = new StringBuilder();
        char character ;
        String[] blocks = binaryInput.split("(?<=\\G.{7})");
        for (String binaryByte: blocks) {
            int decimalValue = Integer.parseInt(binaryByte, 2);
            character = (char)decimalValue;
            sb.append(character);
        }
        return sb.toString();
    }

    private void displayResult() {
        System.out.printf("\nDecoded string:\n%s\n",decodedInput);
    }

    private String decodeInputToBinary(String input) throws Exception {
        String[] splitInput = input.split(" ");
        int i = 0;
        StringBuilder result = new StringBuilder();
        String firstWord, secondWord;
        char currentChar;
        while (i < splitInput.length) {
            firstWord = splitInput[i];
            secondWord = splitInput[i + 1];
            switch (firstWord) {
                case "0" -> {
                    currentChar = '1';
                }
                case "00" -> {
                    currentChar = '0';
                }
                default -> {
                    throw new Exception("Unknown character");
                }
            }
            result.append(String.valueOf(currentChar).repeat(secondWord.length()));
            i += 2;
        }
        return result.toString();
    }

}
