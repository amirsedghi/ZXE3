package com.mygdx.game;

import com.badlogic.gdx.Preferences;

import java.util.Arrays;

// Generic scoreboard implementation
// Uses simple arrays to implement scoreboard
// Data is not saved once closed.

public class GenericScoreboard implements Scoreboard {
    // Private members
    private ScoreField[] entries;
    private Preferences prefs;

    // Consturctor(s)
    public GenericScoreboard(Preferences prefs){
        this.prefs = prefs;
        // Create blank entries
        entries = new ScoreField[11];    // Leave 1 extra
        /*for (int i = 0; i < entries.length; i++){
            entries[i] = new ScoreField("None", 0);
        }*/
        loadScores();
    }
    @Override
    public boolean nameSubmittable(){
        return true;
    }
    @Override
    public void submitScore (String name, int score){
        // Enter into the end of the array.
        entries[entries.length - 1] = new ScoreField(name, score);
        // Sort it
        Arrays.sort(entries);
        // End
        // Save
        saveScores();
    }
    @Override
    public ScoreField getScore (int index){
        return entries[index];
    }

    class ScoreField implements Scoreboard.ScoreField, Comparable<ScoreField>{
        // Fields for scorefield
        private String name;
        private int score;
        // Constructor
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
        // Comparator for Arrays.sort();
        @Override
        public int compareTo(ScoreField comparison){
            return comparison.getScoreNum() - this.score;
        }
    }

    private void saveScores(){
        if(prefs != null){
            for (int i = 0; i < 10; i++){
                prefs.putString("ScoreName"+i, entries[i].getName());
                prefs.putInteger("ScoreNum"+i, entries[i].getScoreNum());
            }
            prefs.flush();
        }
    }

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
