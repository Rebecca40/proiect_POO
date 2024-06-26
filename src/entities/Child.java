package entities;

import common.Constants;
import enums.Category;
import enums.Cities;
import enums.ElvesType;
import fileio.input.ChildrenInput;

import java.util.ArrayList;
import java.util.List;

public final class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private List<Category> giftsPreferences;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<Gift> receivedGifts;
    private String ageCategory;
    private Double niceScore;
    private Double niceScoreBonus;
    private ElvesType elf;

    public Child(final Child child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.city = child.city;
        this.age = child.age;
        this.giftsPreferences = child.giftsPreferences;
        this.niceScore = child.niceScore;
        this.ageCategory = child.ageCategory;
        this.niceScoreBonus = child.niceScoreBonus;
        this.elf = child.elf;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());
        receivedGifts = new ArrayList<>();
    }

    public Child(final ChildBuilder childBuilder) {
        id = childBuilder.id;
        lastName = childBuilder.lastName;
        firstName = childBuilder.firstName;
        city = childBuilder.city;
        age = childBuilder.age;
        giftsPreferences = childBuilder.giftsPreferences;
        niceScore = childBuilder.niceScore;
        niceScoreHistory = childBuilder.niceScoreHistory;
        receivedGifts = childBuilder.receivedGifts;
        this.niceScoreBonus = childBuilder.niceScoreBonus;
        this.elf = childBuilder.elf;
    }

    public static class ChildBuilder {
        private final Integer id;
        private final String lastName;
        private final String firstName;
        private final Cities city;
        private final Integer age;
        private final List<Category> giftsPreferences;
        private final List<Double> niceScoreHistory;
        private final List<Gift> receivedGifts;
        private final Double niceScore;
        private Double niceScoreBonus = 0.0; /* Optional */
        private final ElvesType elf;

        public ChildBuilder(final ChildrenInput childInput) {
            id = childInput.getId();
            lastName = childInput.getLastName();
            firstName = childInput.getFirstName();
            city = childInput.getCity();
            age = childInput.getAge();
            giftsPreferences = childInput.getGiftsPreferences();
            niceScore = childInput.getNiceScore();
            niceScoreHistory = new ArrayList<>();
            niceScoreHistory.add(niceScore);
            receivedGifts = new ArrayList<>();
            elf = childInput.getElf();
        }

        /**
         *  Set the optional parameter
         */
        public ChildBuilder niceScoreBonus(final Double niceBonus) {
            niceScoreBonus = niceBonus;
            return this;
        }

        /**
         * Returns new instance of the Child class
         */
        public Child build() {
            return new Child(this);
        }
    }

    /**
     *  Determine the age category of each child
     */
    public void determineAgeCategory() {
        if (age < Constants.BABY_MAX_AGE) {
            ageCategory = Constants.BABY;
        } else if (age < Constants.KID_MAX_AGE) {
            ageCategory = Constants.KID;
        } else if (age <= Constants.TESTS_NUMBER) {
            ageCategory = Constants.TEEN;
        }
    }

    /**
     * Increase age
     */
    public void increaseAge() {
        age++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setAgeCategory(final String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }
}
