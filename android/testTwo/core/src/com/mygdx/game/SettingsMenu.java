package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SettingsMenu extends Menu {
    // Global variables and objects

    // Buttons
    private TextButton backButton;

    // Constuctor(s)
    public SettingsMenu(final MyGdxGame game){
        super(game);

        // Buttons
        // Back button
        backButton = new TextButton("Back", simpleButtonStyle);
        backButton.setPosition((800/2) - (176/2), (480/2) - (50/2));
        backButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new MainMenu(game));
                dispose();
            }
        });

        // Add button actors
        stage.addActor(backButton);
    }

    // Render method thing, just in case
    @Override
    public void render(float delta){
        super.render(delta);
    }
}
