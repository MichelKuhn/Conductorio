package com.conductorio.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.conductorio.game.techstuff.CardDeckLoader;

import java.util.ArrayList;
import java.util.Random;

public class CardStash {
    private Random rand;
    private GameMode gameMode;

    private CardDeckLoader cardDeckLoader;
    private ArrayList<Card> basicCards;
    private ArrayList<Card> tutorialCards;

    CardStash() {
        rand  = new Random();

        cardDeckLoader = new CardDeckLoader();
        basicCards = cardDeckLoader.getCards();
        tutorialCards = cardDeckLoader.getTutorial();
    }

    private Card drawBasicCard() {
        int cardNumber = rand.nextInt(basicCards.size());
        Card returnCard =  basicCards.get(cardNumber);
        basicCards.remove(cardNumber);

        if (basicCards.isEmpty()) {
            basicCards = cardDeckLoader.getCards();
        }

        return returnCard;
    }

    private Card drawTutorialCard() {
        if (tutorialCards.isEmpty()) {
            gameMode = GameMode.BASIC;
            return drawBasicCard();
        }

        Card returnCard =  tutorialCards.get(0);
        tutorialCards.remove(0);

        return returnCard;
    }

    public Card drawCard() {
        switch (gameMode) {
            case TUTORIAL:
                return drawTutorialCard();

            default:
                return drawBasicCard();
        }
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

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}
