package factories;

import common.Constants;
import entities.Child;
import entities.Santa;
import interfaces.DistributeGiftsStrategy;
import interfaces.ScoreStrategy;
import strategies.averageScore.BabyScoreStrategy;
import strategies.averageScore.KidScoreStrategy;
import strategies.averageScore.TeenScoreStrategy;
import strategies.giftsDistribution.IdDistributionStrategy;
import strategies.giftsDistribution.NiceScoreCityDistributionStrategy;
import strategies.giftsDistribution.NiceScoreDistributionStrategy;

import java.util.List;

public final class DistributeGiftsFactory {
    private DistributeGiftsFactory() {
    }

    /**
     * Create the needed strategy
     * @return new strategy
     */
    public static DistributeGiftsStrategy createStrategy(final String strategy,
                                                         final List<Child> children,
                                                         final Santa santa) {
        return switch (strategy) {
            case Constants.ID -> new IdDistributionStrategy(children, santa);
            case Constants.NICESCORE -> new NiceScoreDistributionStrategy(children, santa);
            case Constants.CITYSCORE -> new NiceScoreCityDistributionStrategy(children, santa);
            default -> null;
        };
    }
}
