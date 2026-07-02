package org.example;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Shelf> shelves = new HashMap<>();

    public Shelf getShelf(String code){
        if(!shelves.containsKey(code))
            throw new IllegalArgumentException("Inventory: isAvailable(): Shelve not found.");
        return shelves.get(code);
    }

    public void addShelve(Shelf shelf){
        shelves.put(shelf.getCode(), shelf);
    }

    public boolean isAvailable(String code){
        if(!shelves.containsKey(code))
            throw new IllegalArgumentException("Inventory: isAvailable(): Wrong code entered.");
        return shelves.get(code).getQuantity() > 0;
    }
}
