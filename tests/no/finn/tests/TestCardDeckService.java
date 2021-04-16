package no.finn.tests;

import no.finn.model.CardDeck;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class TestCardDeckService {

    @Test
    public void testIfCardsFromDeckActuallyGetsShuffled() {

        CardDeck cardDeck = new CardDeck();
        CardDeck cardDeck1 = new CardDeck();

        String firstcardfromdeck = cardDeck.getDeck().get(0).getCardValue();
        String firstcardfromdeck2 = cardDeck1.getDeck().get(0).getCardValue();

        assertNotEquals(firstcardfromdeck,firstcardfromdeck2);
    }



}
