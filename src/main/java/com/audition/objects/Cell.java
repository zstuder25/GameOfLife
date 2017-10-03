package com.audition.objects;

/**
 * Created by zmans on 4/15/2017.
 */
public class Cell {
    private boolean lifeStatus;
    private int neighborsAlive;
    private boolean nextGenLifeStatus;

    public Cell(boolean lifeStatus){
        this.lifeStatus = lifeStatus;
    }

    public boolean isLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(boolean lifeStatus) {
        this.lifeStatus = lifeStatus;
    }

    public int getNeighborsAlive() {
        return neighborsAlive;
    }

    public void setNeighborsAlive(int neighborsAlive) {
        this.neighborsAlive = neighborsAlive;
    }

    public boolean getNextGenLifeStatus() {
        return nextGenLifeStatus;
    }

    public void setNextGenLifeStatus(boolean nextGenLifeStatus) {
        this.nextGenLifeStatus = nextGenLifeStatus;
    }
}
