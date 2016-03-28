package com.badlogic.dd;

import java.beans.VetoableChangeListener;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
/**
 * Created by evolerup on 3/7/16.
 */
public class GameScreen implements Screen {
    final DD game;






    public GameScreen(final DD gam){
        this.game = gam;


    }

    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);




    }

    public void resize(int width, int height){

    }

    public void show(){

    }

    public void hide(){

    }

    public void pause(){

    }

    public void resume(){

    }

    public void dispose(){

    }
}
