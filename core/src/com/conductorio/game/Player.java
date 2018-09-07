package com.conductorio.game;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private int money;
    private int legal;
    private int satisfaction;
    private int influence;
    private ArrayList<Card> cards;

    private static Player instance;
    private Player () {
        money = 50;
        legal= 50;
        satisfaction = 50;
        influence = 50;
        cards = new ArrayList<Card>();
        cards.add(new Card("Geld oder Influence", new Choice("Geld", 10, 0,0,0), new Choice("Influence",0,0,0,10)));
        cards.add(new Card("Geld oder Legal", new Choice("Geld",10, 0,0,0), new Choice("Legal",0,10,0,0)));
        cards.add(new Card("Legal oder Satisfaction", new Choice("Legal",0, 10,0,0), new Choice("Satisfaction",0,0,10,0)));
    }

    public static Player getInstance () {
        if (Player.instance == null) {
            Player.instance = new Player ();
        }

        return Player.instance;
    }

    public Card getCard() {
        if(cards.isEmpty()) {
            return new Card("Empty", new Choice("empty",0,0,0,0), new Choice("empty",0,0,0,0));
        }

        Random rand = new Random();
        int randomElement = rand.nextInt(cards.size());
        Card card = cards.get(randomElement);
        cards.remove(randomElement);

        return card;
    }

    public int getMoney() {
        return money;
    }

    public int getLegal() {
        return legal;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public int getInfluence() {
        return influence;
    }

    public void addMoney(int money) {
        this.money += money;
        if(money > 100) {
            this.money = 100;
        }
    }

    public void addLegal(int legal) {
        this.legal += legal;
        if(money > 100) {
            this.legal = 100;
        }
    }

    public void addSatisfaction(int satisfaction) {
        this.satisfaction += satisfaction;
        if(money > 100) {
            this.satisfaction = 100;
        }
    }

    public void addInfluence(int influence) {
        this.influence += influence;
        if(money > 100) {
            this.influence = 100;
        }
    }
}
