package com.badlogic.dd;

import java.beans.VetoableChangeListener;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

    Texture cannonImage;
    OrthographicCamera camera;
    Rectangle cannon;
    Polygon cc;



    public GameScreen(final DD gam){
        this.game = gam;

        // initializing variables
        cannonImage = new Texture(Gdx.files.internal("cannonImage.png"));

        // creating the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // create a rectangle to represent the cannon
        cannon = new Rectangle();
        cannon.x = 800/2 - 120/2;
        cannon.y = 20;

        cannon.width = 120;
        cannon.height = 108;
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices
        camera.update();

        // tell the sprite batch to update in the
        // coordinate system specified by the camera
        game.batch.setProjectionMatrix(camera.combined);

        // begin a new batch and draw the cannon
        game.batch.begin();
        game.batch.draw(cannonImage, cannon.x, cannon.y);
        game.batch.end();


        // rotate the cannon based on the location of the mouse
        Vector2 mousePointer = new Vector2();








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
        cannonImage.dispose();
    }
}
