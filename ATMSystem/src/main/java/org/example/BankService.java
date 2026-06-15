package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Map<String, Card> cards;
    private final Map<Card, Double> balances;

    public BankService(){
        cards = new ConcurrentHashMap<>();
        balances = new ConcurrentHashMap<>();
    }

    public void addCard(Card card, double balance){
        cards.put(card.getNumber(), card);
        balances.put(card, balance);
    }

    public Card getCard(String cardNumber){
        return cards.get(cardNumber);
    }

    public double getBalance(Card card){
        return balances.get(card);
    }

    public void deductAmount(Card card, int amount){
        balances.put(card, balances.get(card) - amount);
    }
}
