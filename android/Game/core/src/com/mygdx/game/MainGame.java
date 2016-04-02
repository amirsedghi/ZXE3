package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Enemy;
import com.mygdx.game.GameConstants;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;

/* To do list:
    High Priority:
    1. Replace rectangles with circles for enemy collision.
    2. Interface enemies with wall
        2.1 Enemy collison with wall
        2.2 Enemy attack wall
            Call attack method
    3. Interface enemies with cannon/projectile
        3.1 Call die method for enemies when health reaches 0.

    Lower Priority:
        2.3 Enemy stops at wall
    4. Animations for Enemies
        4.1 Walking Animation
        4.2 Attack Animation
        4.3 Death Animation
    5. Sounds for enemies:
        5.1 Attack Sound
        5.2 Footstep Sound
        5.3 Death Sound
*/

public class MainGame extends Game implements InputProcessor {

    // Variables:
    private SpriteBatch batch;
    private Sprite enemySprite;
    private OrthographicCamera camera;
    private long lastSpawnTime;
    public Skeleton skeleton; // skeleton instance
    public Texture enemyTexture;
    public TextureRegion textureRegion;
    public static Texture backgroundTexture;
    public static Sprite backSprite;
    // public Array<Skeleton> skeletons; // array of enemies
    // ArrayList skeletons = new ArrayList();
    ListIterator<Skeleton> iterEn = null;
    List<Skeleton> skeletons = new ArrayList<Skeleton>();
    Iterator<Skeleton> iterEn;
    TextureAtlas textureAtlas;
    private int xcordSpawn, skeletonArraySize = 0;
    ShapeRenderer rect;
    Color red = Color.RED;

    // Declare music object
    private Music music;

    //Screen dimensions/ Viewport Width and Height
    private float screenWidth, screenHeight;

    @Override
    public void create() //Called when the Application is first created.
    {
        //Set screen dimensions to window dimensions.
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        // instantiate the camera and set the viewport
        camera = new OrthographicCamera(screenWidth, screenHeight);

        // center the camera at screenWidth/2, and screenHeight/2
        camera.setToOrtho(false, screenWidth, screenHeight);

        textureAtlas = new TextureAtlas(Gdx.files.internal(GameConstants.skeletonSpriteSheet));
        textureRegion = textureAtlas.findRegion("go", 1);
        enemyTexture =  new Texture(GameConstants.enemyImage);

        // Instantiate and initalize the skeleton class
        //skeletons = new Array<Skeleton>();
        spawnSkeleton();
        skeletonArraySize++;

        // load background texture
        backgroundTexture = new Texture(GameConstants.backgroundImage);
        // set background sprite with texture
        backSprite = new Sprite(backgroundTexture);
        backSprite.setSize(screenWidth, screenHeight);
        backSprite.setPosition(0, 0f);

        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);

        //Initialize and set input processor
        music = Gdx.audio.newMusic(Gdx.files.internal(GameConstants.music));
    }
    @Override
    public void render() { //Called when the Application should render itself.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set the spritebatch's drawing view to the camera's view
        batch.setProjectionMatrix(camera.combined);

        //Play streaming music
        music.play();
        music.setLooping(true);

        // Spritebatch drawing must be made between the begin and end methods rendering the game objects:
        batch.begin();
        backSprite.draw(batch);
//        rect.begin(ShapeRenderer.ShapeType.Filled);
//        rect.setColor(red);
//        rect.rect(100, 100, 20, 20);
//        rect.end();
        for(Skeleton skeleton : skeletons)
        {
            skeleton.update();
            skeleton.render(batch);
//            if(skeleton.isOutOfBounds())
//            {
//                skeletons.remove()
//            }
        }
        batch.end();

        // check how much time has passed since we spawned a new skeleton
        if(TimeUtils.nanoTime() - lastSpawnTime > 1000000000)
        {
            if(skeletons.size > 15)
            {
                ;
            }
            else
                spawnSkeleton();
        }

        while(iterEn.hasNext())
        {
            Skeleton e = iterEn.next();
            if (e.isOutOfBounds() )
            {
                iterEn.remove();
            }
            if (e.getRectangle().overlaps(e.getRectangle()))
            {
                iterEn.remove();
            }
        }

//        if(skeletons.size > 20)
//        {
//            removeSkeletons();
//        }

//        if(skeletonArraySize > 20)
//        {
//            removeSkeletons();
//            skeletonArraySize = 0;
//        }
    }

        private void spawnSkeleton()
    {   // instantiate new enemy and add it to the array
            xcordSpawn = MathUtils.random((int)screenWidth);
            skeleton = new Skeleton(screenWidth, screenHeight, textureRegion, xcordSpawn);
            skeletons.add(skeleton);
            skeletonArraySize++;
            lastSpawnTime = TimeUtils.nanoTime();
            iterEn = skeletons.iterator();
    }

        // Get rid of all the skeletons.
        private void removeSkeletons()
    {
        skeletons.clear();
    }

//    public static void initEnemies()
//    {
//        skeletons = new Array<Enemy>();
//        // instantiate new enemies and add it to the array
//        for (int i = 0; i < 3; i++) {
//            enemies.add(new Enemy());
//        }
//    }

    @Override
    public void dispose()
    {   // dispose of the textures to ensure no memory leaks
        batch.dispose();
        music.dispose();
        backgroundTexture.dispose();
        enemyTexture.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    //Return true to indicate that the event was handled
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    //When finger is lifted up
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    //While dragging finger across screen
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}