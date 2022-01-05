package entities;

import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Santa {
    private Double santaBudget;
    private List<Gifts> santaGiftsList;

    public Santa(final Double santaBudget, final List<Gifts> santaGiftsList) {
        this.santaBudget = santaBudget;
        this.santaGiftsList = santaGiftsList;
    }

    /**
     * Finds all the gifts that santa has from a given category
     * and stores them in a list which will be sorted by price
     * @param category needed category
     * @return sorted gifts list
     */
    public List<Gifts> getCategoryGiftsList(final Category category) {
        List<Gifts> allGiftsFromCategory = new ArrayList<>();
        for (Gifts gift : santaGiftsList) {
            if (gift.getCategory().equals(category)) {
                allGiftsFromCategory.add(gift);
            }
        }
        return sortGiftsByPrice(allGiftsFromCategory);
    }

    /**
     * Sorts the gift list by price (ascending)
     * @param gifts list needs sorting
     * @return sorted list
     */
    public List<Gifts> sortGiftsByPrice(final List<Gifts> gifts) {
        return gifts.stream().sorted(Comparator.comparingDouble(Gifts::getPrice))
                .collect(Collectors.toList());
    }

    public List<Gifts> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<Gifts> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }
}
