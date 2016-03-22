package com.mygdx.game;

/**
 * Created by ghost_000 on 3/21/2016.
 */
public interface Scoreboard {
    // Return true if scoreboard implementation allows for a name submission
    boolean nameSubmittable();
    // Submits the score
    // Use null if we're not going to take names
    void submitScore(String name, int score);
    // Get a requested scorefield
    // Returns null if implementation is external
    ScoreField getScore(int index);

    // Simple object for storing a scorefiled
    interface ScoreField{
        String getName();
        int getScoreNum();
    }
}
