package simulation.Actions;

import entities.Child;
import enums.Category;
import fileio.input.AnnualChangesInput;
import fileio.input.ChildUpdateInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class UpdateChildrenInfo {
    private final AnnualChangesInput annualChange;
    private final List<Child> currentRoundChildren;

    public UpdateChildrenInfo(final AnnualChangesInput annualChange,
                              final List<Child> currentRoundChildren) {
        this.annualChange = annualChange;
        this.currentRoundChildren = currentRoundChildren;
    }

    /**
     * Update the children information
     */
    public void update() {
        List<ChildUpdateInput> childrenUpdates = annualChange.getChildrenUpdates();

        for (ChildUpdateInput childUpdate : childrenUpdates) {
            for (Child child : currentRoundChildren) {
                /* Check if the id matches */
                if (child.getId().equals(childUpdate.getId())) {
                    /* Add new nice score to the child's nice scores list */
                    if (childUpdate.getNiceScore() != null) {
                        child.getNiceScoreHistory().add(childUpdate.getNiceScore());
                        child.setNiceScore(childUpdate.getNiceScore());
                    }

                    /* Update elf */
                    if (childUpdate.getElf() != null) {
                        child.setElf(childUpdate.getElf());
                    }
                    /*
                     *  Add new gifts categories to the beginning
                     *  of the child's gifts preferences list
                     */
                    if (!childUpdate.getGiftsPreferences().isEmpty()) {

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
    }
}
