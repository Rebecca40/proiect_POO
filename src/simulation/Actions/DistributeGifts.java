package simulation.Actions;

import common.Constants;
import entities.Child;
import entities.Gift;
import entities.Santa;
import enums.Category;

import java.util.List;

public final class DistributeGifts {
    private final List<Child> currentRoundChildren;
    private final Santa santa;

    public DistributeGifts(final List<Child> currentRoundChildren, final Santa santa) {
        this.currentRoundChildren = currentRoundChildren;
        this.santa = santa;
    }

    /**
     *  Distributes gifts to each child
     *  according to their gifts preferences
     */
    public void update() {
        for (Child child : currentRoundChildren) {
            Double childBudget = child.getAssignedBudget();

            if (!child.getReceivedGifts().isEmpty()) {
                child.getReceivedGifts().clear();
            }

            for (Category giftCategory : child.getGiftsPreferences()) {
                /* Get all the gifts from the given category */
                List<Gift> allGiftFromCategory = santa.getCategoryGiftsList(giftCategory);

                /*
                 * The first gift from the list is the gift that will be given to the child
                 * If the list is empty then santa doesn't have a gift from the given category
                 */
                if (!allGiftFromCategory.isEmpty()) {
                    Double giftPrice =
                            allGiftFromCategory.get(Constants.CHEAPEST_GIFT).getPrice();

                    /* Check if the gift doesn't exceed the child's budget*/
                    if (childBudget - giftPrice >= 0) {
                        child.getReceivedGifts()
                                .add(allGiftFromCategory.get(Constants.CHEAPEST_GIFT));
                        childBudget -= giftPrice;
                    }
                }
            }
        }
    }
}
