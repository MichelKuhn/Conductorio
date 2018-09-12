package com.conductorio.game.Logic;

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
        if(money <= 0) {
            return (cardStash.getLoserCard(Category.MONEY));
        } else if (legal <= 0) {
            return (cardStash.getLoserCard(Category.LEGAL));
        }else if (satisfaction <= 0) {
            return (cardStash.getLoserCard(Category.SATISFACTION));
        }else if (influence <= 0) {
            return (cardStash.getLoserCard(Category.INFLUENCE));
        } else {
            return cardStash.drawCard();
        }
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
        if(legal > 100) {
            this.legal = 100;
        }
    }

    public void addSatisfaction(int satisfaction) {
        this.satisfaction += satisfaction;
        if(satisfaction > 100) {
            this.satisfaction = 100;
        }
    }

    public void addInfluence(int influence) {
        this.influence += influence;
        if(influence > 100) {
            this.influence = 100;
        }
    }

    public static void reset() {
        instance = new Player();
    }
}
