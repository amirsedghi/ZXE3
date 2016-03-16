package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.InputProcessor;

public class MyGdxGame extends Game implements InputProcessor {

    private SpriteBatch batch;
    private Sprite backgroundSprite;
    private Texture backgroundImg, heliboy;

    // Declare music object
    Music mirage;

    //Screen dimensions
    private float screenWidth, screenHeight;


    @Override
    //Set screen dimensions, font, and use this class for input processing
    public void create() //Called when the Application is first created.
    {
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);

        //Initialize and set input processor
        mirage = Gdx.audio.newMusic(Gdx.files.internal("mirage.mp3"));

        backgroundImg = new Texture("sand.jpg");
        heliboy =  new Texture("heliboy.png");
        backgroundSprite = new Sprite(backgroundImg);

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

    }

//	public void renderBackground(){
//		backgroundSprite.draw(batch);
//	}

    @Override
    public void dispose() { //Called when the Application is first created.
        batch.dispose();
        backgroundImg.dispose();
        mirage.dispose();
        heliboy.dispose();
    }

    @Override
    public void render() { //Called when the Application should render itself.
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Spritebatch drawing must be made between the begin and end methods:
        batch.begin();
        batch.draw(backgroundImg, 0, 0, screenWidth, screenHeight);

        //Play streaming music
        mirage.play();
        mirage.setLooping(true);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        mirage.pause();
    }

    @Override
    public void resume() {
        mirage.play();
    }

    //Return true to indicate that the event was handled
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return true;
    }

    //When finger is lifted up
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    //While dragging finger across screen
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
