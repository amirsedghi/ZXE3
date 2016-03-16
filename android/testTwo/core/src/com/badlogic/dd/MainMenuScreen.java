package com.badlogic.dd;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.*;
/**
 * Created by evolerup on 3/7/16.
 */
public class MainMenuScreen implements Screen{

    final DD game;

    OrthographicCamera camera;

    public MainMenuScreen(final DD gam){
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        

    }

    public void dispose(){

    }

    public void show(){

    }

    public void hide(){

    }

    public void pause(){

    }

    public void resume(){

    }


    public void resize(int width, int height){

    }

}
