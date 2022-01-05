package input;

import entities.Gifts;

import java.util.List;

public final class AnnualChangesInput {
    private Double newSantaBudget;
    private List<Gifts> newGifts;
    private List<ChildrenInput> newChildren;
    private List<ChildUpdateInput> childrenUpdates;

    public AnnualChangesInput() {
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Gifts> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<Gifts> newGifts) {
        this.newGifts = newGifts;
    }

    public List<ChildrenInput> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<ChildrenInput> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdateInput> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final List<ChildUpdateInput> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }
}
