package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GameConstants
{
    public static final String enemyImage = "singleSkeleton.png";
    public static final String SkeletonWalkSpriteSheet = "skeletonwalksheet.atlas";
    public static final String SkeletonAttackSpriteSheet = "skeletonattack.atlas";
    public static final String SkeletonDeathSpriteSheet = "skeletondead.atlas";
    public static final String BossWalkSpriteSheet = "bosswalk.atlas";
    public static final String BossAttackSpriteSheet = "bossattack.atlas";
    public static final String BossDeathSpriteSheet = "bossdead.atlas";
    public static final String BossAppearSpriteSheet = "bossappear.atlas";
    public static final float unitScale = 1/2f;
    public static final float ENEMY_RESIZE_FACTOR = 1500f;
    public static final float BOSS_RESIZE_FACTOR = 1000f;
    public static final float X_MOVE_UNITS = 0.1f; // units will move in x direction
    public static final float Y_MOVE_UNITS = 0.1f; // units will move in y direction
    public static final float maxVelocity = 0.1f;
    public static final float SKELETON_VELOCITY = 0.29f;
    public static final float BOSS_VELOCITY = 0.18f;
    public static final float SKELETON_ATTACK_SPEED = 0.1f;
    public static float WALK_FRAME_DURATION = 0.08f; // Time which each walk frame is kept on the screen
    public static float ATTACK_FRAME_DURATION = 0.12f; // Time which each attack frame is kept on the screen
    public static float DEATH_FRAME_DURATION = 0.11f; // Time which each death frame is kept on the screen
    public static float BOSS_DEATH_FRAME_DURATION = 0.12f; // Time which each death frame for boss is kept on the screen
    public static float APPEAR_FRAME_DURATION = 0.1f; // Time which each appear frame is kept on the screen


    //public static float screenWidth = Gdx.graphics.getWidth();
    //public static float screenHeight = Gdx.graphics.getHeight();
    public static float screenWidth = 800;
    public static float screenHeight = 480;
}
