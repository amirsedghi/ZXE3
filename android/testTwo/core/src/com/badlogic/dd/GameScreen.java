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
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.*;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by evolerup on 3/7/16.
 */
public class GameScreen implements Screen {
    final DD game;

    private OrthographicCamera camera;
    private SpriteBatch batch;
    public Vector3 mousePos;

    // Declaring game components: cannon, wall, enemy, and bullet:
    Cannon cannon = new Cannon(); // instantiate cannon object
    private Wall wall;
    ArrayList<Enemy> enemies;
    long lastSpawnTime;
    Enemy enemy;
    Bullets bullet = null;

    /**
     * Name of Module: GameScreen
     * Purpose: Constructor for Gamescreen class to initialize game objects.
     * Input Parameters:
     * Output Parameters: N/A
     * Author: -
     * Creation Date: 3/7/2016
     */
    public GameScreen(final DD gam){
        this.game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        wall = new Wall(100, 120); // instantiate wall object
        System.out.println("Wall Created");
        System.out.println("Wall Current Health: " + wall.getHealth());
        enemies = new ArrayList();// Create array list of enemies
        System.out.println("Initial size of enemies: " + enemies.size());
    }

    /**
     * Name of Module: render
     * Purpose: render is a method called by the game loop from the application every time rendering
     *          should be performed. Game logic updates are usually also performed in this method.
     * Input Parameters: float delta
     * Output Parameters: N/A
     * Author: -
     * Creation Date: 3/7/2016
     */
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

        // print the angle
        //System.out.println("angle: "+-cannon.getAngle());

        // Draw and update the enemies properties:
        for(int index = 0; index < enemies.size(); index++)
        {
            enemies.get(index).update();
            enemies.get(index).render(batch);

            if(enemies.get(index).isDead == true)
            {
                System.out.println("---Enemy " + index + " is dead!---");
                enemies.remove(enemies.get(index));
            }
        }

        wall.render(batch); // Draw wall onto screen

        //Changed the cannon to sprite to add more functionality
        //batch.draw(cannon.getTextureRegion(), 320, 10, 60, 54, 120, 108, 1,1, -cannon.getAngle());
        cannonSprite.draw(batch);
        if(bullet != null){
            if(bullet.updateBullet()) {
                bullet.getSprite().draw(batch);
            }
            else{
                // Dispose/hide the bullet, because it landed
                // Now, check whether or not it has hit an enemy.

                for (Enemy e : enemies) {
                    if (e.isCollided(bullet)) {
                        e.die();
                    }
                }

                bullet.dispose();
                bullet = null;
            }
        }
        batch.end();

        // Enemy Spawn Timer:
        if(TimeUtils.nanoTime() - lastSpawnTime > 10000000000f)
        {
            if(enemies.size() > 20)
            {
                ;
            }
            else
                spawnEnemy();
        }

        //If a mouse click is registered on the gamescreen a cannonball will be spawned
        if(Gdx.input.isTouched() && bullet == null){
            int X = (int)bulletAngleX(cannonSprite.getX());
            //int Y = (int)bulletAngleY(cannonSprite.getY());
            bullet = new Bullets(mousePos,370, 34, cannon.getAngle());
        }
    }

    public float bulletAngleX (float xBall){
        xBall =  (float)-Math.sin(Math.PI*cannon.getAngle()/180)*54;
        xBall = 400 - xBall - 30;
        return xBall;


    }

    // Helper function to spawn enemies:
    public void spawnEnemy()
    {
        enemy = new Enemy(wall); // instantiate enemy object
        enemies.add(enemy);
        System.out.println("----Enemy Spawned----");
        System.out.println("Number of enemies: " + enemies.size());
        lastSpawnTime = TimeUtils.nanoTime();
    }

    /**
     * Name of Module: resize
     * Purpose: render is a method is only called on Android, when the application resumes from a paused state.
     * Input Parameters: int width, int height
     * Output Parameters: N/A
     * Author: -
     * Creation Date: 3/7/2016
     */
    public void resize(int width, int height){

    }

    public void show(){

    }

    public void hide(){

    }

    /**
     * Name of Module: pause
     * Purpose: On Android this method is called when the Home button is pressed or an incoming call is received.
     *          On desktop this is called just before dispose() when exiting the application.
     *          A good place to save the game state.
     * Input Parameters: N/A
     * Output Parameters: N/A
     * Author: -
     * Creation Date: 3/7/2016
     */
    public void pause(){

    }

    /**
     * Name of Module: resume
     * Purpose: This method is only called on Android, when the application resumes from a paused state.
     * Input Parameters: N/A
     * Output Parameters: N/A
     * Author: -
     * Creation Date: 3/7/2016
     */
    public void resume(){

    }

    /**
     * Name of Module: dispose
     * Purpose: Called when the application is destroyed. It is preceded by a call to pause().
     * Input Parameters: N/A
     * Output Parameters: N/A
     * Author: -
     * Creation Date: 3/7/2016
     */
    public void dispose(){

    }
}
