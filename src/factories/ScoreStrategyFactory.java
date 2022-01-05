package factories;

import common.Constants;
import entities.Child;
import interfaces.ScoreStrategy;
import strategies.BabyScoreStrategy;
import strategies.KidScoreStrategy;
import strategies.TeenScoreStrategy;

import java.util.List;

public final class ScoreStrategyFactory {
    private static ScoreStrategyFactory singleInstance = null;

    private ScoreStrategyFactory() {

    }

    /**
     * Create the needed strategy
     * @return new strategy
     */
    public static ScoreStrategy createStrategy(final Child child, final List<Child> children) {
        if (child.getAgeCategory().equals(Constants.BABY)) {
            return new BabyScoreStrategy(children, child.getId());
        } else if (child.getAgeCategory().equals(Constants.KID)) {
            return new KidScoreStrategy(children, child.getId());
        } else if (child.getAgeCategory().equals(Constants.TEEN)) {
            return new TeenScoreStrategy(children, child.getId());
        }
        return null;
    }

    /**
     * @return the only instance of the class
     */
    public static ScoreStrategyFactory getInstance() {
        if (singleInstance == null) {
            singleInstance = new ScoreStrategyFactory();
        }
        return singleInstance;
    }
}
