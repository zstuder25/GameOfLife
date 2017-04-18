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

    /**
     * Method to set the dimensions of the grid during object creation
     * @param in
     *      the scanner input for the grid text file
     */
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

    /**
     * Grid constructor that fills out the grid object based on the scanner input
     * @param gridFile
     *      the file containing the grid of cells as represented by '.' and 'O'
     * @throws FileNotFoundException
     *      error thrown if file directory isn't found
     */
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

    /**
     * Method to convert the current Grid object into a string representation to be printed
     * @return
     *      the string representation of the Grid object using '.' and 'O' characters
     */
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

    /**
     * Method to see if the given cell coordinates is within the bounds of the grid and alive
     * @param c
     *      the column index
     * @param r
     *      the row index
     * @return
     *      true if the combined indices is within the bounds of the grid and the cell is alive
     */
    private boolean isCellAlive(int c, int r){
        boolean isNotACell = (c < 0 || r < 0 || c > columns - 1 || r > rows - 1);
        return !isNotACell ? cells[c][r].isLifeStatus() : false;
    }


    /**
     * Method to set the next generation cell state based off the alive neighbors
     * @param cell
     *      the cell to have its nextGenLifeStatus set to true or false based off the
     *      rules of Conway's Game of Life using the number of neighborsAlive for each
     *      cell
     */
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

    /**
     * Evaluate a given cell with its indices to count how many of its neighbors are alive
     * and set the neighborsAlive value for the given cell
     * @param cell
     *      the cell to have its neighbors evaluated as alive or dead
     * @param c
     *      the column index for the cell
     * @param r
     *      the row index for the cell
     */
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

    /**
     * After all evaluation is complete this method is called to transfer the
     * grid over to the new state into the next generation by setting the
     * current life status to the next generation life status that has been
     * evaluated before this method call
     */
    private void processNextGeneration(){
        for(Cell[] cellCol : cells){
            for(Cell cell : cellCol){
                cell.setLifeStatus(cell.getNextGenLifeStatus());
            }
        }
    }

    /**
     * Iterate through each of the cells within the grid to evaluate the number
     * of neighbors it has alive and set its next generations life status. Once
     * completed, a call to process the next generation by updating the life status
     * of each of the cells
     */
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
                ",\n grid=\n" + printCells() +
                '}';
    }
}
