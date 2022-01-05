package output;

import entities.Child;

import java.util.ArrayList;
import java.util.List;

public final class OutputData {
    private final List<ChildrenListOutput> annualChildren = new ArrayList<>();

    public OutputData(final List<List<Child>> annualChildren) {
        for (List<Child> list : annualChildren) {
            this.annualChildren.add(new ChildrenListOutput(list));
        }
    }

    public List<ChildrenListOutput> getAnnualChildren() {
        return annualChildren;
    }
}
