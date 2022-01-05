package input;

import java.util.List;

public final class Input {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChangesInput> annualChanges;

    public Input() {

    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public List<AnnualChangesInput> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final List<AnnualChangesInput> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
