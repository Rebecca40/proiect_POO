package strategies;

import entities.Child;
import interfaces.ScoreStrategy;

import java.util.List;

public final class TeenScoreStrategy implements ScoreStrategy {
    private final List<Child> children;
    private final int childId;

    public TeenScoreStrategy(final List<Child> children, final int childId) {
        this.children = children;
        this.childId = childId;
    }

    @Override
    public void computeAverageScore() {
        for (Child child : children) {
            if (child.getId() == childId) {
                Double niceScoreSum = 0.0;
                Double sum = 0.0;

                for (int i = 0; i < child.getNiceScoreHistory().size(); i++) {
                    sum += i + 1;
                    niceScoreSum += (i + 1) * child.getNiceScoreHistory().get(i);
                }
                child.setAverageScore(niceScoreSum / sum);
            }
        }
    }
}
