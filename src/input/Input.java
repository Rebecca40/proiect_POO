package input;

import java.util.List;

public final class Input {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private List<AnnualChangesInput> annualChanges;

//    public Input(Integer numberOfYears, Double santaBudget, InitialData initialData,
//                 List<AnnualChanges> annualChanges) {
//        this.numberOfYears = numberOfYears;
//        this.santaBudget = santaBudget;
//        this.initialData = initialData;
//        this.annualChanges = annualChanges;
//    }

//    public Input(InitialData initialData) {
//        this.initialData = initialData;
//    }
//
//    public Input(List<AnnualChanges> annualChanges) {
//        this.annualChanges = annualChanges;
//    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialData initialData) {
        this.initialData = initialData;
    }

    public List<AnnualChangesInput> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(List<AnnualChangesInput> annualChanges) {
        this.annualChanges = annualChanges;
    }

    @Override
    public String toString() {
        return "Input{" +
                "\nnumberOfYears=" + numberOfYears +
                ",\n santaBudget=" + santaBudget +
                ",\n initialData=" + initialData +
                ",\n annualChanges=" + annualChanges +
                '}';
    }
}
