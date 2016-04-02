package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameConstants;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Skeleton extends Enemy
{
    // Variables:
//    Animation walkAnimation;
//    TextureRegion walkSheet;
//    TextureRegion currentFrame;
//    float stateTime;
//    private static int ANIMATION_FRAME_SIZE = 8;
//    int currentFrame = 1;
//    int MAX_FRAMES = 8;

    enum Direction{UP,DOWN,LEFT,RIGHT};
    Direction direction; //denotes enemies's direction

    // Constructor:
    public Skeleton(float width, float height, TextureRegion skeletonTexture, int xcoordSpawn)
    {
        health = 1;
        power = 1;
        isDead = false;
        position = new Vector2(xcoordSpawn, height);
        enemySprite = new Sprite(skeletonTexture);
        enemySprite.setSize(enemySprite.getWidth()*(width/GameConstants.ENEMY_RESIZE_FACTOR), enemySprite.getHeight()*(width/GameConstants.ENEMY_RESIZE_FACTOR));
        enemySprite.setSize(enemySprite.getWidth()*GameConstants.unitScale, enemySprite.getHeight()*GameConstants.unitScale);
        enemySprite.setPosition(position.x, position.y);
        velocity = new Vector2(0, (-1)*GameConstants.SKELETON_VELOCITY);
        rectangle = new Rectangle();
//        circle = new Circle();
    }
    // Behavioral Methods:
//    public void checkCollision(Skeleton skeleton)
//    {
//        if (skeleton.getRectangle().overlaps(skeleton.getRectangle()))
//        {
//            skeleton.stop();
//        }
//    }

    public void dead()
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
        Gdx.app.log("Collision Detected", "" + rectangle.overlaps(rect));
        return rect.overlaps(rectangle);
    }

    public void checkforcollisions(Rectangle rect)
    {
        Gdx.app.log("Collision Detected", "" + rectangle.overlaps(rect));
    }

    public boolean isOutOfBounds()
    {
        if( ( position.x > Gdx.graphics.getWidth() || position.x < Gdx.graphics.getWidth() ) || position.y < Gdx.graphics.getHeight() )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
//    public Circle getCircle()
//    {
//        return circle;
//    }

    // Rendering Methods:
    @Override
    public void render(SpriteBatch batch)
    {
        enemySprite.draw(batch);
    }

    @Override
    public void update ()
    {
        // set the rectangle with skeleton's dimensions for collisions
//        rectangle.setPosition(enemySprite.getX(), enemySprite.getY());
        rectangle.setPosition(position);
        rectangle.setSize(enemySprite.getWidth(), enemySprite.getHeight());

        // change direction based on velocity
            // For x-axis:
        if (velocity.x < 0) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.RIGHT;
        }
//        enemySprite.setX(enemySprite.getX()+velocity.x);

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
        if (enemySprite.getY() > 75) {
            position.add(velocity);
            enemySprite.setY(position.y);
            rectangle.setPosition(position);
        }
        else {
            position.sub(velocity);
            enemySprite.setY(position.y);
            rectangle.setPosition(position);
        }
    }

}
