package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameConstants;

public class Skeleton extends Enemy
{
    // Variables:
//    Animation walkAnimation;
//    TextureRegion walkSheet;
//    TextureRegion currentFrame;
//    float stateTime;
//    private static int ANIMATION_FRAME_SIZE = 8;
    int currentFrame = 1;
    int MAX_FRAMES = 8;

    enum Direction{UP,DOWN,LEFT,RIGHT};
    Direction direction = Direction.DOWN; //denotes enemies's direction

    // Constructor:
    public Skeleton(float width, float height, TextureRegion skeletonTexture, int coordSpawn)
    {
        enemySprite = new Sprite(skeletonTexture);
        enemySprite.setSize(enemySprite.getWidth()*(width/GameConstants.ENEMY_RESIZE_FACTOR), enemySprite.getHeight()*(width/GameConstants.ENEMY_RESIZE_FACTOR));
        enemySprite.setSize(enemySprite.getWidth()*GameConstants.unitScale, enemySprite.getHeight()*GameConstants.unitScale);
        enemySprite.setPosition(coordSpawn, height);
        velocity = new Vector2(0, (-1)*GameConstants.SKELETON_VELOCITY);
        rectangle = new Rectangle();
    }
    // Behavioral Methods:
    public void die()
    {

    }

    public void attack()
    {

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

    @Override
    public void render(SpriteBatch batch)
    {
        enemySprite.draw(batch);
    }

    @Override
    public void update ()
    {
        // set the rectangle with skeleton's dimensions for collisions
        rectangle.set(enemySprite.getX(), enemySprite.getY(),
                enemySprite.getWidth(), enemySprite.getHeight());

        // change direction based on velocity
            // For x-axis:
        if (velocity.x < 0) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.RIGHT;
        }
        enemySprite.setX(enemySprite.getX()+velocity.x);
        if(direction==Direction.RIGHT){
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
        enemySprite.setY(enemySprite.getY()+velocity.y);
    }

//    public void checkWallHit() {
//// get the tiles from map utilities
//        Array<Rectangle> tiles = MapUtils.getHorizNeighbourTiles
//                (velocity, sprite, "Wall");
////if zombie collides with any wall tile while walking
//        right/left, reverse his horizontal motion
//        for (Rectangle tile : tiles) {
//            if (rectangle.overlaps(tile)) {
//                velocity.x *=-1;
//                break;
//            }
//        }
//    }

}
