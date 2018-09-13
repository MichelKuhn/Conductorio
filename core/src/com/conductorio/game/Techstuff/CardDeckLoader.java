package com.conductorio.game.Techstuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.conductorio.game.Logic.Card;
import com.conductorio.game.Logic.Character;
import com.conductorio.game.Logic.Choice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class CardDeckLoader {
    private XmlReader xmlReader;
    private HashMap<String, Character> characters;
    private ArrayList<Card> cards;

    private Iterator getIteratorFromFile(String thing) {
        FileHandle fileHandle = Gdx.files.internal("xml/" + thing + ".xml");
        XmlReader.Element element = xmlReader.parse(fileHandle);

        return element.getChildrenByName(thing).iterator();
    }

    private Character getCharacterFromXml(XmlReader.Element node) {
        String name = node.getText();
        String file = node.getAttribute("file");

        return new Character(name, new Texture(Gdx.files.internal(file)));
    }

    private Choice getChoiceFromXml(XmlReader.Element node) {
        String text = node.getAttribute("text");
        int money = Integer.parseInt(node.getChildByName("money").getText());
        int legal = Integer.parseInt(node.getChildByName("legal").getText());
        int satisfaction = Integer.parseInt(node.getChildByName("satisfaction").getText());
        int influence = Integer.parseInt(node.getChildByName("influence").getText());

        return new Choice(text, money, legal, satisfaction, influence);
    }

    private Card getCardFromXlm(XmlReader.Element node) {
        String characterName = node.getAttribute("character");
        String text = node.getAttribute("text");
        Choice choiceLeft = getChoiceFromXml(node.getChildByName("choiceLeft"));
        Choice choiceRight = getChoiceFromXml(node.getChildByName("choiceRight"));

        return new Card(characters.get(characterName), text, choiceLeft, choiceRight);
    }

    public CardDeckLoader() {
        xmlReader = new XmlReader();
        characters = new HashMap<String, Character>();
        cards = new ArrayList<Card>();

        Iterator characterIterator = getIteratorFromFile("character");
        while(characterIterator.hasNext()) {
            XmlReader.Element characterElement = (XmlReader.Element)characterIterator.next();
            Character charTemp = getCharacterFromXml(characterElement);
            characters.put(charTemp.getName(), charTemp);
        }

        Iterator cardIterator = getIteratorFromFile("card");
        while(cardIterator.hasNext()){
            XmlReader.Element cardElement = (XmlReader.Element)cardIterator.next();
            cards.add(getCardFromXlm(cardElement));
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
