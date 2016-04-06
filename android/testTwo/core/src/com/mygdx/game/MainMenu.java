package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

import java.awt.*;

/*
 * MainMenu screen
 * This is the very first screen seen when the game starts up.
 * By Jacob
 * 3/21/16
 */

public class MainMenu extends Menu {
    // Global variables and objects

    // Buttons
    private TextButton startButton;
    private TextButton settingsButton;
    private TextButton exitButton;
    private TextButton scoreboardButton;

    // Scoreboard shenanigans
    private String scoreName1;
    private BitmapFont scoreFont;
    private String highScoreName;

    /*
     * Constructor
     * Takes necessary game type
     * Calls abstract constructor as well as creating all buttons
     * Positioning them and such
     * By Jacob
     * 3/21/16
     */
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

        // Create Scoreboard button
        scoreboardButton = new TextButton("Scoreboard", simpleButtonStyle);
        scoreboardButton.setPosition((800/2) - (176/2), (480/2) - ((50/2) + 112));
        scoreboardButton.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor){
                showScoreBoardUpdate();
            }
        });

        // Add any buttons to the stage
        stage.addActor(startButton);
        stage.addActor(settingsButton);
        stage.addActor(exitButton);
        stage.addActor(scoreboardButton);
    }
    /*
     * Alternative constructor
     * If a score is submitted, this one is called in order to do the score submission process
     * By Jacob
     * 3/21/16
     */
    public MainMenu(final MyGdxGame game, final int score){
        this(game); // Call main constructor
        // TODO: Do actual scoreboard stuff

        if(game.scoreboard.nameSubmittable()) {
            highScoreName = null;
            Gdx.input.getTextInput(new Input.TextInputListener() {
                @Override
                public void input(String text) {
                    game.scoreboard.submitScore(text, score);
                    //showScoreBoardUpdate();
                }

                @Override
                public void canceled() {
                    // Do nothing
                }

            }, "High Score", "Input your name", "");
        }
    }

    // Render method thing, just in case
    @Override
    public void render(float delta){
        super.render(delta);
    }

    /* showScoreBoardUpdate
     * Pulls the data from the scoreboard, and then displays it in a window
     */
    public void showScoreBoardUpdate(){
        // Put the printout into a big string
        String scoreboardString = "";
        for(int i = 0; i < 10; i++){
            scoreboardString += String.format("%s %10d\n", game.scoreboard.getScore(i).getName(),
                    game.scoreboard.getScore(i).getScoreNum());
        }
        // Try showing a dialogue box
        Dialog scoreboardDiag = new Dialog("Scoreboard", new Skin(Gdx.files.internal("uiskin.json")), "dialog") {
            public void result(Object obj) {
                //
            }
        };
        scoreboardDiag.text(scoreboardString);
        scoreboardDiag.button("Ok");
        scoreboardDiag.show(stage);
    }
}
