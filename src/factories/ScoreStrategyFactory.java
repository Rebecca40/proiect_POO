package factories;

import common.Constants;
import entities.Child;
import interfaces.ScoreStrategy;
import strategies.BabyScoreStrategy;
import strategies.KidScoreStrategy;
import strategies.TeenScoreStrategy;

import java.util.List;

public class ScoreStrategyFactory {
//    private static final ScoreStrategyFactory FACTORY = new ScoreStrategyFactory();
    private static ScoreStrategyFactory singleInstance = null;

    private ScoreStrategyFactory() {

    }

    /**
     * Create the needed strategy
     * @return new strategy
     */
    public static ScoreStrategy createStrategy (Child child, List<Child> children) {
//        for (Child child : children) {
        if (child.getAgeCategory().equals(Constants.BABY)) {
            return new BabyScoreStrategy(children, child.getId());
        } else if (child.getAgeCategory().equals(Constants.KID)) {
            return new KidScoreStrategy(children, child.getId());
        } else if (child.getAgeCategory().equals(Constants.TEEN)) {
            return new TeenScoreStrategy(children, child.getId());
        }
        return null;
    }
//        if (strategyType.equals(Constants.BABY)) {
//            return new BabyScoreStrategy(children);
//        } else if (strategyType.equals(Constants.KID)) {
//            return new KidScoreStrategy(children);
//        } else if (strategyType.equals(Constants.TEEN)) {
//            return new TeenScoreStrategy(children);
//        }

    /**
     * @return the only instance of the class
     */
    public static ScoreStrategyFactory getInstance() {
        if (singleInstance == null) {
            singleInstance = new ScoreStrategyFactory();
        }

        return singleInstance;
    }
//    public static ScoreStrategyFactory getInstance() {
//        return FACTORY;
//    }
}
