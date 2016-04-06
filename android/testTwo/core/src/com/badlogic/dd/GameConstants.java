package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GameConstants
{
    public static final String enemyImage = "singleSkeleton.png";
    public static final String backgroundImage = "sand.jpg";
    public static final String skeletonSpriteSheet = "skeleton.atlas";
    public static final String music = "mirage.mp3";
    public static final float unitScale = 1/2f;
    public static final float ENEMY_RESIZE_FACTOR = 1500f;
    public static final float X_MOVE_UNITS = 0.1f; // units will move in x direction
    public static final float Y_MOVE_UNITS = 0.1f; // units will move in y direction
    public static final float maxVelocity = 0.1f;
    public static final float SKELETON_VELOCITY = 0.2f;
    //public static float screenWidth = Gdx.graphics.getWidth();
    //public static float screenHeight = Gdx.graphics.getHeight();
    public static float screenWidth = 800;
    public static float screenHeight = 480;
}
