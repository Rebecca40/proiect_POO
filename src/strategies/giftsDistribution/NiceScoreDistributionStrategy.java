package strategies.giftsDistribution;

import entities.Child;
import entities.Santa;
import interfaces.DistributeGiftsStrategy;
import simulation.Actions.DistributeGifts;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class NiceScoreDistributionStrategy implements DistributeGiftsStrategy {
    private List<Child> currentRoundChildren;
    private Santa santa;

    public NiceScoreDistributionStrategy(List<Child> currentRoundChildren, Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    @Override
    public void distributeGifts() {
        List<Child> children = currentRoundChildren.stream()
                .sorted(Comparator.comparing(Child::getAverageScore).reversed()
                .thenComparing(Child::getId))
                .collect(Collectors.toList());

        System.out.println(santa.getSantaGiftsList());
        System.out.println();
        /* Distribute gifts for each child */
        DistributeGifts updateReceivedGifts =
                new DistributeGifts(children, santa);
        updateReceivedGifts.update();

        System.out.println(santa.getSantaGiftsList());

        currentRoundChildren = children.stream()
                .sorted(Comparator.comparing(Child::getId))
                .collect(Collectors.toList());
    }
}
