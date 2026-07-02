package org.example.ChainOfResponsibility;

public class NoteDispenser100 extends NoteDispenser{
    public NoteDispenser100(DispenserChain nextDispenser, int billCounts){
        super(nextDispenser, billCounts, 100);
    }
}
