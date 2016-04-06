package com.badlogic.dd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.swing.text.GapContent;

/**
 * Created by evolerup on 3/7/16.
 */
public class DD extends Game{

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public BitmapFont font;

    public Preferences prefs;
    public double screenMultiplier;
    protected Scoreboard scoreboard;

    // Constructor
    public DD(){
        this(null); // Call with null if no scoreboard specified
    }

    public DD(Scoreboard scoreboard){
        this.scoreboard = scoreboard;
    }

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default
        font = new BitmapFont();
        // Universal camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        prefs = Gdx.app.getPreferences("DD.prefs");
        if(scoreboard == null){
            // If no scorebaord implementation for platform, use genric
            scoreboard = new GenericScoreboard(prefs);
        }
        screenMultiplier = (double)prefs.getFloat("Screen Multiplier", 1);
        Gdx.graphics.setWindowedMode((int)(800 * screenMultiplier), (int)(480 * screenMultiplier));

        this.setScreen(new MainMenu(this));
    }

    public void render(){
        super.render();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
