package simulation.Actions;

import common.Constants;
import entities.Child;
import entities.Santa;
import enums.ElvesType;

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
        Double childBudget;
        for (Child child : currentRoundChildren) {
            childBudget = budgetUnit * child.getAverageScore();

            if (child.getElf().equals(ElvesType.PINK)) {
                childBudget += childBudget * Constants.THIRTY / Constants.ONE_HUNDRED;
            } else if (child.getElf().equals(ElvesType.BLACK)) {
                childBudget -= childBudget * Constants.THIRTY / Constants.ONE_HUNDRED;
            }

            child.setAssignedBudget(childBudget);

        }
    }
}
