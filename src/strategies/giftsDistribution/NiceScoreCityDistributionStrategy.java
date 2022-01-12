package strategies.giftsDistribution;

import entities.Child;
import entities.Santa;
import interfaces.DistributeGiftsStrategy;

import java.util.List;

public final class NiceScoreCityDistributionStrategy implements DistributeGiftsStrategy {
    private List<Child> currentRoundChildren;
    private Santa santa;

    public NiceScoreCityDistributionStrategy(List<Child> currentRoundChildren, Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    @Override
    public void distributeGifts() {

    }

//    public void
}
