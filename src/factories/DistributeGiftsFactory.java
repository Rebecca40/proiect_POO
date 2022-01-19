package factories;

import common.Constants;
import entities.Child;
import entities.Santa;
import interfaces.DistributeGiftsStrategy;
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
            case Constants.NICE_SCORE -> new NiceScoreDistributionStrategy(children, santa);
            case Constants.CITY_SCORE -> new NiceScoreCityDistributionStrategy(children, santa);
            default -> null;
        };
    }
}
