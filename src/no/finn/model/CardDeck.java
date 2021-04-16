package no.finn.model;

import no.finn.services.CardDeckService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CardDeck {

    private List<Card> deck;
    private CardDeckService cardDeckService;

    public CardDeck () {
        cardDeckService = new CardDeckService();
        this.deck = new ArrayList<>();

        List<String> kort = Card.getValidCardSuites();
        List<String> verdi = Card.getValidCardValue();

        for (String kortene:kort) {
            for (String verdiene : verdi) {
                deck.add(new Card(kortene,verdiene));
            }
        }

        deck = cardDeckService.ShuffleCardDeck(getDeck());
    }

    public CardDeck (String[] argument) {
        this.deck = new ArrayList<>();

        if (argument.length > 1)
            throw new IllegalArgumentException("Too many arguments! Only one argument is allowed!");

        String fileName= argument[0];
        File file= new File(fileName);
        List<String> cards = new ArrayList<>();

        try{
            Scanner inputStream= new Scanner(file);
            while(inputStream.hasNext()){
                String data= inputStream.next();
                cards = Arrays.asList(data.split(","));
            }
            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (cards.size() < 52)
            throw new IllegalArgumentException("The deck of cards must contain 52 cards!!!");

        for (String kortene:cards)
            deck.add(new Card(kortene.substring(0,1),kortene.substring(1)));
    }

    public List<Card> getDeck() {
        return deck;
    }
}
