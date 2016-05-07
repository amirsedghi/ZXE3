package com.badlogic.dd;

import java.beans.VetoableChangeListener;
import java.sql.Time;
import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.btree.utils.DistributionAdapters;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    private Vector3 mouseClickPos;
    private Texture backgroundimg;
    private Sprite backgroundsprite;
    private Vector2 explosionPosition;

    // Declaring game components: cannon, wall, enemy, and bullet:
    Cannon cannon = new Cannon(); // instantiate cannon object
    private Wall wall;
    private ArrayList<Enemy> enemies;
    long lastSpawnTime; // holds enemies last spawn time
    long lastBulletTime;
    private Enemy enemy;
    Bullets bullet = null;
    ArrayList<Bullets> ammo;
    ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Boss boss;
    private int bosscounter = 0, score = 0;
    private boolean hasBossSpawned = false;
    private long startTime = TimeUtils.nanoTime();
    private double WIDTH = 60;
    private double HEIGHT = 60;
    private ParticleEffect effect = new ParticleEffect();

    /**
     * Name of Module: GameScreen
     * Purpose: Constructor for Gamescreen class to initialize game objects.
     * Input Parameters:
     * Output Parameters: N/A
     * Author: -
     * Creation Date: 3/7/2016
     */
    public GameScreen(final DD gam) {
        backgroundimg = new Texture(GameConstants.backgroundImage);
        backgroundsprite = new Sprite(backgroundimg);
        backgroundsprite.setSize(GameConstants.screenWidth, GameConstants.screenHeight);
        this.game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        wall = new Wall(100, 120); // instantiate wall object
        System.out.println("Wall Created");
        System.out.println("Wall Current Health: " + wall.getHealth());
        enemies = new ArrayList();// Create array list of enemies
        ammo = new ArrayList();//array list for cannonballs
        lastBulletTime = 0;//last bullet fired
        System.out.println("Initial size of enemies: " + enemies.size());
        effect.load(Gdx.files.internal("explosion.p"), Gdx.files.internal("img"));
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

        if (wall.getHealth() == 0) {
            game.setScreen(new MainMenu(game, score));
            dispose();
            return;
        }

        //Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // get the coordinates of mouse position
        if(Gdx.input.isTouched())
            mouseClickPos= new Vector3(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
        mousePos = new Vector3(camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
        // pass the vector to the cannon instant
        cannon.setVector(mousePos);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Cannon sprite properties
        Sprite cannonSprite = cannon.getSprite();
        cannonSprite.setOrigin(cannonSprite.getWidth() / 2, 54);
        cannonSprite.setRotation(-cannon.getAngle());
        cannonSprite.setX(340);
        cannonSprite.setY(10);

        batch.begin();

        backgroundsprite.draw(batch); // draw the background as the first thing!

        // print the angle
        //System.out.println("angle: "+-cannon.getAngle());

        // Draw and update the enemies properties:
        for (int index = 0; index < enemies.size(); index++) {
            enemies.get(index).update();
            enemies.get(index).render(batch, delta);

            if (enemies.get(index).isDead == true) {
                if(enemies.get(index).playDeathAnimation(batch, delta) == true) {
                    System.out.println("---Enemy " + index + " is dead!---");
                    enemies.remove(enemies.get(index));
                    score += 1;
                    if(hasBossSpawned == false) {
                        bosscounter++;
                        System.out.println("Bosscounter: " + bosscounter);
                    }
                 }
            }
        }

        if (bosscounter >= GameConstants.BOSS_COUNTER && hasBossSpawned != true)
        {
            spawnBoss();
        }

        if (hasBossSpawned == true) {
            boss.render(batch, delta);
            boss.update();
            if (boss.isDead == true) {
                if (boss.playDeathAnimation(batch, delta) == true) {
                    System.out.println("---Boss is dead!---");
                    bosscounter = 0;
                    hasBossSpawned = false;
                    score += 10;
                }
            }
        }

        wall.render(batch); // Draw wall onto screen
        effect.draw(batch, delta);

        //Changed the cannon to sprite to add more functionality
        //batch.draw(cannon.getTextureRegion(), 320, 10, 60, 54, 120, 108, 1,1, -cannon.getAngle());
        cannonSprite.draw(batch);

        for(int i = 0; i < ammo.size(); i++) {
            if (ammo.get(i) != null) {
                if (ammo.get(i).updateBullet()) {
                    ammo.get(i).getSprite().draw(batch);
                }
                else {
                    // Dispose/hide the bullet, because it landed
                    explosionPosition = ammo.get(i).getBulletPosition();
                    effect.getEmitters().first().setPosition((float) (explosionPosition.x + WIDTH/2),(float) (explosionPosition.y + HEIGHT/2));
                    effect.start();

                    // Now, check whether or not it has hit an enemy.
                    for (Enemy e : enemies) {
                        if (e.isCollided(ammo.get(i), batch, delta)) {
                            e.die();
                        }
                    }
                    if(hasBossSpawned == true)
                    {
                        if (boss.isCollided(ammo.get(i)))
                        {
                            boss.hurt(1);
                        }
                    }

                    ammo.get(i).dispose();
                    ammo.remove(ammo.get(i));
                }
            }
        }

        batch.end();

        // Draw UI elements last:

        // Health bar background
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(100, 100, 200, 40);
        shapeRenderer.end();
        // Health bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(wall.getHealthBarColor());
        shapeRenderer.rect(100, 100, wall.getHealth() * 2, 40);
        shapeRenderer.end();

        // Enemy Spawn Timer:
        if(TimeUtils.nanoTime() - lastSpawnTime > 1000000000f)
        {
            if(enemies.size() > 20)
            {
                ; // Do nothing
            }
            else
                spawnEnemy();
        }

        //If a mouse click is registered on the gamescreen a cannonball will be spawned
        //if half a second has passed since last bullet
        if(Gdx.input.isTouched() && TimeUtils.nanoTime() - lastBulletTime > 500000000f){
            //int X = (int)bulletAngleX(cannonSprite.getX());
            //int Y = (int)bulletAngleY(cannonSprite.getY());
                bullet = new Bullets(mouseClickPos, 370, 34, cannon.getAngle());
                ammo.add(bullet);
            lastBulletTime = TimeUtils.nanoTime();
        }
    }

    public float bulletAngleX (float xBall){
        xBall =  (float)-Math.sin(Math.PI*cannon.getAngle()/180)*54;
        xBall = 400 - xBall - 30;
        return xBall;
    }

    // Helper functions to spawn enemies:
    public void spawnEnemy()
    {
        enemy = new Enemy(wall); // instantiate enemy object
        enemies.add(enemy);
        System.out.println("----Enemy Spawned----");
        System.out.println("Number of enemies: " + enemies.size());
        lastSpawnTime = TimeUtils.nanoTime();
    }

    public void spawnBoss()
    {
        boss = new Boss(wall);
        System.out.println("----Boss Spawned----");
        hasBossSpawned = true;
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
        bosscounter = 0;
        hasBossSpawned = false;
        enemies.clear();
        enemy.dispose();
        if (boss != null) {
            boss.dispose();
        }
        backgroundimg.dispose();
        effect.dispose();
    }
}
