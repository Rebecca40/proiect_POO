import entities.Child;
import entities.Santa;
import input.AnnualChanges;
import input.ChildrenInput;
import input.Input;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
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

     public Simulation (Input input) {
         rounds = new ArrayList<>(input.getNumberOfYears() + 1);

         allChildren = new ArrayList<>();
         for (ChildrenInput child : input.getInitialData().getChildren()) {
             Child currentChild = new Child(child);
             allChildren.add(currentChild);
         }

         annualChanges = input.getAnnualChanges();

         santa = new Santa(input.getSantaBudget());
     }

    void simulateAllRounds () {
        initialRound();
        for (AnnualChanges annualChange : annualChanges) {
            basicRound(annualChange);
        }
    }

     void initialRound () {
         /* Remove all children 18+ */
         allChildren.removeIf(child -> child.getAge() > 18);

         /* Determine age category for each child from the current round */
         allChildren.forEach(Child::determineAgeCategory);

         allChildren.forEach(Child::updateNiceScoreList);



     }

     void basicRound (final AnnualChanges annualChange) {

     }


    public List<Child> getAllChildren() {
        return allChildren;
    }

    public List<List<Child>> getRounds() {
        return rounds;
    }
}
