package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

public class GameConstants
{
    public static final String enemyImage = "heliboy.png";
    public static final String backgroundImage = "sand.jpg";
    public static final float unitScale = 1/2f;
    public static final float ENEMY_RESIZE_FACTOR = 750f;
    public static final float X_MOVE_UNITS = 0.1f; // units will move in x direction
    public static final float Y_MOVE_UNITS = 0.1f; // units will move in y direction
    public static final float maxVelocity = 0.1f;
    public static final float HELIBOY_VELOCITY = 0.1f;
    public static final Vector2 spawnPoint = new Vector2(6, 7);
}
