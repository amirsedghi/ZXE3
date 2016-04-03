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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.Color;

import java.awt.*;

/**
 * Created by ghost_000 on 3/7/2016.
 */
public class MainMenu extends Menu {
    // Global variables and objects

    // Buttons
    private TextButton startButton;
    private TextButton settingsButton;
    private TextButton exitButton;

    // Scoreboard shenanigans
    private String scoreName1;
    private BitmapFont scoreFont;

    public MainMenu(final MyGdxGame game){
        super(game);

        // Button are vertically spaced by 56 units
        // Buttons are 176x50
        // Mid point in 800x480 would be 355, 218

        // Create a Start button
        // Set style onto the start button object
        startButton = new TextButton("Start", simpleButtonStyle);
        // Position
        startButton.setPosition((800/2) - (176/2), (480/2) - ((50/2) - 56));
        // The listener for the button, to change screens
        startButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                game.setScreen(new GdxGameScreen2(game));
                dispose();
            }
        });

        // Create a settings button
        settingsButton = new TextButton("Settings", simpleButtonStyle);
        settingsButton.setPosition((800/2) - (176/2), (480/2) - (50/2));
        settingsButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                // TODO: Settings bs
                game.setScreen(new SettingsMenu(game));
                dispose();
            }
        });

        // Create an exit button
        exitButton = new TextButton("Exit", simpleButtonStyle);
        exitButton.setPosition((800/2) - (176/2), (480/2) - ((50/2) + 56));
        exitButton.addListener(new ChangeListener(){
           @Override
            public void changed(ChangeEvent event, Actor actor){
               dispose();
               Gdx.app.exit();  // Close the application
           }
        });

        // Add any buttons to the stage
        stage.addActor(startButton);
        stage.addActor(settingsButton);
        stage.addActor(exitButton);
    }
    // In the event of a score being submitted, this constructor is called
    public MainMenu(final MyGdxGame game, int score){
        this(game); // Call main constructor
        // TODO: Do actual scoreboard stuff
        game.scoreboard.submitScore("Foo Score", score);
    }

    // Render method thing, just in case
    @Override
    public void render(float delta){
        super.render(delta);
    }

    @Override
    public void show(){
        super.show();
        // Temporarily show scoreboard like this. This is always run after the constructor
        System.out.printf("Scores: \n");
        for (int i = 0; i < 10; i++){
            System.out.printf("%s %d\n", game.scoreboard.getScore(i).getName(),
                    game.scoreboard.getScore(i).getScoreNum());
        }
        System.out.printf("\n");
    }
}
