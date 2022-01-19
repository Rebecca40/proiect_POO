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

    public YellowElfAction(final List<Child> currentRoundChildren) {
        this.currentRoundChildren = currentRoundChildren;
    }

    /**
     *  Function that helps the yellow elf to assign a gift
     *  to all the children that haven't received one yet only
     *  if Santa has in his bag the cheapest gift from the child's
     *  most wanted gift category
     */
    public void applyYellowElfAction(final Santa santa) {
        for (Child child : currentRoundChildren) {
            if (child.getElf().equals(ElvesType.YELLOW) && child.getReceivedGifts().isEmpty()) {

                /*
                * Get from santa's bag all the gifts
                * from the child's most wanted category
                */
                List<Gift> allGiftsFromCategory = santa.getCategoryGiftsListYellowElf(
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
    public void findGift(final Gift gift, final List<Gift> giftsList) {
        for (Gift gift1 : giftsList) {
            if (gift.equals(gift1)) {
                gift1.decreaseQuantity();
                break;
            }
        }
    }
}
