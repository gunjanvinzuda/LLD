package org.example;

public enum Denomination {
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50);

    private int value;

    Denomination(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

