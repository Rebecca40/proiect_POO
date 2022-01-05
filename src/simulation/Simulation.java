package simulation;

import common.Constants;
import entities.Child;
import entities.Gifts;
import entities.Santa;
import enums.Category;
import factories.ScoreStrategyFactory;
import input.AnnualChangesInput;
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

     List<AnnualChangesInput> annualChanges;

     Santa santa;

     public Simulation (final Input input) {
         rounds = new ArrayList<>(input.getNumberOfYears() + 1);

         allChildren = new ArrayList<>();
         for (ChildrenInput child : input.getInitialData().getChildren()) {
             Child currentChild = new Child(child);
             allChildren.add(currentChild);
         }

         annualChanges = input.getAnnualChanges();

         santa = new Santa(input.getSantaBudget(),
                 input.getInitialData().getSantaGiftsList());
     }

    public void simulateAllRounds () {
        initialRound();
        rounds.add(allChildren);
        for (AnnualChangesInput annualChange : annualChanges) {
            basicRound(annualChange);
            rounds.add(allChildren);
        }

    }

     public void initialRound () {
         /* Remove all children 18+ */
         allChildren.removeIf(child -> child.getAge() > 18);

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

         distributeGifts();

     }

     public void basicRound(final AnnualChangesInput annualChange) {
//         for (Child child : allChildren) {
//             if (!child.getReceivedGifts().isEmpty()) {
//                 child.getReceivedGifts().clear();
//             }
//         }
     }


     public void distributeGifts() {
         for (Child child : allChildren) {
             Double childBudget = child.getAssignedBudget();

//             if (!child.getReceivedGifts().isEmpty()) {
//                 child.getReceivedGifts().clear();
//             }

             for (Category giftCategory : child.getGiftsPreferences()) {
                 /* Get all the gifts from the given category */
                 List<Gifts> allGiftsFromCategory = santa.getCategotyGiftsList(giftCategory);

                 /*
                 * The first gift from the list is the gift that will be given to the child
                 * If the list is empty then santa doesn't have a gift from the given category
                 */
                 if (!allGiftsFromCategory.isEmpty()) {
                     Double giftPrice = allGiftsFromCategory.get(Constants.CHEAPEST_GIFT).getPrice();

                     /* Check if the gift doesn't exceed the child's budget*/
                     if (childBudget - giftPrice >= 0) {
                         child.getReceivedGifts()
                                 .add(allGiftsFromCategory.get(Constants.CHEAPEST_GIFT));
                         childBudget -= giftPrice;
                     }
                 }
             }
         }
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
             System.out.println(child.getFirstName() + " " + child.getAssignedBudget());
         }

     }

    public List<Child> getAllChildren() {
        return allChildren;
    }

    public List<List<Child>> getRounds() {
        return rounds;
    }
}
