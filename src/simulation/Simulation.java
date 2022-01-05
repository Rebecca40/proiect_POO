package simulation;

import common.Constants;
import entities.Child;
import entities.Gifts;
import entities.Santa;
import enums.Category;
import factories.ScoreStrategyFactory;
import input.*;
import interfaces.ScoreStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Simulation {
     /*
     *  List in which I keep all the children that
     *  received gifts from the current round
     */
     List<Child> currentRoundChildren;

     /*
     *  List in which I keep a list of all the children
     *  that received gifts at each round
     */
     List<List<Child>> allRoundsChildren;

     List<AnnualChangesInput> annualChanges;

     Santa santa;

     public Simulation (final Input input) {
         allRoundsChildren = new ArrayList<>(input.getNumberOfYears() + 1);

         currentRoundChildren = new ArrayList<>();
         for (ChildrenInput child : input.getInitialData().getChildren()) {
             Child currentChild = new Child(child);
             currentRoundChildren.add(currentChild);
         }

         annualChanges = input.getAnnualChanges();

         santa = new Santa(input.getSantaBudget(),
                 input.getInitialData().getSantaGiftsList());
     }

    public void simulateAllRounds () {
        currentRoundChildren.removeIf(child -> child.getAge() > 18);
        initialRound();
        allRoundsChildren.add(currentRoundChildren);
//        System.out.println(currentRoundChildren);
//
        for (AnnualChangesInput annualChange : annualChanges) {
//            System.out.println(annualChange);
            basicRound(annualChange);
//            System.out.println(currentRoundChildren);
//            List<Child> subjects = new ArrayList<>();
//            for (Child subject : currentRoundChildren) {
//                subjects.add(new Child(subject));
//            }
            allRoundsChildren.add(currentRoundChildren);
        }
//        System.out.println(allRoundsChildren);
//        System.out.println(allRoundsChildren.size());
//        allRoundsChildren.add(currentRoundChildren);



//        System.out.println(allRoundsChildren.get(0));
//        System.out.println(allRoundsChildren.get(1));
//        System.out.println();

    }

     public void initialRound () {
         /* Remove all children 18+ */
         currentRoundChildren.removeIf(child -> child.getAge() > 18);

         /* Determine age category for each child from the current round */
         currentRoundChildren.forEach(Child::determineAgeCategory);

//         currentRoundChildren.forEach(Child::updateNiceScoreHistory);

         /* Compute average score for each child */
         ScoreStrategyFactory scoreStrategyFactory = ScoreStrategyFactory.getInstance();
         for (Child child : currentRoundChildren) {
             ScoreStrategy strategy = scoreStrategyFactory.createStrategy(child, currentRoundChildren);

             assert strategy != null;
             strategy.computeAverageScore();
         }

         calculateBudget();

         distributeGifts();
     }

     public void basicRound(final AnnualChangesInput annualChange) {
         List<ChildrenInput> newChildren = annualChange.getNewChildren();
         List<ChildUpdateInput> childrenUpdates = annualChange.getChildrenUpdates();
         List<Gifts> newGifts = annualChange.getNewGifts();
         List<Child> a = new ArrayList<>();
         for (Child child : currentRoundChildren) {
             Child currentChild = new Child(child);
             a.add(currentChild);
         }
//         System.out.println("a = "+ a.get(0).getAge());
//
//         System.out.println("b = "+ currentRoundChildren.get(0).getAge());
         currentRoundChildren = a;

         currentRoundChildren.forEach(Child::increaseAge);


//         System.out.println(a);

//         a.get(0).setAge(6);
//         System.out.println("a = "+ a.get(0).getAge());

//         System.out.println(a);
//         System.out.println("b = "+currentRoundChildren.get(0).getAge());

         for (ChildrenInput child : newChildren) {
             Integer age = child.getAge();
             if (age <= 18) {
                 Child currentChild = new Child(child);
                 currentRoundChildren.add(currentChild);
             }
         }

         /* Remove all children 18+ */
         currentRoundChildren.removeIf(child -> child.getAge() > 18);

         for (ChildUpdateInput childUpdate : childrenUpdates) {
             for (Child child : currentRoundChildren) {
                 /* Check if the id matches */
                 if (child.getId().equals(childUpdate.getId())) {
                     /* Add new nice score to the child's nice scores list */
                     if (childUpdate.getNiceScore() != null) {
                         child.getNiceScoreHistory().add(childUpdate.getNiceScore());
                         child.setNiceScore(childUpdate.getNiceScore());
                     }

                     /*
                      *  Add new gifts categories to the beginning
                      *  of the child's gifts preferences list
                      */
                     if (childUpdate.getGiftsPreferences() != null) {
                         List<Category> newList =
                                 Stream.concat(childUpdate.getGiftsPreferences().stream(),
                                                 child.getGiftsPreferences().stream())
                                         .collect(Collectors.toList());

                             child.setGiftsPreferences(newList);
                     }
                 }
             }
         }

         santa.setSantaBudget(annualChange.getNewSantaBudget());

         List<Gifts> newList =
                 Stream.concat(santa.getSantaGiftsList().stream(),
                                 newGifts.stream())
                         .collect(Collectors.toList());
         santa.setSantaGiftsList(newList);
         initialRound();
     }

     public void distributeGifts() {
         for (Child child : currentRoundChildren) {
             Double childBudget = child.getAssignedBudget();

             if (!child.getReceivedGifts().isEmpty()) {
                 child.getReceivedGifts().clear();
             }

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
         for (Child child : currentRoundChildren) {
             averageScoreSum += child.getAverageScore();
         }

        Double budgetUnit = santa.getSantaBudget() / averageScoreSum;

         for (Child child : currentRoundChildren) {
             child.setAssignedBudget(budgetUnit * child.getAverageScore());
         }

     }

    public List<Child> getCcurrentRoundChildren() {
        return currentRoundChildren;
    }

    public List<List<Child>> getAllRoundsChildren() {
        return allRoundsChildren;
    }
}
