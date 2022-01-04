package entities;

import common.Constants;
import entities.Gifts;
import enums.Category;
import enums.Cities;
import factories.ScoreStrategyFactory;
import input.ChildrenInput;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Cities city;
    private Integer age;
    private List<Category> giftsPreference;
    private Double averageScore;
    private List<Double> niceScoreHistory;
    private Double assignedBudget;
    private List<Gifts> receivedGifts;
    private String ageCategory;
    private Double niceScore;

//    private S

    public Child(Integer id, String lastName, String firstName, Cities city,
                 Integer age, List<Category> giftsPreference, Double niceScore) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreference = giftsPreference;
        this.niceScore = niceScore;
        niceScoreHistory = new ArrayList<>();
        receivedGifts = new ArrayList<>();
    }

    public Child (ChildrenInput childInput) {
        id = childInput.getId();
        lastName = childInput.getLastName();
        firstName = childInput.getFirstName();
        city = childInput.getCity();
        age = childInput.getAge();
        giftsPreference = childInput.getGiftsPreferences();
        niceScoreHistory = new ArrayList<>();
        receivedGifts = new ArrayList<>();
    }

    private void getagecategory_computeStrategy(List<Child> children) {
//        ScoreStrategyFactory scoreStrategyFactory = ScoreStrategyFactory(); //.getInstance();
//        ScoreStrategy strategy = scoreStrategyFactory.createStrategy("baby", children);
//        ScoreStrategy strategy = scoreStrategyFactory.createStrategy("baby", children);
//        ScoreStrategy strategy = scoreStrategyFactory.createStrategy("baby", children);

//        strategy.chooseProducers();
    }

    /**
     *  Determins the age category of each child
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

    public void updateNiceScoreList () {
        if (niceScore == null) {
            System.out.println("Nice socre ul nu poate fi null");
        }
        niceScoreHistory.add(niceScore);
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

    public List<Category> getGiftsPreference() {
        return giftsPreference;
    }

    public void setGiftsPreference(List<Category> giftsPreference) {
        this.giftsPreference = giftsPreference;
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

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", city=" + city +
                ", age=" + age +
                ", giftsPreference=" + giftsPreference +
                ", averageScore=" + averageScore +
                ", niceScoreHistory=" + niceScoreHistory +
                ", assignedBudget=" + assignedBudget +
                ", receivedGifts=" + receivedGifts +
                ", ageCategory='" + ageCategory + '\'' +
                '}';
    }
}
