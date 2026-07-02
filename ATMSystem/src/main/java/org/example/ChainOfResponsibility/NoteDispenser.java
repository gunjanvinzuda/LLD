package org.example.ChainOfResponsibility;

public class NoteDispenser implements DispenserChain{

    private int BILL_VALUE;
    private int billCounts;
    private DispenserChain nextDispenser;

    public NoteDispenser(DispenserChain nextDispenser, int billCounts, int billValue){
        this.nextDispenser = nextDispenser;
        this.billCounts = billCounts;
        this.BILL_VALUE = billValue;
    }

    @Override
    public boolean canDispense(int amount){
        int reqBillCounts = Math.min(amount/BILL_VALUE, billCounts);

        amount = amount - reqBillCounts*BILL_VALUE;

        if(amount == 0) return true;
        if(nextDispenser != null) return nextDispenser.canDispense(amount);
        return false;
    }

    @Override
    public void dispenseNotes(int amount) {
        int reqBillCounts = Math.min(amount/BILL_VALUE, billCounts);

        amount = amount - reqBillCounts*BILL_VALUE;
        billCounts -= reqBillCounts;

        if(amount != 0){
            try{
                if(nextDispenser != null) nextDispenser.dispenseNotes(amount);
                else throw new IllegalStateException();
            }catch (Exception e){
                System.out.println("NoteDispenser: dispenseNotes(): Error while dispensing money.");
                billCounts += reqBillCounts;
                throw new IllegalStateException();
            }
        }
    }
}
