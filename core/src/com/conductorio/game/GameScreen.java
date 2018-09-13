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

    private DudeField dudeField;
    private FieldBackground textField;

    private ValueCounter moneyCounter;
    private ValueCounter legalCounter;
    private ValueCounter satisfactionCounter;
    private ValueCounter influenceCounter;

    private TextBox leftBox;
    private TextBox rightBox;

    private Card card;
    private Side pickedSide;

    private int getTextfieldWriteHeight() {
        return Constants.FLOOR_TO_DUDE + Constants.DUDE_BOX_SIZE + Constants.DUDE_TO_TEXT + Constants.TEXTFIELD_HEIGHT - Constants.BORDER;
    }

    private int getStatsWriteHeight() {
        return Constants.FLOOR_TO_DUDE + Constants.DUDE_BOX_SIZE + Constants.DUDE_TO_TEXT + Constants.TEXTFIELD_HEIGHT + Constants.TEXT_TO_STATS;
    }

    private int getWriteStartX() {
        return (Constants.SCREEN_WIDTH - Constants.DUDE_BOX_SIZE) / 2 + Constants.BORDER;
    }

    private int getStatsStartX(int borderNumbers) {
        return (Constants.SCREEN_WIDTH - Constants.DUDE_BOX_SIZE) / 2 + Constants.STATS_BORDER * borderNumbers;
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
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        if (game.tutorial) {
            Player.getInstance().setTutorial();
        }

        card = Player.getInstance().getCard();

        dudeField = new DudeField(Constants.SCREEN_WIDTH / 2 - Constants.DUDE_BOX_SIZE / 2, Constants.FLOOR_TO_DUDE, card.getCharacter());
        textField = new FieldBackground(Constants.SCREEN_WIDTH / 2 - Constants.DUDE_BOX_SIZE / 2, Constants.FLOOR_TO_DUDE + Constants.DUDE_BOX_SIZE + Constants.DUDE_TO_TEXT, new Texture(Gdx.files.internal("textfield.jpg")));

        moneyCounter = new ValueCounter(getStatsStartX(1), getStatsWriteHeight(), new Texture(Gdx.files.internal("statsTextures/money.png")), new Texture(Gdx.files.internal("statsTextures/moneyWhite.png")));
        legalCounter = new ValueCounter(getStatsStartX(5), getStatsWriteHeight(), new Texture(Gdx.files.internal("statsTextures/legal.png")), new Texture(Gdx.files.internal("statsTextures/legalWhite.png")));
        satisfactionCounter = new ValueCounter(getStatsStartX(9), getStatsWriteHeight(), new Texture(Gdx.files.internal("statsTextures/satisfaction.png")), new Texture(Gdx.files.internal("statsTextures/satisfactionWhite.png")));
        influenceCounter = new ValueCounter(getStatsStartX(13), getStatsWriteHeight(), new Texture(Gdx.files.internal("statsTextures/influence.png")), new Texture(Gdx.files.internal("statsTextures/influenceWhite.png")));
        leftBox = new TextBox(Constants.BORDER, getTextfieldWriteHeight() - 64, card.getLeft().getText());
        rightBox = new TextBox(Constants.SCREEN_WIDTH - Constants.BORDER - Constants.CHOICE_BOX_SIZE, getTextfieldWriteHeight() - 64, card.getRight().getText());

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
        game.batch.draw(dudeField.getFace(), dudeField.getX(), dudeField.getY(), Constants.DUDE_BOX_SIZE, Constants.DUDE_BOX_SIZE);
        game.batch.draw(textField.getTexture(), textField.getX(), textField.getY());
        game.batch.end();
    }

    private void renderChoiceBackgroundBoxes() {
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(Color.DARK_GRAY);
        if (pickedSide == Side.LEFT) {
            game.shapeRenderer.rect(leftBox.getX(), leftBox.getY() - Constants.CHOICE_BOX_SIZE, Constants.CHOICE_BOX_SIZE, Constants.CHOICE_BOX_SIZE);
        } else if (pickedSide == Side.RIGHT) {
            game.shapeRenderer.rect(rightBox.getX(), rightBox.getY() - Constants.CHOICE_BOX_SIZE, Constants.CHOICE_BOX_SIZE, Constants.CHOICE_BOX_SIZE);
        }
        game.shapeRenderer.end();
    }

    private void drawValueCounter(ValueCounter valueCounter) {
        game.batch.draw(valueCounter.getTextureWhite(), valueCounter.getX(), valueCounter.getY(), Constants.VALUE_COUNTER_SIZE, Constants.VALUE_COUNTER_SIZE);
        game.batch.draw(valueCounter.getTexture(), valueCounter.getX(), valueCounter.getY() + valueCounter.getValueInPx(), 0, 0, Constants.VALUE_COUNTER_SIZE, Constants.VALUE_COUNTER_SIZE - valueCounter.getValueInPx());
    }

    private void drawStatsAndStuff() {
        game.font.draw(game.batch, card.getText(), getWriteStartX(), getTextfieldWriteHeight(), Constants.DUDE_BOX_SIZE - Constants.BORDER * 2, 10, true);
        drawValueCounter(moneyCounter);
        drawValueCounter(legalCounter);
        drawValueCounter(satisfactionCounter);
        drawValueCounter(influenceCounter);
    }

    private void drawChoiceText() {
        if (pickedSide == Side.LEFT) {
            game.font.draw(game.batch, leftBox.getText(), leftBox.getX() + Constants.FONT_BORDER, leftBox.getY() - Constants.FONT_BORDER, Constants.CHOICE_BOX_SIZE, 10, true);
        } else if (pickedSide == Side.RIGHT) {
            game.font.draw(game.batch, rightBox.getText(), rightBox.getX() + Constants.FONT_BORDER, rightBox.getY() - Constants.FONT_BORDER, Constants.CHOICE_BOX_SIZE, 10, true);
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
            if (touchPos.x < Constants.SCREEN_WIDTH / 2) {
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