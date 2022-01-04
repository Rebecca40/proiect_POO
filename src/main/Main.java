package main;

import checker.Checker;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import common.Constants;
import entities.Child;
import entities.Gifts;
import input.Input;
import lombok.SneakyThrows;
import output.OutputData;
import simulation.Simulation;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

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
    public static void main(final String[] args) throws IOException {

//        Input inputData = objectMapper.readValue(new File("tests/test1.json"), Input.class);

        for (int i = 1; i <= Constants.TESTS_NUMBER; i++) {
            ObjectMapper objectMapper = new ObjectMapper();
            Input inputData = objectMapper.readValue
                    (new File(Constants.TESTS_PATH + i + Constants.FILE_EXTENSION), Input.class);
            Simulation simulation = new Simulation(inputData);
            simulation.simulateAllRounds();
//            List<List<Child>> children = simulation.getRounds();

            /* Write output in file */
            OutputData outputData = new OutputData(simulation.getRounds());
            Path path = Paths.get(Constants.OUT_PATH);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            objectMapper = new ObjectMapper();
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
//            objectWriter.writeValue(new File("output/out_1.json"), outputData);
            objectWriter.writeValue(new File
                    (Constants.OUTPUT_PATH + i + Constants.FILE_EXTENSION), outputData);


        }
//        System.out.println(inputData);
//        System.out.println(simulation.getAllChildren());
//        System.out.println();

//        Gifts giift = new Gifts("Uno", 10.0, "Board Games");
//
//        children.get(0).get(0).getReceivedGifts().add(giift);
//        Gifts giift2 = new Gifts("Dress", 45.0, "Clothes");
//        children.get(0).get(0).getReceivedGifts().add(giift2);


//        Checker.calculateScore();
    }
}
