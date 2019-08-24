package br.ufsc.game.engine.config;

/**
 * GameSettings
 */
public class GameSettings {

    private String title;
    private double gWidth;
    private double gHeight;
    private int tickRate;

    public GameSettings(String title, double width, double height,int tickRate) {
        this.title = title;
        this.gWidth = width;
        this.gHeight = height;
        this.tickRate = tickRate;
    }

    public double getHeight() {
        return gHeight;
    }

    public double getWidth() {
        return gWidth;
    }

    public String getTitle() {
        return title;
    }

    public int getTickRate() {
        return tickRate;
    }

}