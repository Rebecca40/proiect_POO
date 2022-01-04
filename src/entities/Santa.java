package entities;

public final class Santa {
    private Double santaBudget;

    public Santa(Double santaBudget) {
        this.santaBudget = santaBudget;
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
                '}';
    }
}
