package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameConstants;

public class Heliboy extends Enemy
{
    enum Direction{UP,DOWN,LEFT,RIGHT};
    Direction left_direction = Direction.LEFT; //denotes enemies's direction
    Direction right_direction = Direction.RIGHT; //denotes enemies's direction
    Direction down_direction = Direction.DOWN; //denotes enemies's direction
    Direction up_direction = Direction.UP; //denotes enemies's direction

    public Heliboy(float width, float height, Texture enemyTexture)
    {
        enemySprite = new Sprite(enemyTexture);
        enemySprite.setSize(enemySprite.getWidth()*(width/GameConstants.ENEMY_RESIZE_FACTOR), enemySprite.getHeight()*(width/GameConstants.ENEMY_RESIZE_FACTOR));
        enemySprite.setSize(enemySprite.getWidth()*GameConstants.unitScale, enemySprite.getHeight()*GameConstants.unitScale);
        enemySprite.setPosition(17, 4);
        velocity = new Vector2(GameConstants.HELIBOY_VELOCITY, 0);
        rectangle = new Rectangle();
    }

    @Override
    public void render(SpriteBatch batch)
    {
        enemySprite.draw(batch);
    }

    @Override
    public void update ()
    {
        // set the rectangle with Heliboy's dimensions for collisions
        rectangle.set(enemySprite.getX(), enemySprite.getY(),
                enemySprite.getWidth(), enemySprite.getHeight());
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
