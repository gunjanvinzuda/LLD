package org.example;

import java.util.HashMap;
import java.util.Map;

public class ChangeVault {
    private Map<Denomination, Integer> mainVault = new HashMap<>();
    private Map<Denomination, Integer> transactionalBuffer = new HashMap<>();
    private ChangeHandler chainHead;

    public ChangeVault(){
        for(Denomination d: Denomination.values()){
            mainVault.put(d, 0);
            transactionalBuffer.put(d, 0);
        }
        buildChain();
    }

    private void buildChain(){
        ChangeHandler h5 = new ChangeHandler(Denomination.FIVE, null);
        ChangeHandler h10 = new ChangeHandler(Denomination.TEN, h5);
        ChangeHandler h20 = new ChangeHandler(Denomination.TWENTY, h10);
        ChangeHandler h50 = new ChangeHandler(Denomination.FIFTY, h20);

        chainHead = h50;
    }

    public void addToVault(Denomination d, int count){
        mainVault.put(d, mainVault.get(d)+count);
    }

    public void insertMoney(Denomination d, int count){
        transactionalBuffer.put(d, transactionalBuffer.get(d)+count);
    }

    public Double getInsertedTotal(){
        double total = 0;

        for(Map.Entry<Denomination, Integer> entry: transactionalBuffer.entrySet()){
            total += entry.getKey().getValue() * entry.getValue();
        }

        return total;
    }

    public boolean processChange(double price){
        double total = getInsertedTotal();

        if(total < price){
            throw new IllegalStateException("ChangeVault: processChange(): Sufficient amount not received.");
        }

        double changeToProcess = total - price;
        Map<Denomination, Integer> tempPool = new HashMap<>(mainVault);

        transactionalBuffer.forEach((d, count)->{
            tempPool.put(d, tempPool.get(d)+count);
        });

        Map<Denomination, Integer> processedChange = new HashMap<>();
        boolean canProcessChange = chainHead.processChange(changeToProcess, tempPool, processedChange);

        if(canProcessChange){
            mainVault = tempPool;
            transactionalBuffer.replaceAll((d, v)->0);
            System.out.println("ChangeVault: processChange(): Transaction successful. Returning change.");
            processedChange.forEach((d, count)->{
                System.out.println(d.getValue() + " x " + count);
            });
            return true;
        }
        return false;
    }

    public void rollBackTransaction(){
        System.out.println("ChangeVault: processChange(): Rolling back transaction. Refunding money.");
        transactionalBuffer.forEach((d, count)->{
            if(count > 0) System.out.println(d.getValue() + " x " + count);
        });
        transactionalBuffer.replaceAll((d, v)->0);
    }
}
