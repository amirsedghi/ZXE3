package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;

import java.awt.*;

/**
 * Created by ghost_000 on 3/7/2016.
 */
public class MainMenu implements Screen {
    private final MyGdxGame game;
    private TextButtonStyle startButtonStyle;
    private TextButton startButton;
    private Stage stage;
    private FitViewport viewPort;
    private TextureAtlas buttonAtlas;
    private Skin startButtonSkin;

    public MainMenu(final MyGdxGame game){
        this.game = game;

        viewPort = new FitViewport(800,400,game.camera);
        stage = new Stage(viewPort, game.batch);
        Gdx.input.setInputProcessor(stage);

        buttonAtlas = new TextureAtlas("testPacker.atlas");

        startButtonSkin = new Skin();
        startButtonSkin.addRegions(buttonAtlas);

        startButtonStyle = new TextButtonStyle();
        startButtonStyle.font = new BitmapFont();
        //startButtonStyle.downFontColor = Color.GRAY;
        startButtonStyle.down = startButtonSkin.getDrawable("orange");
        startButtonStyle.up = startButtonSkin.getDrawable("green");
        startButton = new TextButton("Start", startButtonStyle);
        //startButton.setWidth(50);
        //startButton.setHeight(30);
        startButton.setPosition(100, 100);
        startButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new GdxGameScreen2(game));
                dispose();
            }
        });

        stage.addActor(startButton);
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(game.camera.combined);

        stage.act();

        stage.draw();
        game.batch.begin();
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {

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
    public void show(){
        //
    }
    @Override
    public void dispose(){
        stage.dispose();
    }
}
