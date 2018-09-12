package com.conductorio.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

public class CardStash {
    private ArrayList<Card> basicCards;

    private Character tom;
    private Character jeff;
    private Character marla;

    private Random rand;

    CardStash() {
        basicCards = new ArrayList<Card>();
        Texture tomTexture = new Texture(Gdx.files.internal("dude1.jpg"));
        Texture jeffTexture = new Texture(Gdx.files.internal("dude2.jpg"));
        Texture marlaTexture = new Texture(Gdx.files.internal("dude3.jpg"));

        tom = new Character("Tom", tomTexture);
        jeff = new Character("Jeff", jeffTexture);
        marla = new Character("Marla", marlaTexture);

        basicCards.add(createBasicCard());

        rand  = new Random();
    }

    private Card createBasicCard() {
        Choice choiceLeft = new Choice("Links", 0,0,0,0);
        Choice choiceRight = new Choice("Rechts", 0,0,0,0);

        return new Card(tom,"Karte", choiceLeft, choiceRight);
    }

    public Card drawCard() {
        return basicCards.get(rand.nextInt(basicCards.size()));
    }

    public Card getLoserCard(Category category) {
        LoserChoice lc = new LoserChoice();
        switch (category) {
            case MONEY:
                return new Card(tom, "Kein Geld mehr", lc, lc);
            case LEGAL:
                return new Card(tom, "Kein Legal mehr", lc, lc);
            case SATISFACTION:
                return new Card(tom, "Kein Satisfaction mehr", lc, lc);
            case INFLUENCE:
                return new Card(tom, "Kein Influence mehr", lc, lc);
            default:
                return null;
        }
    }
}
