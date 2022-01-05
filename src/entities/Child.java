package entities;

import common.Constants;
import entities.Gifts;
import enums.Category;
import enums.Cities;
import factories.ScoreStrategyFactory;
import input.ChildrenInput;
import interfaces.ScoreStrategy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private List<Category> giftsPreferences;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<Gifts> receivedGifts;
    private String ageCategory;
    private Double niceScore;

//    private S

    public Child(Child child) {
        this.id = child.id;
        this.lastName = child.lastName;
        this.firstName = child.firstName;
        this.city = child.city;
        this.age = child.age;
        this.giftsPreferences = child.giftsPreferences;
        this.niceScore = child.niceScore;
        this.ageCategory = new String(child.ageCategory);
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(child.getNiceScoreHistory());

        receivedGifts = new ArrayList<>();
    }

    public Child (ChildrenInput childInput) {
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
    }

    /**
     *  Determine the age category of each child
     */
    public void determineAgeCategory() {
        if (age < 5) {
            ageCategory = Constants.BABY;
        } else if (age < 12) {
            ageCategory = Constants.KID;
        } else if (age <= 18) {
            ageCategory = Constants.TEEN;
        }
    }

    public void updateNiceScoreHistory () {
        if (niceScore == null) {
            niceScoreHistory.add(niceScore);

//            System.out.println("Nice socre ul nu poate fi null");
        }
//        this.niceScore = niceScore;
    }

    /**
     * Increase age with one
     */
    public void increaseAge() {
        age++;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public void setAssignedBudget(Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public List<Gifts> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(List<Gifts> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public String getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(String ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(Double niceScore) {
        this.niceScore = niceScore;
    }

    @Override
    public String toString() {
        return "\nChild{" +
                "\nid=" + id +
                ",\n lastName='" + lastName + '\'' +
                ",\n firstName='" + firstName + '\'' +
                ",\n city=" + city +
                ",\n age=" + age +
                ",\n giftsPreferences=" + giftsPreferences +
                ",\n averageScore=" + averageScore +
                ",\n niceScoreHistory=" + niceScoreHistory +
                ",\n assignedBudget=" + assignedBudget +
                ",\n receivedGifts=" + receivedGifts +
                ",\n ageCategory='" + ageCategory + '\'' +
                ",\n niceScore=" + niceScore +
                '}';
    }
}
