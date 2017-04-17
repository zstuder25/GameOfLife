package objects;

/**
 * Created by zmans on 4/15/2017.
 */
public class Cell {
    private boolean lifeStatus;

    public Cell(boolean lifeStatus){
        this.lifeStatus = lifeStatus;
    }

    public boolean isLifeStatus() {
        return lifeStatus;
    }

    public void setLifeStatus(boolean lifeStatus) {
        this.lifeStatus = lifeStatus;
    }
}
