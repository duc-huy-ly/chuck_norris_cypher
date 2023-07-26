package chucknorris;

import java.util.Scanner;

public class Encoder {
    /*------------------------class attributes and constructor -------------------- */
    private final Scanner scanner;
    private String input;
    private byte[] byteArray;
    private String binaryInput ;
    private String chuckNorrisCypher;

    public Encoder() {
        this.scanner = new Scanner(System.in);
    }
    /*--------------------------public methods---------------------*/
    public void start() {
        enterInputString();
        setByteArray();
        convertToBinary(byteArray);
        convertToChuckNorris(binaryInput);
        displayResult();
    }

    public void setByteArray() {
        this.byteArray = getInput().getBytes();
    }

    public String getInput() {
        return input;
    }
    /*----------------------private methods -------------------------------*/

    private void convertToChuckNorris(String binaryInput) {
        StringBuilder res = new StringBuilder();
        int length = binaryInput.length();
        char currentChar ;
        int i = 0 ;

        while (i < length) {
            if (binaryInput.charAt(i) == '0') {
                res.append("00 ");
                currentChar = '0';
            } else {
                res.append("0 ");
                currentChar = '1';
            }
            while (currentChar == binaryInput.charAt(i)) {
                res.append("0");
                i++;
                if (i == length) {
                    break;
                }
            }
            if (i < length) {
                res.append(" ");
            }
        }
        chuckNorrisCypher = res.toString();
    }

    private void convertToBinary(byte[] byteArray) {
        StringBuilder codeInBinary = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            codeInBinary.append(String.format("%7s", Integer.toBinaryString(input.charAt(i))).replace(" ", "0"));
        }
        this.binaryInput = codeInBinary.toString();
    }
    private void setInput(String input) {
        this.input = input;
    }
    private void enterInputString() {
        System.out.println("Input string:");
        setInput(scanner.nextLine());
        System.out.println();
    }

    private void displayResult(){
        System.out.println("Encoded string:");
        System.out.println(chuckNorrisCypher);
    }


}
