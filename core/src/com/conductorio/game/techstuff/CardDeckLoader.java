package com.conductorio.game.techstuff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.conductorio.game.logic.Card;
import com.conductorio.game.logic.Character;
import com.conductorio.game.logic.Choice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class CardDeckLoader {
    private XmlReader xmlReader;
    private HashMap<String, Character> characters;
    private ArrayList<Card> cards;

    private Iterator getIteratorFromFile(String filename, String elementName) {
        FileHandle fileHandle = Gdx.files.internal("xml/" + filename);
        XmlReader.Element element = xmlReader.parse(fileHandle);

        return element.getChildrenByName(elementName).iterator();
    }

    private Character getCharacterFromXml(XmlReader.Element node) {
        String name = node.getText();
        String file = node.getAttribute("file");

        return new Character(name, new Texture(Gdx.files.internal("characterTextures/" + file)));
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

        Iterator characterIterator = getIteratorFromFile("characters.xml", "character");
        while(characterIterator.hasNext()) {
            XmlReader.Element characterElement = (XmlReader.Element)characterIterator.next();
            Character charTemp = getCharacterFromXml(characterElement);
            characters.put(charTemp.getName(), charTemp);
        }

        Iterator cardIterator = getIteratorFromFile("cards.xml", "card");
        while(cardIterator.hasNext()){
            XmlReader.Element cardElement = (XmlReader.Element)cardIterator.next();
            cards.add(getCardFromXlm(cardElement));
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Card> getTutorial() {
        ArrayList<Card> tutorial = new ArrayList<Card>();

        Iterator cardIterator = getIteratorFromFile("tutorial.xml", "card");
        while(cardIterator.hasNext()){
            XmlReader.Element cardElement = (XmlReader.Element)cardIterator.next();
            tutorial.add(getCardFromXlm(cardElement));
        }

        return tutorial;
    }
}
