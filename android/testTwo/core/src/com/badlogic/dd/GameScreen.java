package com.badlogic.dd;

import java.beans.VetoableChangeListener;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * Created by evolerup on 3/7/16.
 */
public class GameScreen implements Screen {
    final DD game;
    private Wall wall;

    public GameScreen(final DD gam){
        this.game = gam;
        int wallHeight = 55;
        int canonHeight = 30;
        // y-up
        wall = new Wall(100, canonHeight + wallHeight);
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        game.batch.begin();
        wall.render(game.batch);
        game.batch.end();
    }
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                // https://github.com/libgdx/libgdx/wiki/Coordinate-systems
                // Touch is y-down, screen/image is y-up

                // convert to y-up
                int y = Gdx.graphics.getHeight() - Gdx.input.getY();
                int distance = wall.getDistanceToWall(y);
                System.out.println("Distance to wall from: "+ y + " is " + distance);
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.H)){
            // heal wall
            wall.adjustHealth(10);
            System.out.println("Healed --> " + wall.getHealth());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            // do wall damage
            wall.adjustHealth(-10);
            System.out.println("Damaged --> " + wall.getHealth());
        }
    }

    public void resize(int width, int height){

    }

    public void show(){

    }

    public void hide(){

    }

    public void pause(){

    }

    public void resume(){

    }

    public void dispose(){

    }
}
