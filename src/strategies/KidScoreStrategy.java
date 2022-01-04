package strategies;

import entities.Child;
import interfaces.ScoreStrategy;

import java.util.List;

public class KidScoreStrategy implements ScoreStrategy {
    private List<Child> children;
    private int childId;

    public KidScoreStrategy(List<Child> children, int childId) {
        this.children = children;
        this.childId = childId;
    }


    // am presupun ca lista de scoruri nu este niciodata goala pt ca e tarziu si inca nu am cautat
    /*
       For kids the average score is given by the arithmetic sum of the nice socres
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
                child.setAverageScore(average);
            }
        }
    }
}
