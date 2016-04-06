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

public class Enemy
{
    // Variables:

    protected int health, maxHealth, currentHealth, power;
    protected boolean isDead;
    protected Sprite enemySprite; // enemy sprite
    private TextureAtlas textureAtlas;
    private Texture enemyImage;
    private TextureRegion textureRegion;
    protected Vector2 velocity; // velocity of the enemy
    protected Vector2 position;
    protected Rectangle rectangle; // rectangle object to detect collisions
    Wall wall;

    enum Direction{UP,DOWN,LEFT,RIGHT};
    Direction direction; //denotes enemies's direction

    // Constructor:
    public Enemy(Wall passedinWall)
    {
        int xcordSpawn = MathUtils.random(0, 600);
        textureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.skeletonSpriteSheet));
        textureRegion = textureAtlas.findRegion("go", 1);
        enemyImage =  new Texture(GameConstants.enemyImage);
        health = 1;
        power = 1;
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

    public void die()
    {

    }

    public int attack()
    {
        return power;
    }

    public void hurt(int damage)
    {
        this.health -= damage;
    }

    // Getters and Setters:
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getPower() {
        return power;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isCollided(Rectangle rect)
    {
        Gdx.app.log("Collision Detected", ""+ rectangle.overlaps(rect));
        return rect.overlaps(rectangle);
    }

    // Rendering Methods:
    public void render(SpriteBatch batch)
    {
        enemySprite.draw(batch);
    }

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
        if (enemySprite.getY() > 120 ) {
            position.add(velocity);
            enemySprite.setY(position.y);
            rectangle.setPosition(position);
        }
        else {
            position.sub(velocity);
            enemySprite.setY(position.y);
            rectangle.setPosition(position);
            wall.adjustHealth(-1 * this.getPower());
        }

    }

}