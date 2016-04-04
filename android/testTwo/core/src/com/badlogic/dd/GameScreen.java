package com.badlogic.dd;

import java.beans.VetoableChangeListener;
import java.util.ArrayList;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by evolerup on 3/7/16.
 */
public class GameScreen implements Screen{
    final DD game;

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public Vector3 mousePos;

    private ArrayList<Cannonballs> cannonball;

    // instantiate a cannon
    Cannon cannon = new Cannon(cannonball);
    //Create a stage in scene2d
    Stage gameScreen;

    public GameScreen(final DD gam){
        this.game = gam;
        //stage GameScreen
        //gameScreen = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(gameScreen);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        //Viewport for gameScreen Stage using camera
        FitViewport viewp = new FitViewport(800,480,camera);
        batch = new SpriteBatch();

        //stage GameScreen
        gameScreen = new Stage(viewp, batch);
        //add cannon as an actor into gameScreen stage
        gameScreen.addActor(cannon);
        gameScreen.setKeyboardFocus(cannon);



    }


    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Scene 2d stuff
        gameScreen.act(Gdx.graphics.getDeltaTime());
        gameScreen.draw();

        // get the coordinates of mouse position
        mousePos = new Vector3(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
        // pass the vector to the cannon instant
        cannon.setVector(mousePos);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        //Drawing to screen with texture region
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
