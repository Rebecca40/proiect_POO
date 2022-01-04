package simulation;

import entities.Child;
import entities.Santa;
import factories.ScoreStrategyFactory;
import input.AnnualChanges;
import input.ChildrenInput;
import input.Input;
import interfaces.ScoreStrategy;

import java.util.ArrayList;
import java.util.List;

public final class Simulation {
     /*
     *  List in which I keep all the children that
     *  received gifts from the current round
     */
     List<Child> allChildren;

     /*
     *  List in which I keep a list of all the children
     *  that received gifts at each round
     */
     List<List<Child>> rounds;

     List<AnnualChanges> annualChanges;

     Santa santa;

     public Simulation (final Input input) {
         rounds = new ArrayList<>(input.getNumberOfYears() + 1);

         allChildren = new ArrayList<>();
         for (ChildrenInput child : input.getInitialData().getChildren()) {
             Child currentChild = new Child(child);
             allChildren.add(currentChild);
         }

         annualChanges = input.getAnnualChanges();

         santa = new Santa(input.getSantaBudget());
     }

    public void simulateAllRounds () {
        initialRound();
        rounds.add(allChildren);
        for (AnnualChanges annualChange : annualChanges) {
            basicRound(annualChange);
            rounds.add(allChildren);
        }

    }

     public void initialRound () {
         /* Remove all children 18+ */
         System.out.println(allChildren.size());

         allChildren.removeIf(child -> child.getAge() > 18);

//         System.out.println(allChildren.size());
         /* Determine age category for each child from the current round */
         allChildren.forEach(Child::determineAgeCategory);

         allChildren.forEach(Child::updateNiceScoreList);

         /* Compute average score for each child */
         ScoreStrategyFactory scoreStrategyFactory = ScoreStrategyFactory.getInstance();

         for (Child child : allChildren) {
             ScoreStrategy strategy = scoreStrategyFactory.createStrategy(child, allChildren);

             assert strategy != null;
             strategy.computeAverageScore();
         }

         calculateBudget();



     }

     public void basicRound(final AnnualChanges annualChange) {

     }

    /**
     *  Calculate the buget assigned for each child
     */
    public void calculateBudget() {
         Double averageScoreSum = 0.0;
         for (Child child : allChildren) {
             averageScoreSum += child.getAverageScore();
         }

        Double budgetUnit = santa.getSantaBudget() / averageScoreSum;

         for (Child child : allChildren) {
             child.setAssignedBudget(budgetUnit * child.getAverageScore());
         }
     }

    public List<Child> getAllChildren() {
        return allChildren;
    }

    public List<List<Child>> getRounds() {
        return rounds;
    }
}
