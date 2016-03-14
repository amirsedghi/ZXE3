package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.sun.jmx.snmp.SnmpTimeticks;

import java.util.Iterator;

public class GdxGameScreen2 implements Screen {
//	SpriteBatch batch;
//	Texture img;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("ehmm.jpg");
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
//	}

    private	Texture dropImage;
    private Texture bucketImage;
    private Sound dropSound;
    private Music rainMusic;
    private OrthographicCamera camera;
    //private SpriteBatch batch;
    private Rectangle bucket;
    private Array<Rectangle> raindrops;
    private long lastDropTime;
    private final MyGdxGame game;

    // Score stuff
    private int score;
    BitmapFont scoreFont;
    private String scoreText;

    public GdxGameScreen2(final MyGdxGame game){
        this.game = game;
        this.camera = game.camera;

        // Score
        score = 0;
        scoreFont = new BitmapFont();
        scoreText = "Score: 0";

        // load the image for the droplet and the buckt
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        // load the droplet sound effect
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

        // start the palyback of the background music immediately
        rainMusic.setLooping(true);
        rainMusic.play();

        // instantiating the rectangle
        bucket = new Rectangle();
        bucket.x = 800/2 - 64/2;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        // spawn raindrop
        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }


    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // updating the camera
        camera.update();

        // render the bucket
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(bucketImage, bucket.x, bucket.y);
        for (Rectangle raindrop: raindrops){
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        // Draw score
        scoreFont.draw(game.batch, scoreText, 50,50);
        game.batch.end();

        // adding bucket movement based on touch
        if(Gdx.input.isTouched()){
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64/2;
        }

        // adding keyboard functionality, left key should move left and right key should move to the right
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            bucket.x += 200 * Gdx.graphics.getDeltaTime();
        }

        // adjusting the limit of the bucket
        if(bucket.x < 0){
            bucket.x = 0;
        }

        if(bucket.x > 800 - 64){
            bucket.x = 800 - 64;
        }

        // check how much time has passed since we spawned a new raidrop
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000){
            spawnRaindrop();
        }

        // moving raindrop down the screen
        Iterator<Rectangle> iter = raindrops.iterator();
        while(iter.hasNext()){
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0){
                iter.remove();
            }
            if(raindrop.overlaps(bucket)){
                dropSound.play();
                iter.remove();
                score++;
                scoreText = "Score: " + score;
            }
        }


    }
    @Override
    public void show(){
        //
    }
    @Override
    public void hide(){
        //
    }
    @Override
    public void pause(){
        //
    }
    @Override
    public void resume(){
        //
    }
    @Override
    public void resize(int width, int height) {
        //
    }


    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void	dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
        game.batch.dispose();
    }
}
