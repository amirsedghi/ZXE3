package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public Preferences prefs;
	public double screenMultiplier;

	protected Scoreboard scoreboard;

	// Constructors
	public MyGdxGame(){
		// Use generic scoreboard
		this(null);
	}
	public MyGdxGame(Scoreboard scoreboard){
		this.scoreboard = scoreboard;
	}

	public void create(){
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		this.setScreen(new MainMenu(this));
		prefs = Gdx.app.getPreferences("ZXE3.prefs");
		if(scoreboard == null){
			scoreboard = new GenericScoreboard(prefs);
		}
		screenMultiplier = (double)prefs.getFloat("Screen Multiplier", 1);
		Gdx.graphics.setWindowedMode((int)(800 * screenMultiplier), (int)(480 * screenMultiplier));
	}
	public void render(){
		super.render();
	}
	public void dispose(){
		batch.dispose();
	}
}