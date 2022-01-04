package strategies;

import entities.Child;
import interfaces.ScoreStrategy;

import java.util.List;

public class BabyScoreStrategy implements ScoreStrategy {
    private List<Child> children;
    private int childId;

    public BabyScoreStrategy(List<Child> children, int childId) {
        this.children = children;
        this.childId = childId;
    }


    // presupun ca lista de copii este ordonata in functie de id
    @Override
    public void computeAverageScore() {
        for (Child child : children) {
            if (child.getId() == childId) {
                child.setAverageScore(10.0);
            }
        }
    }
}
