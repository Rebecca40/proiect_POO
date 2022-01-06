package fileio.input;

import entities.Gifts;

import java.util.List;

public final class InitialData {
    private List<ChildrenInput> children;
    private List<Gifts> santaGiftsList;

    public InitialData() {
    }

    public List<ChildrenInput> getChildren() {
        return children;
    }

    public void setChildren(final List<ChildrenInput> children) {
        this.children = children;
    }

    public List<Gifts> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<Gifts> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
