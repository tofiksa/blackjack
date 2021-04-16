package no.finn.tests;

import no.finn.model.Card;
import no.finn.services.GameService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TestGameService {


    @Test
    public void testBlackJackCase() {
        Card card1 = new Card("D","A");
        Card card2 = new Card("D","10");

        List<Card> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);

        GameService gameService = new GameService();

        assertEquals(true,gameService.CheckIfHandIsBlackJack(hand));
    }

    @Test
    public void ifHandIsTwentyOneAndOver() {
        Card card1 = new Card("D","A");
        Card card2 = new Card("C","A");

        List<Card> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);

        GameService gameService = new GameService();

        assertEquals(true,gameService.CheckIfHandIsOverTwentyOne(hand));
    }

    @Test
    public void ifBothPlayersGetBlackJackSamWins() {
        Card card1 = new Card("D","A");
        Card card2 = new Card("D","10");

        List<Card> dealerHand = new ArrayList<>();
        List<Card> samHand = new ArrayList<>();
        dealerHand.add(card1);
        dealerHand.add(card2);
        samHand.add(card1);
        samHand.add(card2);


        GameService gameService = new GameService();
        assertEquals("Sam wins the game!!",gameService.getBlackJackWinner(samHand,dealerHand));
    }

    @Test
    public void ifBothPlayersGetTwentyTwoDealerWinns() {
        Card card1 = new Card("D","A");
        Card card2 = new Card("S","A");

        List<Card> dealerHand = new ArrayList<>();
        List<Card> samHand = new ArrayList<>();
        dealerHand.add(card1);
        dealerHand.add(card2);
        samHand.add(card1);
        samHand.add(card2);


        GameService gameService = new GameService();
        assertEquals("Dealer wins the game!!",gameService.getTwentyTwoWinner(samHand,dealerHand));
    }
}
