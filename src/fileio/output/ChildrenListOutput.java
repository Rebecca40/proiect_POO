package fileio.output;

import entities.Child;

import java.util.ArrayList;
import java.util.List;

public final class ChildrenListOutput {
    private final List<ChildOutput> children;

    public ChildrenListOutput(final List<Child> children) {
        this.children = new ArrayList<>();
        for (Child child : children) {
            this.children.add(new ChildOutput(child));
        }
    }

    public List<ChildOutput> getChildren() {
        return children;
    }
}
