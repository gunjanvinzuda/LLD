package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;

public class BankService {
    private final Map<String, Card> cards;
    private final Map<String, BigDecimal> balances;

    public BankService(){
        cards = new ConcurrentHashMap<>();
        balances = new ConcurrentHashMap<>();
    }

    public void addCard(Card card, long balance){
        cards.put(card.getNumber(), card);
        balances.put(card.getNumber(), BigDecimal.valueOf(balance));
    }

    public Card getCard(String cardNumber){
        return cards.get(cardNumber);
    }

    public BigDecimal getBalance(Card card){
        if(card == null) throw new IllegalArgumentException("BankService: getBalance(): Card is null.");
        BigDecimal balance = balances.get(card.getNumber());
        if(balance == null) throw new IllegalArgumentException("BankService: getBalance(): Card not found.");
        return balance;
    }

    public void deductAmount(Card card, int amount){
        if(card == null) throw new IllegalArgumentException("BankService: deductAmount(): Card is null.");
        BigDecimal balance = balances.get(card.getNumber());
        if(balance == null) throw new IllegalArgumentException("BankService: deductAmount(): Card not found.");
        if(amount <= 0) throw new IllegalArgumentException("BankService: deductAmount(): Amount must be positive.");
        if(balance.compareTo(BigDecimal.valueOf(amount)) < 0) throw new IllegalStateException("BankService: deductAmount(): Insufficient funds.");
        balances.put(card.getNumber(), balance.subtract(BigDecimal.valueOf(amount)));
    }
}
