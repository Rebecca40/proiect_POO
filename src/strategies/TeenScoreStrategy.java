package strategies;

import entities.Child;
import interfaces.ScoreStrategy;

import java.util.List;

public class TeenScoreStrategy implements ScoreStrategy {
    private List<Child> children;
    private int childId;

    public TeenScoreStrategy(List<Child> children, int childId) {
        this.children = children;
        this.childId = childId;
    }

    @Override
    public void computeAverageScore() {

    }
}
