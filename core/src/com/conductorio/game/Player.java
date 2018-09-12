package com.conductorio.game;

public class Player {
    private int money;
    private int legal;
    private int satisfaction;
    private int influence;
    private CardStash cardStash;

    private static Player instance;
    private Player () {
        money = 50;
        legal= 50;
        satisfaction = 50;
        influence = 50;
        cardStash = new CardStash();
    }

    public static Player getInstance () {
        if (Player.instance == null) {
            Player.instance = new Player ();
        }

        return Player.instance;
    }

    public Card getCard() {
        return cardStash.drawCard();
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
