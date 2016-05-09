package com.badlogic.dd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import javafx.scene.shape.Circle;

/**
 * Name: Enemy
 * Purpose: * To define the enemy component for the game.
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
    private int health, maxHealth, attackPower, behavior, updateCount;
    protected boolean isDead = false;
    private long prevtime;
    private float elapsedTime = 0f;
    private float deathTimer = 0f;
    private double WIDTH=60;
    private double HEIGHT=60;
    public Intersector intersector = new Intersector();
    private Vector2 bpos;
    private Bullets theBullet;
        // Sprite Properties:
    private Sprite enemySprite; // enemy sprite
    private TextureAtlas walkingtextureAtlas, attacktextureAtlas, deathtextureAtlas;
    private TextureRegion walkingtextureRegion;

        // Other Properties for enemy:
    private Vector2 velocity; // velocity of the enemy
    protected Vector2 position;
    private Rectangle rectangle; // rectangle object to detect collisions
    private Animation walkingAnimation, attackAnimation, deathAnimation; // animations

    private Wall wall; // Used to refer to wall object passed in

    // Enumerator to hold the direction of the enemy:
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

        // Frames loaded from texture atlas:
        walkingtextureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.SkeletonWalkSpriteSheet));
        walkingtextureRegion = walkingtextureAtlas.findRegion("go", 1);
        attacktextureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.SkeletonAttackSpriteSheet));
        deathtextureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.SkeletonDeathSpriteSheet));

        this.setMaxHealth(1);
        this.setCurrentHealth(this.maxHealth);
        this.setAttackPower(1);
        position = new Vector2(xcordSpawn, GameConstants.screenHeight);
        enemySprite = new Sprite(walkingtextureRegion);
        enemySprite.setSize(enemySprite.getWidth()*(GameConstants.screenWidth/GameConstants.ENEMY_RESIZE_FACTOR), enemySprite.getHeight()*(GameConstants.screenWidth/GameConstants.ENEMY_RESIZE_FACTOR));
        enemySprite.setSize(enemySprite.getWidth()*GameConstants.unitScale/2, enemySprite.getHeight()*GameConstants.unitScale/2);
        enemySprite.setPosition(position.x, position.y);
        velocity = new Vector2(0, (-1)*GameConstants.SKELETON_VELOCITY);
        rectangle = new Rectangle();

        // Building the animation:
        walkingAnimation = new Animation(GameConstants.WALK_FRAME_DURATION, walkingtextureAtlas.getRegions(), Animation.PlayMode.LOOP);
        attackAnimation = new Animation(GameConstants.ATTACK_FRAME_DURATION, attacktextureAtlas.getRegions(), Animation.PlayMode.LOOP);
        deathAnimation = new Animation(GameConstants.DEATH_FRAME_DURATION, deathtextureAtlas.getRegions(), Animation.PlayMode.NORMAL);

        wall = passedinWall;
        // Behavior defined
        behavior = 1;
    }
    // Behavioral Methods:

    /**
     * Name of Module: die
     * Purpose: sets the flag for when the enemy reaches a health of 0.
     * Input Parameters: N/A
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 4/12/2016
     */
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
    public boolean isCollided(Bullets b, SpriteBatch batch, float delta)
    {
        theBullet = b;
        if (b != null) {
            bpos = b.getBulletPosition();
            Gdx.app.log("Collision with bullet detected", "" + bpos.x + ", " + bpos.y);
            com.badlogic.gdx.math.Circle cir = new com.badlogic.gdx.math.Circle((float) (bpos.x + WIDTH / 2), (float) (bpos.y + HEIGHT / 2), (float) (2 * WIDTH / 2));

            return intersector.overlaps(cir, rectangle);
        }
        else
            return false;

    }

    // Render and Update Methods:

    /**
     * Name of Module: render
     * Purpose: Draws the enemy sprite on the screen.
     * Input Parameters: SpriteBatch batch, float delta
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 3/15/2016
     */
    public void render(SpriteBatch batch, float delta)
    {
        elapsedTime += delta;
        // Getting the frame which must be rendered
        if (enemySprite.getY() > 130 && isDead != true) {
            enemySprite.setRegion(walkingAnimation.getKeyFrame(elapsedTime));
        }
        else if(enemySprite.getY() <= 130 && isDead != true)
        {
            enemySprite.setRegion(attackAnimation.getKeyFrame(elapsedTime));
        }
        enemySprite.draw(batch);
    }

    /**
     * Name of Module: playDeathAnimation
     * Purpose: Draws the enemy death animation sprite on the screen.
     * Input Parameters: SpriteBatch batch, float delta
     * Output Parameters: N/A
     * Author: Armand Abrahamian
     * Creation Date: 4/20/2016
     */
    public boolean playDeathAnimation(SpriteBatch batch, float delta)
    {
        boolean ok = false;
        deathTimer += delta;

        enemySprite.setRegion(deathAnimation.getKeyFrame(deathTimer));
        // Drawing the frame
        enemySprite.draw(batch);
        if (deathAnimation.isAnimationFinished(deathTimer) == true)
        {
            ok = true;
        }
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
        updateCount++;
        // set the rectangle with skeleton's dimensions for collisions
        rectangle.setPosition(position);
        rectangle.setSize(enemySprite.getWidth(), enemySprite.getHeight());

        // change direction based on velocity
        // For x-axis:
//        if (velocity.x < 0) {
//            direction = Direction.LEFT;
//        } else {
//            direction = Direction.RIGHT;
//        }
//
//        // Flip sprite when going right.
//        if(direction == Direction.RIGHT){
//            enemySprite.setFlip(true, false);
//        }
//        else {
//            enemySprite.setFlip(false, false);
//        }

        // For y-axis:
        if (velocity.y < 0) {
            direction = Direction.DOWN;
        } else {
            direction = Direction.UP;
        }

        // Move and stop enemy:
        if (enemySprite.getY() > 130 ) {
//            switch (behavior){
//                case 1:{
//                    position.add(velocity);
//                    enemySprite.setY(position.y);
//                    // ZigZag
//                    enemySprite.translateX(((updateCount % (17*2) < 17) ? 6:-6));
//                    position.y = enemySprite.getY();
//                    rectangle.setPosition(position);
//                }
//                default:{
                    position.add(velocity);
                    enemySprite.setY(position.y);
                    rectangle.setPosition(position);
//                }
//            }
        }
        else {
            enemySprite.setY(position.y);
            rectangle.setPosition(position);

            if(TimeUtils.nanoTime() - prevtime > 1000000000) { // damages every second
                this.attackWall(wall); // Damage wall
                System.out.println("Wall health after taking damage: " + wall.getHealth());
//                this.hurt(this.getAttackPower());
//                System.out.println("--Enemy health after attacking wall: " + this.getCurrentHealth());
//                if(this.getCurrentHealth() == 0)
//                    this.die();
                prevtime = TimeUtils.nanoTime();
            }
        }

    }

    /**
     * Name of Module: dispose
     * Purpose: Cleans up resources used for enemy.
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