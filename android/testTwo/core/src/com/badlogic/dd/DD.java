package com.badlogic.dd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import javax.swing.text.GapContent;

/**
 * Created by evolerup on 3/7/16.
 */
public class DD extends Game{

    public SpriteBatch batch;
    public BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        //Use LibGDX's default
        font = new BitmapFont();
        this.setScreen(new GameScreen(this));
    }

    public void render(){
        super.render();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
