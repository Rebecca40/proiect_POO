package simulation;

import common.Constants;
import database.Database;
import entities.Child;
import entities.Santa;
import factories.ScoreStrategyFactory;
import fileio.input.AnnualChangesInput;
import fileio.input.ChildrenInput;
import fileio.input.Input;
import simulation.Actions.CalculateBudget;
import simulation.Actions.UpdateChildrenInfo;
import simulation.Actions.DistributeGifts;
import simulation.Actions.UpdateSantaInfo;

import java.util.ArrayList;
import java.util.List;

public final class Simulation {
     /*
     *  List in which I keep all the children that
     *  received gifts in the current round
     */
     private List<Child> currentRoundChildren;

     /*
     *  List in which I keep a list of all the children
     *  that received gifts at each round
     */
     private final List<List<Child>> allRoundsChildren;

     private final List<AnnualChangesInput> annualChanges;

     private final Santa santa;

     private final Integer numberOfYears;

    private final Database database;

    public Simulation(final Input input) {
         database = Database.getDatabase();
         database.init(input);
         currentRoundChildren = database.getCurrentRoundChildren();
         annualChanges = database.getAnnualChanges();
         santa = database.getSanta();
         numberOfYears = database.getNumberOfYears();
         allRoundsChildren = new ArrayList<>();
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
         /* Determine age category for each child */
         currentRoundChildren.forEach(Child::determineAgeCategory);

         /* Compute average score for each child */
         for (Child child : currentRoundChildren) {
             ScoreStrategyFactory
                     .createStrategy(child, currentRoundChildren).computeAverageScore();
         }

         /* Compute budget for each child */
        CalculateBudget budget = new CalculateBudget(currentRoundChildren, santa);
        budget.calculateBudget();

         /* Distribute gifts for each child */
        DistributeGifts updateReceviedGifts =
                new DistributeGifts(currentRoundChildren, santa);
        updateReceviedGifts.update();
     }

    /**
     * The basic round of the simulation
     * @param annualChange input information for each annual change
     */
     public void basicRound(final AnnualChangesInput annualChange) {
         List<ChildrenInput> newChildren = annualChange.getNewChildren();

         List<Child> copyChildList = new ArrayList<>();
         for (Child child : currentRoundChildren) {
             Child currentChild = new Child(child);
             copyChildList.add(currentChild);
         }
         currentRoundChildren = copyChildList;

         /* Increase the age of each child */
         currentRoundChildren.forEach(Child::increaseAge);

         /* Add new children to the list only if they are under 18 years old */
         for (ChildrenInput child : newChildren) {
             Integer age = child.getAge();
             if (age <= Constants.TEEN_MAX_AGE) {
                 Child currentChild = new Child(child);
                 currentRoundChildren.add(currentChild);
             }
         }

         /* Remove all children 18+ */
         currentRoundChildren.removeIf(child -> child.getAge() > Constants.TEEN_MAX_AGE);

         /* Update children information */
         UpdateChildrenInfo updateChildrenInfo =
                 new UpdateChildrenInfo(annualChange, currentRoundChildren);
         updateChildrenInfo.update();

         /* Update santa information */
         UpdateSantaInfo updateSantaInfo = new UpdateSantaInfo(annualChange, santa);
         updateSantaInfo.update();

         initialRound();
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

    public Database getDatabase() {
        return database;
    }
}
