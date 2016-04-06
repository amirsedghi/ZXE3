package com.mygdx.game;

import com.badlogic.gdx.Preferences;

import java.util.Arrays;

// GenericScoreboard
// Generic scoreboard implementation
// Uses simple arrays to implement scoreboard
// Data is saved using libgdx preferences
// By Jacob Poersch
// Created 3/21/16

public class GenericScoreboard implements Scoreboard {
    // Private members
    private ScoreField[] entries;
    private Preferences prefs;

    /*
     * Consturctor(s)
     * Takes in a libgdx prefs object for the purpose of saving scores
     * By Jacob
     * 3/21/16
    */
    public GenericScoreboard(Preferences prefs){
        this.prefs = prefs;
        // Create blank entries
        entries = new ScoreField[11];    // Leave 1 extra
        loadScores();
    }
    /* nameSbumittable
     * Returns tre if the scoreboard implemenetation can take a player name
     * By Jacob
     * 3/21/16
     */
    @Override
    public boolean nameSubmittable(){
        return true;
    }
    @Override
    /* Submit Score
     * Method called when submitting a score
     * Takes name and score and saves the score
     * Name would be null if implementation doesn't take a score
     * By Jacob
     * 3/21/16
     */
    public void submitScore (String name, int score){
        // Enter into the end of the array.
        entries[entries.length - 1] = new ScoreField(name, score);
        // Sort it
        Arrays.sort(entries);
        // End
        // Save
        saveScores();
    }
    /*
     * getScore
     * Called to get data about a particular score field
     * Takes in the index and returns a ScoreField class (listed below)
     * By Jacob
     * 3/21/16
     */
    @Override
    public ScoreField getScore (int index){
        return entries[index];
    }

    /*
     * ScoreField
     * Basic subclass to carry information about the player on a particular index on the scoreboard
     * By Jacob
     * 3/21/16
     */
    class ScoreField implements Scoreboard.ScoreField, Comparable<ScoreField>{
        // Fields for scorefield
        private String name;
        private int score;
        // Constructor
        // Takes in name and score
        private ScoreField(String name, int score) {
            this.name = name;
            this.score = score;
        }
        // Getters defined in the interface
        @Override
        public String getName(){
            return name;
        }
        @Override
        public int getScoreNum(){
            return score;
        }
        /*
         * compareTo
         * This is why we implement Comparable
         * when Arrays.sort is called a ScoreField type, it orders from high to low by the score
         * By Jacob
         * 3/21/16
         */
        @Override
        public int compareTo(ScoreField comparison){
            return comparison.getScoreNum() - this.score;
        }
    }

    /*
     * saveScores
     * Saves the score information
     * By Jacob
     * 3/21/16
     */
    private void saveScores(){
        if(prefs != null){
            for (int i = 0; i < 10; i++){
                prefs.putString("ScoreName"+i, entries[i].getName());
                prefs.putInteger("ScoreNum"+i, entries[i].getScoreNum());
            }
            prefs.flush();
        }
    }

    /*
     * Load scores
     * Load from the prefs file
     *
     */
    public void loadScores(){
        if(prefs != null){
            for (int i = 0; i < 10; i++){
                String loadedScoreName = prefs.getString("ScoreName"+i, "None");  // Default to none if no field
                int loadedScore = prefs.getInteger("ScoreNum"+i, 0);              // Default to 0
                entries[i] = new ScoreField(loadedScoreName, loadedScore);
            }
        }
    }
}
