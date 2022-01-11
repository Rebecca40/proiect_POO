package simulation.Actions;

import entities.Child;
import entities.Santa;

import java.util.List;

public final class CalculateBudget {
    private final List<Child> currentRoundChildren;
    private final Santa santa;

    public CalculateBudget(final List<Child> currentRoundChildren, final Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    /**
     *  Calculate the budget assigned for each child
     */
    public void calculateBudget() {
        Double averageScoreSum = 0.0;
        for (Child child : currentRoundChildren) {
            averageScoreSum += child.getAverageScore();
        }

        Double budgetUnit = santa.getSantaBudget() / averageScoreSum;

        for (Child child : currentRoundChildren) {
            child.setAssignedBudget(budgetUnit * child.getAverageScore());
        }
    }
}
