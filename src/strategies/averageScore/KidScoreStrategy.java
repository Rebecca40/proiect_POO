package strategies.averageScore;

import common.Constants;
import entities.Child;
import interfaces.ScoreStrategy;

import java.util.List;

public final class KidScoreStrategy implements ScoreStrategy {
    private final List<Child> children;
    private final int childId;

    public KidScoreStrategy(final List<Child> children, final int childId) {
        this.children = children;
        this.childId = childId;
    }


    /*
       For kids the average score is given by
       the arithmetic sum of the nice scores
     */
    @Override
    public void computeAverageScore() {
        for (Child child : children) {
            if (child.getId() == childId) {
                double average = 0.0;
                for (double score : child.getNiceScoreHistory()) {
                    average += score;
                }
                average /= child.getNiceScoreHistory().size();

                if (child.getNiceScoreBonus() != 0) {
                    average += average * child.getNiceScoreBonus() / Constants.ONE_HUNDRED;
                }

                if (average > Constants.PERFECT_SCORE) {
                    average = Constants.PERFECT_SCORE;
                }
                child.setAverageScore(average);

            }
        }
    }
}
