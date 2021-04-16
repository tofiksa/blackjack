package no.finn.services;

import no.finn.model.Card;

import java.util.Collections;
import java.util.List;

public class CardDeckService {

    private final int TOPP_OF_CARDDECK = 0;


    public CardDeckService() {

    }

    public List<Card> ShuffleCardDeck(List<Card> cardDeck) {
        Collections.shuffle(cardDeck);
        return cardDeck;
    }

    public Card DealCardsFromCardDeck(List<Card> cardDeck) {
        return cardDeck.get(TOPP_OF_CARDDECK);
    }

}
