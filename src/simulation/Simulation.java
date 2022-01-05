package simulation;

import common.Constants;
import entities.Child;
import entities.Gifts;
import entities.Santa;
import enums.Category;
import factories.ScoreStrategyFactory;
import input.AnnualChangesInput;
import input.ChildUpdateInput;
import input.ChildrenInput;
import input.Input;
import interfaces.ScoreStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Simulation {
     /*
     *  List in which I keep all the children that
     *  received gifts from the current round
     */
     private List<Child> currentRoundChildren;

     /*
     *  List in which I keep a list of all the children
     *  that received gifts at each round
     */
     private List<List<Child>> allRoundsChildren;

     private List<AnnualChangesInput> annualChanges;

     private Santa santa;

     private Integer numberOfYears;

     public Simulation(final Input input) {
         allRoundsChildren = new ArrayList<>();
         numberOfYears = input.getNumberOfYears();

         currentRoundChildren = new ArrayList<>();
         for (ChildrenInput child : input.getInitialData().getChildren()) {
             Child currentChild = new Child(child);
             currentRoundChildren.add(currentChild);
         }

         annualChanges = input.getAnnualChanges();
         santa = new Santa(input.getSantaBudget(),
                 input.getInitialData().getSantaGiftsList());
     }

    /**
     *  Execute the initial round and then execute numberOfYears basic rounds
     */
    public void simulateAllRounds() {
        /* Remove all children 18+ */
        currentRoundChildren.removeIf(child -> child.getAge() > Constants.TEEN_MAX_AGE);
        initialRound();
        allRoundsChildren.add(currentRoundChildren);

        for (int i = 0; i < numberOfYears; i++) {
            basicRound(annualChanges.get(i));
            allRoundsChildren.add(currentRoundChildren);
        }
    }

    /**
     * The initial round of the simulation
     */
    public void initialRound() {
         /* Determine age category for each child from the current round */
         currentRoundChildren.forEach(Child::determineAgeCategory);

         /* Compute average score for each child */
         ScoreStrategyFactory scoreStrategyFactory = ScoreStrategyFactory.getInstance();
         for (Child child : currentRoundChildren) {
             ScoreStrategy strategy =
                     scoreStrategyFactory.createStrategy(child, currentRoundChildren);

             assert strategy != null;
             strategy.computeAverageScore();
         }

         calculateBudget();

         distributeGifts();
     }

    /**
     * The basic round of the simulation
     * @param annualChange input for each annual change
     */
     public void basicRound(final AnnualChangesInput annualChange) {
         List<ChildrenInput> newChildren = annualChange.getNewChildren();
         List<ChildUpdateInput> childrenUpdates = annualChange.getChildrenUpdates();
         List<Gifts> newGifts = annualChange.getNewGifts();

         List<Child> copyChildList = new ArrayList<>();
         for (Child child : currentRoundChildren) {
             Child currentChild = new Child(child);
             copyChildList.add(currentChild);
         }
         currentRoundChildren = copyChildList;

         /* Increase the age of each child */
         currentRoundChildren.forEach(Child::increaseAge);

         for (ChildrenInput child : newChildren) {
             Integer age = child.getAge();
             if (age <= Constants.TEEN_MAX_AGE) {
                 Child currentChild = new Child(child);
                 currentRoundChildren.add(currentChild);
             }
         }

         /* Remove all children 18+ */
         currentRoundChildren.removeIf(child -> child.getAge() > Constants.TEEN_MAX_AGE);

         /* Update the children information */
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

                         /*
                          *  Create a list of new gifts preferences that
                          *  doesn't have duplicates of categories
                         */
                        List<Category> giftsPreferencesNoDuplicates = new ArrayList<>();
                        for (Category category : childUpdate.getGiftsPreferences()) {
                            if (!giftsPreferencesNoDuplicates.contains(category)) {
                                giftsPreferencesNoDuplicates.add(category);
                            }
                        }

                         List<Category> copyGiftsPreferences =
                                 new ArrayList<>(child.getGiftsPreferences());

                         copyGiftsPreferences
                                 .removeIf(giftsPreferencesNoDuplicates::contains);

                             child.setGiftsPreferences(Stream
                                     .concat(giftsPreferencesNoDuplicates.stream(),
                                             copyGiftsPreferences.stream())
                                     .collect(Collectors.toList()));
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

    /**
     *  Distributes gifts to each child according to their gifts preferences
     */
    public void distributeGifts() {
         for (Child child : currentRoundChildren) {
             Double childBudget = child.getAssignedBudget();

             if (!child.getReceivedGifts().isEmpty()) {
                 child.getReceivedGifts().clear();
             }

             for (Category giftCategory : child.getGiftsPreferences()) {
                 /* Get all the gifts from the given category */
                 List<Gifts> allGiftsFromCategory = santa.getCategoryGiftsList(giftCategory);

                 /*
                 * The first gift from the list is the gift that will be given to the child
                 * If the list is empty then santa doesn't have a gift from the given category
                 */
                 if (!allGiftsFromCategory.isEmpty()) {
                     Double giftPrice =
                             allGiftsFromCategory.get(Constants.CHEAPEST_GIFT).getPrice();

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
     *  Calculate the budget assigned for each child
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

    public List<AnnualChangesInput> getAnnualChanges() {
        return annualChanges;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public List<List<Child>> getAllRoundsChildren() {
        return allRoundsChildren;
    }
}
