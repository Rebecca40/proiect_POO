package factories;

import common.Constants;
import entities.Child;
import interfaces.ScoreStrategy;
import strategies.BabyScoreStrategy;
import strategies.KidScoreStrategy;
import strategies.TeenScoreStrategy;

import java.util.List;

public class ScoreStrategyFactory {

    private ScoreStrategyFactory() {

    }

    /**
     * Create the needed strategy
     * @param strategyType the age category
     * @param children the list of all the children
     * @return new strategy
     */
    public static ScoreStrategy createStrategy (String strategyType, List<Child> children) {
        for (Child child : children) {
            if (child.getAge() < 5) {
                return new BabyScoreStrategy(children, child.getId());
            } else if (child.getAge() < 12) {
                return new KidScoreStrategy(children, child.getId());
            } else if (child.getAge() <= 18) {
                return new TeenScoreStrategy(children, child.getId());
            }
        }
//        if (strategyType.equals(Constants.BABY)) {
//            return new BabyScoreStrategy(children);
//        } else if (strategyType.equals(Constants.KID)) {
//            return new KidScoreStrategy(children);
//        } else if (strategyType.equals(Constants.TEEN)) {
//            return new TeenScoreStrategy(children);
//        }
        return null;
    }
}
