package strategies.giftsDistribution;

import entities.Child;
import entities.Santa;
import interfaces.DistributeGiftsStrategy;
import simulation.Actions.DistributeGifts;

import java.util.List;

public final class IdDistributionStrategy implements DistributeGiftsStrategy {
    private List<Child> currentRoundChildren;
    private Santa santa;

    public IdDistributionStrategy(List<Child> currentRoundChildren,
                                  Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    @Override
    public void distributeGifts() {
        /* Distribute gifts for each child */
        DistributeGifts updateReceivedGifts =
                new DistributeGifts(currentRoundChildren, santa);
        updateReceivedGifts.update();
//        System.out.println(currentRoundChildren.get(0));

    }
}
