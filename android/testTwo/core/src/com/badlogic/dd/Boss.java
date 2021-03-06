package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.graphics.Color;
/**
 * Created by Armand on 4/21/2016.
 */
/**
 * Name: Boss
 * Purpose: * To define the boss component for the game.
            * Boss has a health, attackPower and various properties
            * contained in it's sprite to display it on the screen.
            * The boss moves from the top of the screen down to the wall.
            * The boss starts damaging the wall once it reaches it.
            * The boss spawns at the middle x coordinate along the top of the screen.
 * Author: Armand Abrahamian
 * Date Created: 4/21/2016
 */
public class Boss
{
    // Variables:
        // Simple Data Types:
    private int health, maxHealth, attackPower;
    protected boolean isDead;
    private long prevtime, changeColorTimer;
    private float elapsedTime = 0f, deathTimer = 0f;
    private double WIDTH=60;
    private double HEIGHT=60;
    public Intersector intersector = new Intersector();

    // Sprite Properties:
    private Sprite bossSprite; // enemy sprite
    private TextureAtlas walkingtextureAtlas, attacktextureAtlas, deathtextureAtlas, appeartextureAtlas;
    private TextureRegion walkingtextureRegion;

    private Vector2 velocity; // velocity of the boss
    protected Vector2 position;
    private Rectangle rectangle; // rectangle object to detect collisions
    private Animation walkingAnimation, attackAnimation, deathAnimation, appearAnimation; // animations

    private Wall wall; // Used to refer to wall object passed in

    // Enumerator to hold the direction of the enemy:
    enum Direction{UP,DOWN,LEFT,RIGHT};
    Enemy.Direction direction; //denotes enemies's direction
/*----------------------------------------------------------------------------------*/
    /**
     * Name of Module: Boss
     * Purpose: Constructor for the boss to initialize its data.
     * Input Parameters: The wall object
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 4/21/2016
     */
    public Boss(Wall passedinWall)
    {
        int xcordSpawn = MathUtils.random(0, 600);
        this.isDead = false;

        // Frames loaded from texture atlas:
        walkingtextureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.BossWalkSpriteSheet));
        walkingtextureRegion = walkingtextureAtlas.findRegion("idle", 1);
        attacktextureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.BossAttackSpriteSheet));
        deathtextureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.BossDeathSpriteSheet));
        appeartextureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.BossAppearSpriteSheet));

        this.setMaxHealth(GameConstants.BOSS_MAX_HEALTH);
        this.setCurrentHealth(this.maxHealth);
        this.setAttackPower(5);

        bossSprite = new Sprite(walkingtextureRegion);
        bossSprite.setSize(bossSprite.getWidth()*(GameConstants.screenWidth/GameConstants.BOSS_RESIZE_FACTOR), bossSprite.getHeight()*(GameConstants.screenWidth/GameConstants.BOSS_RESIZE_FACTOR));
        bossSprite.setSize(bossSprite.getWidth()*GameConstants.unitScale, bossSprite.getHeight()*GameConstants.unitScale);
        position = new Vector2(xcordSpawn, GameConstants.screenHeight - bossSprite.getHeight() + 30 );
        bossSprite.setPosition(position.x, position.y);
        velocity = new Vector2(0, (-1)*GameConstants.BOSS_VELOCITY);
        rectangle = new Rectangle();

        // Building the animation:
        walkingAnimation = new Animation(GameConstants.WALK_FRAME_DURATION, walkingtextureAtlas.getRegions(), Animation.PlayMode.LOOP);
        attackAnimation = new Animation(GameConstants.ATTACK_FRAME_DURATION, attacktextureAtlas.getRegions(), Animation.PlayMode.LOOP);
        deathAnimation = new Animation(GameConstants.BOSS_DEATH_FRAME_DURATION, deathtextureAtlas.getRegions(), Animation.PlayMode.NORMAL);
        appearAnimation = new Animation(GameConstants.APPEAR_FRAME_DURATION, appeartextureAtlas.getRegions(), Animation.PlayMode.NORMAL);

        wall = passedinWall;
    }

    public void die()
    {
        this.isDead = true;
        velocity.setZero();
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
        if(this.getCurrentHealth() == 0)
            this.die();
        else
        {
            this.setCurrentHealth(this.getCurrentHealth() - damage);
            bossSprite.setColor(Color.RED);
        }
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

    public boolean isCollided(Rectangle rect)
    {
        Gdx.app.log("Collision Detected", ""+ rectangle.overlaps(rect));
        return rect.overlaps(rectangle);
    }
    public boolean isCollided(Bullets b)
    {
        Vector2 bpos = b.getBulletPosition();
        Gdx.app.log("Collision with bullet detected", "" + bpos.x + ", " + bpos.y);
        com.badlogic.gdx.math.Circle cir = new com.badlogic.gdx.math.Circle((float) (bpos.x+WIDTH/2), (float) (bpos.y + HEIGHT/2), (float) (2*WIDTH/2));

        //return r.overlaps(rectangle);
        return intersector.overlaps(cir, rectangle);
    }

    // Render and Update Methods:

    /**
     * Name of Module: render
     * Purpose: Draws the enemy sprite on the screen.
     * Input Parameters: SpriteBatch batch
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void render(SpriteBatch batch, float delta) {
        elapsedTime += delta;
        //elapsedTime += Gdx.graphics.getDeltaTime();
        // Getting the frame which must be rendered

        if (appearAnimation.isAnimationFinished(elapsedTime) == false) {
            bossSprite.setRegion(appearAnimation.getKeyFrame(elapsedTime));
            bossSprite.draw(batch); // Drawing the frame
        }
        else {
            if (isDead != true && bossSprite.getY() > 130)
                bossSprite.setRegion(walkingAnimation.getKeyFrame(elapsedTime));
            else if (isDead != true && bossSprite.getY() <= 130)
                bossSprite.setRegion(attackAnimation.getKeyFrame(elapsedTime));
            // Drawing the frame
            bossSprite.draw(batch);
            if(TimeUtils.nanoTime() - changeColorTimer > 1000000000/2) {
                bossSprite.setColor(Color.WHITE);
                changeColorTimer = TimeUtils.nanoTime();
            }
        }
    }

    /**
     * Name of Module: playDeathAnimation
     * Purpose: Draws the boss death animation sprite on the screen.
     * Input Parameters: SpriteBatch batch, float delta
     * Output Parameters: boolean ok
     * Author: Armand Abrahamian
     * Creation Date: 4/20/2016
     */
    public boolean playDeathAnimation(SpriteBatch batch, float delta)
    {
        boolean ok = false;
        deathTimer += delta;

        bossSprite.setRegion(deathAnimation.getKeyFrame(deathTimer));
        bossSprite.draw(batch); // Drawing the frame
        if (deathAnimation.isAnimationFinished(deathTimer) == true)
            ok = true;
        return ok;
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
        rectangle.setSize(bossSprite.getWidth(), bossSprite.getHeight());

        // change direction based on velocity
        // For x-axis:
//        if (velocity.x < 0) {
//            direction = Enemy.Direction.LEFT;
//        } else {
//            direction = Enemy.Direction.RIGHT;
//        }
//
//        // Flip sprite when going right.
//        if(direction == Enemy.Direction.RIGHT){
//            bossSprite.setFlip(true, false);
//        }
//        else {
//            bossSprite.setFlip(false, false);
//        }

        // For y-axis:
        if (velocity.y < 0) {
            direction = Enemy.Direction.DOWN;
        } else {
            direction = Enemy.Direction.UP;
        }

        // Move and stop enemy:
        if (bossSprite.getY() > 130 ) {
            position.add(velocity);
            bossSprite.setY(position.y);
            rectangle.setPosition(position);
        }
        else {
            bossSprite.setY(position.y);
            rectangle.setPosition(position);

            if(TimeUtils.nanoTime() - prevtime > 1000000000) { // damages every second
                this.attackWall(wall); // Damage wall
                System.out.println("Wall health after taking damage: " + wall.getHealth());
                prevtime = TimeUtils.nanoTime();
            }
        }

    }
    /**
     * Name of Module: dispose
     * Purpose: Cleans up resources used for boss.
     * Input Parameters: N/A
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 4/15/2016
     */
    public void dispose()
    {
        walkingtextureAtlas.dispose();
        attacktextureAtlas.dispose();
        deathtextureAtlas.dispose();
    }
}
