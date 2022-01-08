package simulation.Actions;

import entities.Gift;
import entities.Santa;
import fileio.input.AnnualChangesInput;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class UpdateSantaInfo {
    private final AnnualChangesInput annualChange;
    private final Santa santa;

    public UpdateSantaInfo(final AnnualChangesInput annualChange, final Santa santa) {
        this.annualChange = annualChange;
        this.santa = santa;
    }

    /**
     * Update santa's budget and santa's list of gifts
     */
    public void update() {
        santa.setSantaBudget(annualChange.getNewSantaBudget());

        List<Gift> newGifts = annualChange.getNewGifts();
        santa.setSantaGiftsList(Stream.concat(
                        santa.getSantaGiftsList().stream(), newGifts.stream())
                .collect(Collectors.toList()));
    }
}
