package com.mygdx.game;

import com.badlogic.gdx.Application;
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

/*
 * SettingsMenu
 * A settings menu screen
 * For changing game settings
 * By Jacob
 * 3/21/16
 */

public class SettingsMenu extends Menu {
    // Global variables and objects

    // Buttons
    private TextButton backButton;
    private TextButton testButton;

    /*
     * Constructors
     * Takes in game ApplicationAdapter
     * By Jacob
     * 3/21/16
     */
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

        // Test
        testButton = new TextButton(String.format("Screen Size x%.2f", game.screenMultiplier), simpleButtonStyle);
        testButton.setPosition((800/2) - (176/2), (480/2) - ((50/2) + 56));
        testButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.screenMultiplier += 0.25;
                if(game.screenMultiplier > 2){
                    game.screenMultiplier = 1;
                }
                Gdx.graphics.setWindowedMode((int)(800 * game.screenMultiplier), (int)(480 * game.screenMultiplier));
                testButton.setText(String.format("Screen Size x%.2f", game.screenMultiplier));
                game.prefs.putFloat("Screen Multiplier", (float)game.screenMultiplier);
                game.prefs.flush();
            }
        });

        // Add button actors
        stage.addActor(backButton);
        // Only add this button if this is desktop
        if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
            stage.addActor(testButton);
        }
    }

    // Render method thing, may or may not use it
    @Override
    public void render(float delta){
        super.render(delta);
    }
}
