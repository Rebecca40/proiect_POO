package factories;

import common.Constants;
import entities.Child;
import interfaces.ScoreStrategy;
import strategies.BabyScoreStrategy;
import strategies.KidScoreStrategy;
import strategies.TeenScoreStrategy;

import java.util.List;

public final class ScoreStrategyFactory {
    private ScoreStrategyFactory() {

    }

    /**
     * Create the needed strategy
     * @return new strategy
     */
    public static ScoreStrategy createStrategy(final Child child, final List<Child> children) {
        return switch (child.getAgeCategory()) {
            case Constants.BABY -> new BabyScoreStrategy(children, child.getId());
            case Constants.KID -> new KidScoreStrategy(children, child.getId());
            case Constants.TEEN -> new TeenScoreStrategy(children, child.getId());
            default -> null;
        };
    }
}
