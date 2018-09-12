package com.conductorio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class GameScreen implements Screen {
    final Conductorio game;
    private OrthographicCamera camera;

    private Texture dude1;
    private Texture textfieldTexture;
    private Texture statsTexture;

    private Rectangle currentDude;
    private Rectangle textField;
    private Rectangle stats;

    private TextBox leftBox;
    private TextBox rightBox;

    private Card card;
    private Side pickedSide;

    private int getTextfieldWriteHeight() {
        return Constants.FLOOR_TO_DUDE + Constants.DUDE_BOX_SIZE + Constants.DUDE_TO_TEXT + Constants.TEXTFIELD_HEIGHT - Constants.BORDER;
    }

    private int getStatsWriteHeight() {
        return Constants.FLOOR_TO_DUDE + Constants.DUDE_BOX_SIZE + Constants.DUDE_TO_TEXT + Constants.TEXTFIELD_HEIGHT + Constants.TEXT_TO_STATS + Constants.STATS_HEIGHT - Constants.STATS_BORDER;
    }

    private int getWriteStartX() {
        return (Constants.SCREEN_WIDTH - Constants.DUDE_BOX_SIZE) / 2 + Constants.BORDER;
    }

    GameScreen(final Conductorio game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        dude1 = new Texture(Gdx.files.internal("dude2.jpg"));
        textfieldTexture = new Texture(Gdx.files.internal("textfield.jpg"));
        statsTexture = new Texture(Gdx.files.internal("stats.jpg"));

        currentDude = new Rectangle();
        currentDude.x = Constants.SCREEN_WIDTH / 2 - Constants.DUDE_BOX_SIZE / 2;
        currentDude.y = Constants.FLOOR_TO_DUDE;

        textField = new Rectangle();
        textField.x = Constants.SCREEN_WIDTH / 2 - Constants.DUDE_BOX_SIZE / 2;
        textField.y = Constants.FLOOR_TO_DUDE + Constants.DUDE_BOX_SIZE + Constants.DUDE_TO_TEXT;

        stats = new Rectangle();
        stats.x = Constants.SCREEN_WIDTH / 2 - Constants.DUDE_BOX_SIZE / 2;
        stats.y = Constants.FLOOR_TO_DUDE + Constants.DUDE_BOX_SIZE + Constants.DUDE_TO_TEXT + Constants.TEXTFIELD_HEIGHT + Constants.TEXT_TO_STATS;

        leftBox = new TextBox(Constants.BORDER, getTextfieldWriteHeight() - 64, "Die Aktion durchführen");
        rightBox = new TextBox(Constants.SCREEN_WIDTH - Constants.BORDER - Constants.CHOICE_BOX_SIZE, getTextfieldWriteHeight() - 64, "Die Aktion durchführen");

        card = new Card("Do stuff", new Choice("DO", 10, 0, 0, 0), new Choice("STUFF", 0, 0, 0, 10));
        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {
            @Override
            public void onRight() {
                card.getRight().pick();
                card = Player.getInstance().getCard();
            }

            @Override
            public void onLeft() {
                card.getLeft().pick();
                card = Player.getInstance().getCard();
            }
        }));
    }

    private void renderBackgroundBoxes() {
        game.batch.begin();
        game.batch.draw(dude1, currentDude.x, currentDude.y);
        game.batch.draw(textfieldTexture, textField.x, textField.y);
        game.batch.draw(statsTexture, stats.x, stats.y);
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

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        renderBackgroundBoxes();
        renderChoiceBackgroundBoxes();

        game.batch.begin();
        game.font.draw(game.batch, card.getText(), getWriteStartX(), getTextfieldWriteHeight());
        game.font.draw(game.batch, Integer.toString(Player.getInstance().getMoney()), getWriteStartX(), getStatsWriteHeight());
        game.font.draw(game.batch, Integer.toString(Player.getInstance().getLegal()), getWriteStartX() + Constants.STATS_ROOM, getStatsWriteHeight());
        game.font.draw(game.batch, Integer.toString(Player.getInstance().getSatisfaction()), getWriteStartX()+ Constants.STATS_ROOM * 2, getStatsWriteHeight());
        game.font.draw(game.batch, Integer.toString(Player.getInstance().getInfluence()), getWriteStartX() + Constants.STATS_ROOM * 3, getStatsWriteHeight());

        if (pickedSide == Side.LEFT) {
            game.font.draw(game.batch, leftBox.getText(), leftBox.getX() + Constants.FONT_BORDER, leftBox.getY() - Constants.FONT_BORDER, Constants.CHOICE_BOX_SIZE, 10, true);
        } else if (pickedSide == Side.RIGHT) {
            game.font.draw(game.batch, rightBox.getText(), rightBox.getX() + Constants.FONT_BORDER, rightBox.getY() - Constants.FONT_BORDER, Constants.CHOICE_BOX_SIZE, 10, true);
        }

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