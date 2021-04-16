package no.finn.model;

import java.util.Arrays;
import java.util.List;

public class Card {

    private String cardValue;
    private String type;

    public Card (String type,String cardValue) {
        this.type = type;
        this.cardValue = cardValue;
    }

    public String getCardValue() {
        return cardValue;
    }

    public String getType() {
        return type;
    }

    public static List<String> getValidCardValue() {
        return Arrays.asList("2","3","4","5","6","7","8","9","10","J","Q","K","A");
    }

    public static List<String> getValidCardSuites() {
        return Arrays.asList("C","S","H","D");
    }

}
