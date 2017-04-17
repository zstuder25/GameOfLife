package objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Created by zmans on 4/15/2017.
 */
public class Grid {
    private int columns = 0;
    private int rows = 0;
    private static Pattern pattern = Pattern.compile("[.O]*");
    private static Cell[][] cells;

    private void setDimensions(Scanner in){
        //Initially getting dimensions for an arbitrary grid size
        this.columns = in.next(pattern).length();
        rows = 1;
        while(in.hasNext(pattern)){
            this.rows++;
            in.next(pattern);
        }
        cells = new Cell[columns][rows];
    }

    public Grid(File gridFile) throws FileNotFoundException{
        setDimensions(new Scanner(gridFile));
        Scanner in = new Scanner(gridFile);
        int c = 0;
        int r = 0;
        while(in.hasNext(pattern)){
            String row = in.next(pattern);
            for(char ch: row.toCharArray()){
                if(ch == 'O'){
                    cells[c][r] = new Cell(true);
                }else{
                    cells[c][r] = new Cell(false);
                }
                c++;
            }
            c = 0;
            r++;
        }
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public static void setCells(Cell[][] cells) {
        Grid.cells = cells;
    }

    public String printCells(){
        String grid = "";
        for (int j = 0; j < rows; j++) {
            for(int i = 0; i < columns; i++){
                if(cells[i][j].isLifeStatus()){
                    grid += "O";
                }else{
                    grid += ".";
                }
            }
            grid += "\n";
        }
        return grid;
    }

    private boolean isCellAlive(int c, int r){
        boolean isNotACell = (c < 0 || r < 0 || c > columns - 1 || r > rows - 1);
        return !isNotACell ? cells[c][r].isLifeStatus() : false;
    }

    private void setNextCellState(Cell cell){
        int neighborsAlive = cell.getNeighborsAlive();
        if(cell.isLifeStatus()){
            //For Live Cells
            if(neighborsAlive < 2 || neighborsAlive > 3){
                cell.setNextGenLifeStatus(false);
            }else{
                cell.setNextGenLifeStatus(true);
            }
        }else{
            //For Dead Cells
            if(neighborsAlive == 3){
                cell.setNextGenLifeStatus(true);
            }
        }
    }

    private void evaluateCell(Cell cell, int c, int r){
        int neighborsAlive = 0;
        if(isCellAlive(c -1, r -1)){
            neighborsAlive++;
        }
        if(isCellAlive(c, r -1)){
            neighborsAlive++;
        }
        if(isCellAlive(c + 1, r -1)){
            neighborsAlive++;
        }
        if(isCellAlive(c + 1, r)){
            neighborsAlive++;
        }
        if(isCellAlive(c + 1, r + 1)){
            neighborsAlive++;
        }
        if(isCellAlive(c, r + 1)){
            neighborsAlive++;
        }
        if(isCellAlive(c - 1, r + 1)){
            neighborsAlive++;
        }
        if(isCellAlive(c - 1, r)){
            neighborsAlive++;
        }
        cell.setNeighborsAlive(neighborsAlive);
        setNextCellState(cell);
    }

    private void processNextGeneration(){
        for(Cell[] cellCol : cells){
            for(Cell cell : cellCol){
                cell.setLifeStatus(cell.getNextGenLifeStatus());
            }
        }
    }

    public void nextGeneration(){
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < columns; i++) {
                evaluateCell(cells[i][j], i, j);
            }
        }
        processNextGeneration();
    }

    @Override
    public String toString() {
        return "Grid{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}';
    }
}
