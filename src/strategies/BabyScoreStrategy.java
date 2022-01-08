package strategies;

import common.Constants;
import entities.Child;
import interfaces.ScoreStrategy;

import java.util.List;

public final class BabyScoreStrategy implements ScoreStrategy {
    private final List<Child> children;
    private final int childId;

    public BabyScoreStrategy(final List<Child> children, final int childId) {
        this.children = children;
        this.childId = childId;
    }

    @Override
    public void computeAverageScore() {
        for (Child child : children) {
            if (child.getId() == childId) {
                child.setAverageScore(Constants.BABY_SCORE);
            }
        }
    }
}
