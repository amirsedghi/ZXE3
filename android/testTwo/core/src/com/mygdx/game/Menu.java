// Abstract class to define features of a menu

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

// Implement screen or maybe not?
abstract class Menu implements Screen{
    // Global variables and objects
    protected final MyGdxGame game;
    // Stage for the buttons
    protected Stage stage;
    // Button stuff
    protected FitViewport viewPort;
    protected TextureAtlas buttonAtlas;
    protected TextButton.TextButtonStyle simpleButtonStyle;
    protected Skin simpleButtonSkin;

    public Menu(final MyGdxGame game){
        this.game = game;
        // Setup viewport, stage, and the input processor
        viewPort = new FitViewport(game.camera.viewportWidth,  game.camera.viewportHeight, game.camera);
        stage = new Stage(viewPort, game.batch);
        Gdx.input.setInputProcessor(stage);

        // Setup the texture for the button
        buttonAtlas = new TextureAtlas("simplebuttons2.atlas");

        // Setup a skin for a simple button
        simpleButtonSkin = new Skin();
        simpleButtonSkin.addRegions(buttonAtlas);

        // Create style and simple font objects
        simpleButtonStyle = new TextButton.TextButtonStyle();
        simpleButtonStyle.font = new BitmapFont();

        // Sets the texture when clicked/unclicked
        simpleButtonStyle.up = simpleButtonSkin.getDrawable("unpressed");
        simpleButtonStyle.down = simpleButtonSkin.getDrawable("pressed");
    }

    @Override
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
    }
    @Override
    public void dispose(){
        stage.dispose();
        simpleButtonSkin.dispose();
    }
}
