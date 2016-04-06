package com.badlogic.dd;

import java.beans.VetoableChangeListener;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.btree.utils.DistributionAdapters;
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

    private Wall wall;
    Enemy enemy;

    Bullets bullet = null;
    //Boolean if mouse is clicked
    boolean touched = true;


    public GameScreen(final DD gam){
        this.game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        wall = new Wall(100, 120);
        // instantiate enemy
        enemy = new Enemy(wall);
    }

    public void render(float delta) {
        // Do damage to wall, or calculate wall distance
        //wall.getDistanceToWall(positionOfEnemy); // how far enemy is to wall to stop enemy from walking through wall
        //wall.adjustHealth(-10); // Some damage done
        //wall.getHealth(); // For health bar
                

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // get the coordinates of mouse position
        mousePos = new Vector3(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
        // pass the vector to the cannon instant
        cannon.setVector(mousePos);


        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Cannon sprite properties
        Sprite cannonSprite = cannon.getSprite();
        cannonSprite.setOrigin(cannonSprite.getWidth()/2,10 + cannonSprite.getHeight()/2);
        cannonSprite.setRotation(-cannon.getAngle());
        cannonSprite.setX(340);
        cannonSprite.setY(10);
        batch.begin();

        // enemy:
        enemy.update();
        enemy.render(batch);

        wall.render(batch);
        //Changed the cannon to sprite to add more functionality
        //batch.draw(cannon.getTextureRegion(), 320, 10, 60, 54, 120, 108, 1,1, -cannon.getAngle());
        cannonSprite.draw(batch);
        if(bullet != null){
            if(bullet.updateBullet()) {
                bullet.getSprite().draw(batch);

            }
            else{
                bullet.dispose();
                bullet = null;
            }
        }
        batch.end();

        //If a mouse click is registered on the gamescreen a cannonball will be spawned
        if(Gdx.input.isTouched() && !touched){
            touched = true;
            int X = (int)bulletAngleX(cannonSprite.getX());
            //int Y = (int)bulletAngleY(cannonSprite.getY());
            bullet = new Bullets(mousePos,370, 34, cannon.getAngle());


        }
        else if(!Gdx.input.isTouched() && touched){
            touched = false;
        }

    }

    public float bulletAngleX (float xBall){
        xBall =  (float)-Math.sin(Math.PI*cannon.getAngle()/180)*54;
        xBall = 400 - xBall - 30;
        return xBall;


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
