package com.conductorio.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
    private final Conductorio game;
    private OrthographicCamera camera;

    MainMenuScreen(final Conductorio game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 568);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Conductorio", game.getWidth() / 12, game.getHeight() / 6 , game.getWidth() / 2, 10, true);
        game.font.draw(game.batch, "Der Titel ist Müll, tipp einfach.", game.getWidth() / 12, game.getHeight() / 6 , game.getWidth() / 2, 10, true);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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