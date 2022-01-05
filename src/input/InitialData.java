package input;

import entities.Gifts;

import java.util.List;

public final class InitialData {
    private List<ChildrenInput> children;
    private List<Gifts> santaGiftsList;

    public List<ChildrenInput> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenInput> children) {
        this.children = children;
    }

    public List<Gifts> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(List<Gifts> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    @Override
    public String toString() {
        return "\nInitialData{" +
                "\nchildren=" + children +
                ",\n santaGiftsList=" + santaGiftsList +
                '}';
    }
}
