package fileio.output;

import entities.Child;
import entities.Gifts;
import enums.Category;
import enums.Cities;

import java.util.List;

public final class ChildOutput {
    private final Integer id;
    private final String lastName;
    private final String firstName;
    private final Cities city;
    private final Integer age;
    private final List<Category> giftsPreferences;
    private final Double averageScore;
    private final List<Double> niceScoreHistory;
    private final Double assignedBudget;
    private final List<Gifts> receivedGifts;

    public ChildOutput(final Child childOutput) {
        id = childOutput.getId();
        lastName = childOutput.getLastName();
        firstName = childOutput.getFirstName();
        city = childOutput.getCity();
        age = childOutput.getAge();
        giftsPreferences = childOutput.getGiftsPreferences();
        averageScore = childOutput.getAverageScore();
        niceScoreHistory = childOutput.getNiceScoreHistory();
        assignedBudget = childOutput.getAssignedBudget();
        receivedGifts = childOutput.getReceivedGifts();
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Cities getCity() {
        return city;
    }

    public Integer getAge() {
        return age;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public List<Gifts> getReceivedGifts() {
        return receivedGifts;
    }
}
