package input;

import java.util.LinkedList;
import java.util.List;

public final class AnnualChanges {
    private Double newSantaBudget;
    private List<SantaGiftsListInput> newGifts;
    private List<ChildrenInput> newChildren;
    private List<ChildUpdateInput> childrenUpdates;

    public AnnualChanges(Double newSantaBudget, List<SantaGiftsListInput> newGifts,
                         List<ChildrenInput> newChildren,
                         List<ChildUpdateInput> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<SantaGiftsListInput> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(List<SantaGiftsListInput> newGifts) {
        this.newGifts = newGifts;
    }

    public List<ChildrenInput> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(List<ChildrenInput> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdateInput> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(List<ChildUpdateInput> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
