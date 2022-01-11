package fileio.input;

import entities.Gift;

import java.util.List;

public final class AnnualChangesInput {
    private Double newSantaBudget;
    private List<Gift> newGifts;
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

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<Gift> newGifts) {
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
