package input;

import common.Constants;
import enums.Category;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class InputLoader {
    /**
     * The path to the input file
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    public Input readData() throws IOException {
        JSONParser jsonParser = new JSONParser();
//        InitialData initialData = null;
//        List<AnnualChanges> annualChanges = null;
        Input input = null;
//        List<ChildrenInput> children;
//        List<SantaGiftsListInput> santaGiftsList;
        try {
            /* Parsing the contents of the JSON file */
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            Integer numberOfYears = Integer.parseInt(jsonObject.get(Constants.NUMBER_OF_YEARS)
                    .toString());
            Double santaBudget = Double.parseDouble(jsonObject.get(Constants.SANTA_BUDGET)
                    .toString());
//            JSONObject jsonInitialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
//
//            JSONArray jsonChildren = (JSONArray)  jsonInitialData.get(Constants.CHILDREN);
//            JSONArray jsonSantaGiftsList = (JSONArray)  jsonInitialData.get(Constants.SANTA_GIFTS_LIST);

//            InitialData initialData = readInitialData(jsonObject);
//            List<AnnualChanges> annualChanges = readAnnualChanges(jsonObject);

//            input = new Input(numberOfYears, santaBudget, initialData, annualChanges);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

//        List<Category> giftsPreferences;
//        List<SantaGiftsListInput> newGifts;
//        List<ChildrenInput> newChildren;
//        List<ChildUpdateInput> childrenUpdates;

        return input;
    }

//    public InitialData readInitialData(final JSONObject jsonObject) {
////        InitialData initialData = new InitialData()
//        JSONObject jsonInitialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
//
//        JSONArray jsonChildren = (JSONArray)  jsonInitialData.get(Constants.CHILDREN);
//        JSONArray jsonSantaGiftsList = (JSONArray)  jsonInitialData.get(Constants.SANTA_GIFTS_LIST);
//
//        List<ChildrenInput> children = new LinkedList<>();
//        for (Object jsonIterator : jsonChildren) {
////            JSONObject jsonChild = (JSONObject) jsonIterator;
//            children.add(new ChildrenInput(Integer.parseInt(((JSONObject) jsonIterator).get(Constants.ID)
//                    .toString()),
//                    (String) ((JSONObject) jsonIterator).get(Constants.LASTNAME),
//                    (String) ((JSONObject) jsonIterator).get(Constants.FIRSTNAME),
//                    Integer.parseInt(((JSONObject) jsonIterator).get(Constants.AGE)
//                                    .toString()),
//                    (String) ((JSONObject) jsonIterator).get(Constants.CITY),
//                    Double.parseDouble(((JSONObject) jsonIterator).get(Constants.NICE_SCORE)
//                                            .toString()),
//                    convertJSONArray((JSONArray) ((JSONObject) jsonIterator)
//                                    .get(Constants.GIFTS_PREFERENCES))
//
//                    ));
//        }
//
//        List<SantaGiftsListInput> santaGiftsList = new LinkedList<>();
//        for (Object jsonIterator : jsonSantaGiftsList) {
//            santaGiftsList.add(new SantaGiftsListInput((String) ((JSONObject) jsonIterator).get(Constants.PRODUCT_NAME),
//                    Integer.parseInt(((JSONObject) jsonIterator).get(Constants.PRICE)
//                            .toString()),
//                    (String) ((JSONObject) jsonIterator).get(Constants.CATEGORY),
//                    ));
//        }
//
//        return new InitialData(children, santaGiftsList);
//    }

//    public List<AnnualChanges> readAnnualChanges(final JSONObject jsonObject) {
//        JSONArray jsonAnnualChanges = (JSONArray)  jsonObject.get(Constants.ANNUAL_CHANGES);
//
//    }

        /**
         * Transforms an array of JSON's into an array of strings
         * @param array of JSONs
         * @return a list of strings
         */
        public static ArrayList<String> convertJSONArray(final JSONArray array) {
            if (array != null) {
                ArrayList<String> finalArray = new ArrayList<>();
                for (Object object : array) {
                    finalArray.add((String) object);
                }
                return finalArray;
            } else {
                return null;
            }
        }
}
