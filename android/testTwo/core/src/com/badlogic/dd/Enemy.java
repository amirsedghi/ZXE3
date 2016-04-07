package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;

/**
 * Name: Enemy
 * Purpose: * To define the enemy component for te game.
            * Enemy has a health, attackPower and various properties
            * contained in it's sprite to display it on the screen.
            * The enemy moves from the top of the screen down to the wall.
            * The enemy starts damaging the wall once it reaches it.
            * The enemy spawns at a random x coordinate along the top of the screen.
 * Author: Armand Abrahamian
 * Date Created: 3/13/2016
 */
public class Enemy
{
    // Variables:
        // Simple Data Types:
    protected int health, maxHealth, attackPower;
    protected boolean isDead;
        // Sprite Properties:
    protected Sprite enemySprite; // enemy sprite
    private TextureAtlas textureAtlas;
    private Texture enemyImage;
    private TextureRegion textureRegion;
        // Other Properties:
    protected Vector2 velocity; // velocity of the enemy
    protected Vector2 position;
    protected Rectangle rectangle; // rectangle object to detect collisions

    Wall wall;

    enum Direction{UP,DOWN,LEFT,RIGHT};
    Direction direction; //denotes enemies's direction
/*----------------------------------------------------------------------------------*/
    /**
     * Name of Module: Enemy
     * Purpose: Constructor for the enemy to initialize its data.
     * Input Parameters: The wall object
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/13/2016
     */
    public Enemy(Wall passedinWall)
    {
        int xcordSpawn = MathUtils.random(0, 600);
        textureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.skeletonSpriteSheet));
        textureRegion = textureAtlas.findRegion("go", 1);
        enemyImage =  new Texture(GameConstants.enemyImage);
        this.setMaxHealth(1);
        this.setCurrentHealth(this.maxHealth);
        this.setAttackPower(1);
        position = new Vector2(xcordSpawn, GameConstants.screenHeight);
        enemySprite = new Sprite(enemyImage);
        enemySprite.setSize(enemySprite.getWidth()*(GameConstants.screenWidth/GameConstants.ENEMY_RESIZE_FACTOR), enemySprite.getHeight()*(GameConstants.screenWidth/GameConstants.ENEMY_RESIZE_FACTOR));
        enemySprite.setSize(enemySprite.getWidth()*GameConstants.unitScale, enemySprite.getHeight()*GameConstants.unitScale);
        enemySprite.setPosition(position.x, position.y);
        velocity = new Vector2(0, (-1)*GameConstants.SKELETON_VELOCITY);
        rectangle = new Rectangle();
        wall = passedinWall;
    }
    // Behavioral Methods:

    // TODO
    public void die()
    {

    }

    /**
     * Name of Module: attackWall
     * Purpose: Wall takes damage.
     * Input Parameters: The wall object
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void attackWall(Wall wall)
    {
        wall.adjustHealth(-1 * this.getAttackPower());
    }

    /**
     * Name of Module: hurt
     * Purpose: Enemy takes damage and loses health.
     * Input Parameters: int damage
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void hurt(int damage)
    {
        this.setCurrentHealth(this.getCurrentHealth() - damage);
    }

    // Getters and Setters:

    /**
     * Name of Module: setMaxHealth
     * Purpose: Sets the maximum health for the enemy.
     * Input Parameters: int maxHealth
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Name of Module: setCurrentHealth
     * Purpose: Sets the current health for the enemy.
     * Input Parameters: int currentHealth
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void setCurrentHealth(int currentHealth) {
        this.health = currentHealth;
    }

    /**
     * Name of Module: setAttackPower
     * Purpose: Sets the attack power for the enemy.
     * Input Parameters: int power
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void setAttackPower(int power) {
        this.attackPower = power;
    }

    /**
     * Name of Module: getMaxHealth
     * Purpose: Return the maximum health
     * Input Parameters: N/A
     * Output Parameters: int maxHealth
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Name of Module: getCurrentHealth
     * Purpose: Return the current health
     * Input Parameters: N/A
     * Output Parameters: int health
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public int getCurrentHealth() {
        return health;
    }

    /**
     * Name of Module: getAttackPower
     * Purpose: Return the enemy's attack power
     * Input Parameters: N/A
     * Output Parameters: int attackPower
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Name of Module: getRectangle
     * Purpose: Return the rectangle drawn around the enemy sprite
     * Input Parameters: N/A
     * Output Parameters: Rectangle rectangle
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    // TODO
    public boolean isCollided(Rectangle rect)
    {
        Gdx.app.log("Collision Detected", ""+ rectangle.overlaps(rect));
        return rect.overlaps(rectangle);
    }

    // Rendering Methods:

    /**
     * Name of Module: render
     * Purpose: Draws the enemy sprite on the screen.
     * Input Parameters: SpriteBatch batch
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void render(SpriteBatch batch)
    {
        enemySprite.draw(batch);
    }

    /**
     * Name of Module: update
     * Purpose: Updates the enemy's properties.
     *          Checks when enemy has reached the wall.
     * Input Parameters: N/A
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void update ()
    {
        // set the rectangle with skeleton's dimensions for collisions
        rectangle.setPosition(position);
        rectangle.setSize(enemySprite.getWidth(), enemySprite.getHeight());

        // change direction based on velocity
        // For x-axis:
        if (velocity.x < 0) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.RIGHT;
        }

        // Flip sprite when going right.
        if(direction == Direction.RIGHT){
            enemySprite.setFlip(true, false);
        }
        else {
            enemySprite.setFlip(false, false);
        }

        // For y-axis:
        if (velocity.y < 0) {
            direction = Direction.DOWN;
        } else {
            direction = Direction.UP;
        }

        // Move and stop enemy:
        if (enemySprite.getY() > 130 ) {
            position.add(velocity);
            enemySprite.setY(position.y);
            rectangle.setPosition(position);
        }
        else {
            position.sub(velocity);
            enemySprite.setY(position.y);
            rectangle.setPosition(position);
            // Damage wall
            this.attackWall(wall);
        }

    }

}