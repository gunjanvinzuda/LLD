package org.example;

import java.util.Map;

public class ChangeHandler {
    private Denomination denomination;
    private ChangeHandler next;

    ChangeHandler(Denomination denomination, ChangeHandler next){
        this.denomination = denomination;
        this.next = next;
    }

    public boolean processChange(double remainingAmount, Map<Denomination, Integer> tempPool, Map<Denomination, Integer> processedChange){
        if(remainingAmount == 0) return true;

        int availableNotes = tempPool.get(denomination);
        int faceValue = denomination.getValue();
        int requiredNotes = (int)(remainingAmount/faceValue);

        int notesToUse = Math.min(availableNotes, requiredNotes);

        if(notesToUse > 0){
            processedChange.put(denomination, notesToUse);
            tempPool.put(denomination, tempPool.get(denomination)-notesToUse);
            remainingAmount -= (notesToUse*faceValue);
        }

        if(remainingAmount == 0) return true;
        if(next != null) return next.processChange(remainingAmount, tempPool, processedChange);
        return false;
    }
}
