package no.finn.model;

import no.finn.services.GameService;

import java.util.List;

public class Player {

    private int score;
    private List<Card> cardList;

    public Player(List<Card> cardList) {
        this.cardList = cardList;
        this.score = playerScore(cardList);
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public void addToCardList (Card card) {
        this.cardList.add(card);
    }

    public List<Card> getPlayerHand() {
        return this.cardList;
    }

    private int playerScore(List<Card> cardList) {
        GameService gameService = new GameService();
        return gameService.getValueOfHand(cardList);
    }
}
