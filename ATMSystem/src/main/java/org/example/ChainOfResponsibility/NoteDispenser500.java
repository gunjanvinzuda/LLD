package org.example.ChainOfResponsibility;

public class NoteDispenser500 extends NoteDispenser{
    public NoteDispenser500(DispenserChain nextDispenser, int billCounts){
        super(nextDispenser, billCounts, 500);
    }
}
