package com.conductorio.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.conductorio.game.Techstuff.CardDeckLoader;

import java.util.ArrayList;
import java.util.Random;

public class CardStash {
    private Random rand;
    private ArrayList<Card> basicCards;

    CardStash() {
        rand  = new Random();

        CardDeckLoader cardDeckLoader = new CardDeckLoader();
        basicCards = cardDeckLoader.getCards();
    }

    public Card drawCard() {
        int cardNumber = rand.nextInt(basicCards.size());
        Card returnCard =  basicCards.get(cardNumber);
        basicCards.remove(cardNumber);

        return returnCard;
    }

    public Card getLoserCard(Category category) {
        Texture loserTexture = new Texture(Gdx.files.internal("dude1.jpg"));
        Character loserCharacter = new Character("Loser", loserTexture);
        LoserChoice lc = new LoserChoice();
        switch (category) {
            case MONEY:
                return new Card(loserCharacter, "Kein Geld mehr", lc, lc);
            case LEGAL:
                return new Card(loserCharacter, "Kein Legal mehr", lc, lc);
            case SATISFACTION:
                return new Card(loserCharacter, "Kein Satisfaction mehr", lc, lc);
            case INFLUENCE:
                return new Card(loserCharacter, "Kein Influence mehr", lc, lc);
            default:
                return null;
        }
    }
}
