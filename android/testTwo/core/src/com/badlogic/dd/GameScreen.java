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
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.*;

/**
 * Created by evolerup on 3/7/16.
 */
public class GameScreen implements Screen {
    final DD game;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    public Vector3 mousePos;

    // instantiate a cannon
    Cannon cannon = new Cannon();

    public GameScreen(final DD gam){
        this.game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();




    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // get the coordinates of mouse position
        mousePos = new Vector3(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
        // pass the vector to the cannon instant
        cannon.setVector(mousePos);



        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(cannon.getTextureRegion(), 320, 10, 60, 54, 120, 108, 1,1, -cannon.getAngle());
        batch.end();





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
