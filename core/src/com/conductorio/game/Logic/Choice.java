package com.conductorio.game.Logic;

public class Choice {
    private String text;
    private int money;
    private int legal;
    private int satisfaction;
    private int influence;

    public Choice(String text, int money, int legal, int satisfaction, int influence) {
        this.text = text;
        this.money = money;
        this.legal = legal;
        this.satisfaction = satisfaction;
        this.influence = influence;
    }

    public boolean pick() {
        Player.getInstance().addMoney(money);
        Player.getInstance().addLegal(legal);
        Player.getInstance().addSatisfaction(satisfaction);
        Player.getInstance().addInfluence(influence);

        return true;
    }

    public String getText() {
        return text;
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
}
