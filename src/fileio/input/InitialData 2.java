package fileio.input;

import entities.Gift;

import java.util.List;

public final class InitialData {
    private List<ChildrenInput> children;
    private List<Gift> santaGiftsList;

    public InitialData() {
    }

    public List<ChildrenInput> getChildren() {
        return children;
    }

    public void setChildren(final List<ChildrenInput> children) {
        this.children = children;
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<Gift> santaGiftList) {
        this.santaGiftsList = santaGiftList;
    }
}
