package entities;

import enums.Category;
import input.SantaGiftsListInput;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Santa {
    private Double santaBudget;
    private List<Gifts> santaGiftsList;

    public Santa(Double santaBudget, List<Gifts> santaGiftsList) {
        this.santaBudget = santaBudget;
        this.santaGiftsList = santaGiftsList;
    }

    /**
     * Finds all the gifts that santa has from a given category
     * and stores them in a list which will be sorted by price
     * @param category needed category
     * @return sorted gifts list
     */
    public List<Gifts> getCategotyGiftsList(Category category) {
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
    public List<Gifts> sortGiftsByPrice (List<Gifts> gifts) {
        return gifts.stream().sorted(Comparator.comparingDouble(Gifts::getPrice))
                .collect(Collectors.toList());
    }

    public List<Gifts> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(List<Gifts> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    @Override
    public String toString() {
        return "Santa{" +
                "santaBudget=" + santaBudget +
                ", santaGiftsList=" + santaGiftsList +
                '}';
    }
}
