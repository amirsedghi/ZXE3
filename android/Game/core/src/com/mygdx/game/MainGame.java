package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Enemy;
import com.mygdx.game.GameConstants;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;

public class MainGame extends Game implements InputProcessor {
    private SpriteBatch batch;
    private Sprite enemySprite;
    private OrthographicCamera camera;
    public static Heliboy heliboy; // heliboy instance
    static Texture enemyTexture;
    public static Texture backgroundTexture;
    public static Sprite backSprite;
    static Array<Enemy> enemies; // array of enemies

    // Declare music object
    private Music mirage;

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

        enemyTexture =  new Texture(GameConstants.enemyImage);
//        // Instantiate and initalize the heliboy class
        heliboy = new Heliboy(screenWidth, screenHeight, enemyTexture);

        // load background texture
        backgroundTexture = new Texture(GameConstants.backgroundImage);
        // set background sprite with texture
        backSprite = new Sprite(backgroundTexture);
        backSprite.setSize(screenWidth, screenHeight);
        backSprite.setPosition(0, 0f);

        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);

        //Initialize and set input processor
        mirage = Gdx.audio.newMusic(Gdx.files.internal("mirage.mp3"));
    }
    @Override
    public void render() { //Called when the Application should render itself.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // set the spritebatch's drawing view to the camera's view
        batch.setProjectionMatrix(camera.combined);

        //Play streaming music
        mirage.play();
        mirage.setLooping(true);

//        for(Enemy enemy : enemies)
//            enemy.render(batch);

        // Spritebatch drawing must be made between the begin and end methods rendering the game objects:
        batch.begin();
        backSprite.draw(batch);
        heliboy.update();
        heliboy.render(batch);
        batch.end();
    }

//    public static void initEnemies()
//    {
//        enemies = new Array<Enemy>();
//        // instantiate new enemies and add it to the array
//        for (int i = 0; i < 3; i++) {
//            enemies.add(new Enemy());
//        }
//    }

    @Override
    public void dispose()
    {
        // dispose of the textures to ensure no memory leaks
        batch.dispose();
        mirage.dispose();
        backgroundTexture.dispose();
        enemyTexture.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        mirage.pause();
    }

    @Override
    public void resume() {
        mirage.play();
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
