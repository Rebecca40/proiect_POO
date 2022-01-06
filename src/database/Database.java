package database;

import entities.Child;
import entities.Santa;
import fileio.input.AnnualChangesInput;
import fileio.input.ChildrenInput;
import fileio.input.Input;

import java.util.ArrayList;
import java.util.List;

public final class Database {
    private static Database instance = null;

    /*
     *  List in which I keep all the children that
     *  received gifts from the current round
     */
    private List<Child> currentRoundChildren;

    private List<AnnualChangesInput> annualChanges;

    private Santa santa;

    private Integer numberOfYears;

    private Database() {

    }

    /**
     *  Returns the singleton instance of database
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Transform input objects to entities objects
     */
    public void init(final Input input) {
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

    public List<Child> getCurrentRoundChildren() {
        return currentRoundChildren;
    }

    public void setCurrentRoundChildren(final List<Child> currentRoundChildren) {
        this.currentRoundChildren = currentRoundChildren;
    }

    public List<AnnualChangesInput> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final List<AnnualChangesInput> annualChanges) {
        this.annualChanges = annualChanges;
    }

    public Santa getSanta() {
        return santa;
    }

    public void setSanta(final Santa santa) {
        this.santa = santa;
    }

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }
}
