package simulation.Actions;

import common.Constants;
import entities.Child;
import entities.Gift;
import entities.Santa;
import enums.ElvesType;

import java.util.ArrayList;
import java.util.List;

public final class YellowElfAction {
    private final List<Child> currentRoundChildren;

    public YellowElfAction(List<Child> currentRoundChildren) {
        this.currentRoundChildren = currentRoundChildren;
    }

    // s ar putea sa am nevoie de copy constructor
    public void applyAction (Santa santa) {
        for (Child child : currentRoundChildren) {
            if (child.getElf().equals(ElvesType.YELLOW) && child.getReceivedGifts().isEmpty()) {

                /*
                * Get from santa's bag all the gifts
                * from the child's most wanted category
                */
                List<Gift> allGiftsFromCategory = santa.getCategoryGiftsList(
                                child.getGiftsPreferences().get(Constants.FIRST));

                if (!allGiftsFromCategory.isEmpty()) {
                    /* Check if santa has in his bag the cheapest gift */
                    if (allGiftsFromCategory.get(Constants.CHEAPEST_GIFT).getQuantity() != 0) {
                        child.getReceivedGifts()
                                .add(allGiftsFromCategory.get(Constants.CHEAPEST_GIFT));

                        /* Decrease the gift's quantity*/
                        List<Gift> copySantaGifts = new ArrayList<>(santa.getSantaGiftsList());
                        findGift(allGiftsFromCategory
                                .get(Constants.CHEAPEST_GIFT), copySantaGifts);
                        santa.setSantaGiftsList(copySantaGifts);
                    }
                }
            }
        }
    }

    /**
     * Finds a given gift in santa's gifts list and decreases its quantity
     */
    public void findGift (Gift gift, List<Gift> giftsList) {
        for (Gift gift1 : giftsList) {
            if (gift.equals(gift1)) {
                gift1.decreaseQuantity();
                break;
            }
        }
    }
}
