package com.conductorio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.conductorio.game.Logic.Card;
import com.conductorio.game.Logic.Player;
import com.conductorio.game.Logic.Side;
import com.conductorio.game.Techstuff.SimpleDirectionGestureDetector;
import com.conductorio.game.Widgets.DudeField;
import com.conductorio.game.Widgets.FieldBackground;
import com.conductorio.game.Widgets.TextBox;
import com.conductorio.game.Widgets.ValueCounter;

public class GameScreen implements Screen {
    private final Conductorio game;
    private OrthographicCamera camera;

    private int HEIGHT_BORDER, HEIGHT_FIELD, HEIGHT_DUDE;
    private int WIDTH_BORDER, WIDTH_DRAW_FIELD, WIDTH_VALUE_COUNTER;
    private int CHOICE_BOX_SIZE;

    private DudeField dudeField;

    private ValueCounter moneyCounter;
    private ValueCounter legalCounter;
    private ValueCounter satisfactionCounter;
    private ValueCounter influenceCounter;

    private TextBox leftBox;
    private TextBox rightBox;

    private Card card;
    private Side pickedSide;

    private int getValueCounterWidth(int counterNumber) {
        return WIDTH_BORDER + (counterNumber - 1) * WIDTH_VALUE_COUNTER;
    }

    private int getTextfieldWriteHeight() {
        return HEIGHT_BORDER * 2 + HEIGHT_DUDE + HEIGHT_FIELD;
    }

    private int getChoiceBoxWriteHeight() {
        return getTextfieldWriteHeight() - HEIGHT_FIELD * 2 - HEIGHT_BORDER;
    }

    private int getStatsWriteHeight() {
        return HEIGHT_BORDER * 3 + HEIGHT_DUDE + HEIGHT_FIELD;
    }

    private void drawNewCard() {
        Player player = Player.getInstance();
        moneyCounter.setValue(player.getMoney());
        legalCounter.setValue(player.getLegal());
        satisfactionCounter.setValue(player.getSatisfaction());
        influenceCounter.setValue(player.getInfluence());

        card = Player.getInstance().getCard();
        dudeField.setCharacter(card.getCharacter());
        leftBox.setText(card.getLeft().getText());
        rightBox.setText(card.getRight().getText());
    }

    private void leaveGame() {
        Player.reset();
        game.setScreen(new MainMenuScreen(game));
    }

    GameScreen(final Conductorio game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.getWidth(), game.getHeight());

        WIDTH_BORDER = game.getWidth() / 12;
        WIDTH_DRAW_FIELD = WIDTH_BORDER * 10;
        WIDTH_VALUE_COUNTER = WIDTH_DRAW_FIELD / 4;

        HEIGHT_BORDER = game.getHeight() / 16;
        HEIGHT_FIELD = game.getHeight() / 8;
        HEIGHT_DUDE = game.getHeight() / 2;

        CHOICE_BOX_SIZE = HEIGHT_FIELD + HEIGHT_BORDER;

        if (game.tutorial) {
            Player.getInstance().setTutorial();
        }

        card = Player.getInstance().getCard();

        dudeField = new DudeField(WIDTH_BORDER, HEIGHT_BORDER, card.getCharacter());

        moneyCounter = new ValueCounter(getValueCounterWidth(1), getStatsWriteHeight(), "statsTextures/money.png","statsTextures/moneyWhite.png", WIDTH_VALUE_COUNTER);
        legalCounter = new ValueCounter(getValueCounterWidth(2), getStatsWriteHeight(), "statsTextures/legal.png", "statsTextures/legalWhite.png", WIDTH_VALUE_COUNTER);
        satisfactionCounter = new ValueCounter(getValueCounterWidth(3), getStatsWriteHeight(),"statsTextures/satisfaction.png", "statsTextures/satisfactionWhite.png", WIDTH_VALUE_COUNTER);
        influenceCounter = new ValueCounter(getValueCounterWidth(4), getStatsWriteHeight(),"statsTextures/influence.png", "statsTextures/influenceWhite.png", WIDTH_VALUE_COUNTER);
        leftBox = new TextBox(WIDTH_BORDER / 2, getChoiceBoxWriteHeight(), card.getLeft().getText());
        rightBox = new TextBox(game.getWidth() - WIDTH_BORDER / 2 - CHOICE_BOX_SIZE, getChoiceBoxWriteHeight(), card.getRight().getText());

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onRight() {
                if(card.getRight().pick()) {
                    drawNewCard();
                } else {
                    leaveGame();
                }
            }

            @Override
            public void onLeft() {
                if(card.getLeft().pick()) {
                    drawNewCard();
                } else {
                    leaveGame();
                }
            }
        }));
    }

    private void renderBackgroundBoxes() {
        game.batch.begin();
        game.batch.draw(dudeField.getFace(), dudeField.getX(), dudeField.getY(), HEIGHT_DUDE, HEIGHT_DUDE);
        game.batch.end();
    }

    private void renderChoiceBackgroundBoxes() {
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(Color.DARK_GRAY);
        if (pickedSide == Side.LEFT) {
            game.shapeRenderer.rect(leftBox.getX(), leftBox.getY(), CHOICE_BOX_SIZE, CHOICE_BOX_SIZE);
        } else if (pickedSide == Side.RIGHT) {
            game.shapeRenderer.rect(rightBox.getX(), rightBox.getY(), CHOICE_BOX_SIZE, CHOICE_BOX_SIZE);
        }
        game.shapeRenderer.end();
    }

    private void drawValueCounter(ValueCounter valueCounter) {
        game.batch.draw(valueCounter.getTextureWhite(), valueCounter.getX(), valueCounter.getY());
        game.batch.draw(valueCounter.getTexture(), valueCounter.getX(), valueCounter.getY() + valueCounter.getValueInPx(), 0, 0, WIDTH_VALUE_COUNTER, WIDTH_VALUE_COUNTER - valueCounter.getValueInPx());
    }

    private void drawStatsAndStuff() {
        game.font.draw(game.batch, card.getText(), WIDTH_BORDER, getTextfieldWriteHeight(), HEIGHT_DUDE, 10, true);
        drawValueCounter(moneyCounter);
        drawValueCounter(legalCounter);
        drawValueCounter(satisfactionCounter);
        drawValueCounter(influenceCounter);
    }

    private void drawChoiceText() {
        if (pickedSide == Side.LEFT) {
            game.font.draw(game.batch, leftBox.getText(), leftBox.getX(), leftBox.getY() + CHOICE_BOX_SIZE, CHOICE_BOX_SIZE, 10, true);
        } else if (pickedSide == Side.RIGHT) {
            game.font.draw(game.batch, rightBox.getText(), rightBox.getX(), rightBox.getY() + CHOICE_BOX_SIZE, CHOICE_BOX_SIZE, 10, true);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        renderBackgroundBoxes();
        renderChoiceBackgroundBoxes();

        game.batch.begin();
        drawStatsAndStuff();
        drawChoiceText();
        game.batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (touchPos.x < game.getWidth() / 2) {
                pickedSide = Side.LEFT;
            } else {
                pickedSide = Side.RIGHT;
            }
        } else {
            pickedSide = Side.NONE;
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}