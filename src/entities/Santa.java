package entities;

import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Santa {
    private Double santaBudget;
    private List<Gift> santaGiftsList;

    public Santa(final Double santaBudget, final List<Gift> santaGiftList) {
        this.santaBudget = santaBudget;
        this.santaGiftsList = santaGiftList;
    }

    /**
     * Finds all the gifts that santa has from a given category
     * and stores them in a list which will be sorted by price
     * @param category needed category
     * @return sorted gifts list
     */
    public List<Gift> getCategoryGiftsList(final Category category) {
        List<Gift> allGiftFromCategory = new ArrayList<>();
        for (Gift gift : santaGiftsList) {
            if (gift.getCategory().equals(category)) {
                allGiftFromCategory.add(gift);
            }
        }
        return sortGiftsByPrice(allGiftFromCategory);
    }

    /**
     * Sorts the gift list by price (ascending)
     * @param gifts list that needs sorting
     * @return sorted list
     */
    public List<Gift> sortGiftsByPrice(final List<Gift> gifts) {
        return gifts.stream().sorted(Comparator.comparingDouble(Gift::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Finds a given gift in santa's gifts list and decreases its quantity
     */
    public void findGift (Gift gift) {
        for (Gift gift1 : santaGiftsList) {
            if (gift.equals(gift1)) {
                gift1.decreaseQuantity();
                break;
            }
        }
    }

    public void setSantaGiftsList(final List<Gift> santaGiftList) {
        this.santaGiftsList = santaGiftList;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    @Override
    public String toString() {
        return "Santa{" +
                "santaBudget=" + santaBudget +
                ", santaGiftsList=" + santaGiftsList +
                '}';
    }
}
