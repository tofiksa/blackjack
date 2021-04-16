package no.finn.services;

import no.finn.model.Card;
import no.finn.model.CardDeck;
import no.finn.model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameService {

    private CardDeckService cardDeckService;
    private Player dealer;
    private Player sam;

    public void startGame (CardDeck cardDeck) {

        String SamsCards = "Sam has: ";
        String DealersCards = "Dealer has: ";

        cardDeckService = new CardDeckService();
        List<Card> shuffledcards = cardDeck.getDeck();

        List<Card> dealerCards = new ArrayList<>();
        List<Card> samCards = new ArrayList<>();

        samCards.add(cardDeckService.DealCardsFromCardDeck(shuffledcards));
        shuffledcards.remove(0);

        dealerCards.add(cardDeckService.DealCardsFromCardDeck(shuffledcards));
        shuffledcards.remove(0);

        samCards.add(cardDeckService.DealCardsFromCardDeck(shuffledcards));
        shuffledcards.remove(0);

        dealerCards.add(cardDeckService.DealCardsFromCardDeck(shuffledcards));
        shuffledcards.remove(0);

        dealer = new Player(dealerCards);
        sam = new Player(samCards);


        for (int i = 0; i < samCards.size(); i++) {
            SamsCards += samCards.get(i).getType() + samCards.get(i).getCardValue() + ", ";
        }

        for (int i = 0; i < dealerCards.size(); i++) {
            DealersCards += dealerCards.get(i).getType() + dealerCards.get(i).getCardValue() + ", ";
        }

        if (getBlackJackWinner(samCards,dealerCards).equals("No blackjack winner!!") || getTwentyTwoWinner(samCards,dealerCards).equals("No TwentyTwoWinner!!")) {

            int counterSam = 1;
            int counterDealer = 1;

            while(sam.getScore() < 17 && sam.getScore() <= 21) {

                sam.addToCardList(cardDeckService.DealCardsFromCardDeck(shuffledcards));
                sam.addToScore(getValueOfCard(cardDeckService.DealCardsFromCardDeck(shuffledcards)));

                shuffledcards.remove(0);
                counterSam++;
                SamsCards += sam.getPlayerHand().get(counterSam).getType() + sam.getPlayerHand().get(counterSam).getCardValue() + ", ";
                if (sam.getScore() > 21)
                    System.out.println("Dealer wins the game!");
            }

            if (sam.getScore() < 21) {
                while (dealer.getScore() <= sam.getScore() && dealer.getScore() <= 21) {
                    dealer.addToCardList(cardDeckService.DealCardsFromCardDeck(shuffledcards));
                    dealer.addToScore(getValueOfCard(cardDeckService.DealCardsFromCardDeck(shuffledcards)));

                    shuffledcards.remove(0);
                    counterDealer++;
                    DealersCards += dealer.getPlayerHand().get(counterDealer).getType() + dealer.getPlayerHand().get(counterDealer).getCardValue() + ", ";
                    if (dealer.getScore() > 21)
                        System.out.println("Sam wins the game!");
                }
            }
        } else if (getBlackJackWinner(samCards,dealerCards).equals("Sam wins the game!!")) {
            System.out.println("BlackJack Sam!!!");
        }


        System.out.println(SamsCards);
        System.out.println(DealersCards);
        System.out.println("*****************************************************");
        System.out.println("The score so far: \n" + "Sam: " + sam.getScore() +
                "\n" + "Dealer: " + dealer.getScore());
        getGameWinner(sam.getScore(),dealer.getScore());
        System.out.println("Number of cards in carddeck: " + shuffledcards.size());
    }

    private void getGameWinner (int scorePlayer1, int scorePlayer2) {

        if (scorePlayer1 > 21)
            System.out.println("Dealer is the winner of the game!");
        else if (scorePlayer2 > 21)
            System.out.println("Sam is the winner of the game!");

        if (scorePlayer1 > scorePlayer2 && scorePlayer1 <= 21) {
            System.out.println("Sam is the winner of the game!!");
        } else if (scorePlayer2 > scorePlayer1 && scorePlayer2 <= 21){
            System.out.println("Dealer is the winner of the game!!");
        }
    }

    public String getTwentyTwoWinner (List<Card> samCards, List<Card> dealerCards) {
        if (CheckIfHandIsOverTwentyOne(samCards)) {
            return("Dealer wins the game!!");
        } else if (CheckIfHandIsOverTwentyOne(dealerCards)) {
            return("Sam wins the game!!");
        } else if (CheckIfHandIsOverTwentyOne(samCards) && CheckIfHandIsOverTwentyOne(dealerCards)) {
            return("Dealer wins the game!!");
        }
        return "No TwentyTwoWinner!!";
    }

    public String getBlackJackWinner (List<Card> samCards, List<Card> dealerCards){

        if (CheckIfHandIsBlackJack(samCards)) {
            return("Sam wins the game!!");
        } else if (CheckIfHandIsBlackJack(dealerCards)) {
            return("Dealer wins the game!!");
        } else if (CheckIfHandIsBlackJack(samCards) && CheckIfHandIsBlackJack(dealerCards)) {
            return("Sam wins the game!!");
        }
        return "No blackjack winner!!";
    }

    public int getValueOfHand (List<Card> hand) {
        int cardvalue = 0;
        for (Card hands:hand) {
            if (isNumeric(hands.getCardValue())) {
                cardvalue += Integer.parseInt(hands.getCardValue());
            } else {
                cardvalue += CheckValueOfImageCard(hands.getCardValue());
            }

        }
        return cardvalue;
    }

    public int getValueOfCard (Card card) {

        int cardvalue = 0;
        if (isNumeric(card.getCardValue())) {
            cardvalue += Integer.parseInt(card.getCardValue());
        } else {
            cardvalue += CheckValueOfImageCard(card.getCardValue());
        }
        return cardvalue;
    }

    public boolean CheckIfHandIsBlackJack (List<Card> hand) {
        List<String> imageCards = Arrays.asList("J","Q","K","A","10");
        int match = 0;

        if (hand.size() == 2) {
            for (int i=0;i<2;i++) {
                for (int j=0;j<imageCards.size();j++) {
                    if (hand.get(i).getCardValue().equals(imageCards.get(j))) {
                        match += 1;
                    }
                }
            }
        }

        if (match == 2) {
            if (hand.get(0).getCardValue().equals("A") || hand.get(1).getCardValue().equals("A")) {
                    return true;
            }
        }
            return false;
    }

    public boolean CheckIfHandIsOverTwentyOne (List<Card> hand) {
        if (getValueOfHand(hand) == 22) {
            return true;
        }
        return false;
    }

    private static boolean isNumeric(String kort) {
        if (kort == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(kort);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private int CheckValueOfImageCard (String kort) {
        if (kort.equals("A"))
            return 11;
        else
            return 10;
    }
}
