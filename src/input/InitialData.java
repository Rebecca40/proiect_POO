package input;

import java.util.List;

public final class InitialData {
    private List<ChildrenInput> children;
    private List<SantaGiftsListInput> santaGiftsList;

    public InitialData(List<ChildrenInput> children, List<SantaGiftsListInput> santaGiftsList) {
        this.children = children;
        this.santaGiftsList = santaGiftsList;
    }

    public List<ChildrenInput> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenInput> children) {
        this.children = children;
    }

    public List<SantaGiftsListInput> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(List<SantaGiftsListInput> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "children=" + children +
                ", santaGiftsList=" + santaGiftsList +
                '}';
    }
}
