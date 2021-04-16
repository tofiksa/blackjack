package no.finn;

import no.finn.model.CardDeck;
import no.finn.services.GameService;

public class Main {

    public static void main(String[] args) {

        CardDeck cardDeck;

        if (args.length == 0)
            cardDeck = new CardDeck();
        else
            cardDeck = new CardDeck(args);

        GameService gameService = new GameService();
        gameService.startGame(cardDeck);

    }

}
