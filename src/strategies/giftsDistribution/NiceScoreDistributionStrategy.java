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
    private final Santa santa;

    public NiceScoreDistributionStrategy(final List<Child> currentRoundChildren,
                                         final Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    @Override
    public void distributeGifts() {
        /* Sort children by average score (descendant) and in case of equality sort by id */
        List<Child> children = currentRoundChildren.stream()
                .sorted(Comparator.comparing(Child::getAverageScore).reversed()
                .thenComparing(Child::getId))
                .collect(Collectors.toList());

        /* Distribute gifts for each child */
        DistributeGifts updateReceivedGifts =
                new DistributeGifts(children, santa);
        updateReceivedGifts.update();

        /* Sort children by id */
        currentRoundChildren = children.stream()
                .sorted(Comparator.comparing(Child::getId))
                .collect(Collectors.toList());
    }
}
