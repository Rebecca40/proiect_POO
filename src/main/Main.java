package main;

import checker.Checker;
import common.Constants;

import java.io.File;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {
        File directory = new File(Constants.TESTS_PATH);

//        String inputFile = args[0];
//        String outputFile = args[1];

//        System.out.println(inputFile + " " + outputFile);
        Checker.calculateScore();
    }
}
