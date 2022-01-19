package strategies.giftsDistribution;

import entities.Child;
import entities.Santa;
import interfaces.DistributeGiftsStrategy;
import simulation.Actions.DistributeGifts;

import java.util.List;

public final class IdDistributionStrategy implements DistributeGiftsStrategy {
    private final List<Child> currentRoundChildren;
    private final Santa santa;

    public IdDistributionStrategy(final List<Child> currentRoundChildren,
                                  final Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    @Override
    public void distributeGifts() {
        /* Distribute gifts for each child */
        DistributeGifts updateReceivedGifts =
                new DistributeGifts(currentRoundChildren, santa);
        updateReceivedGifts.update();
    }
}
