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
        for (Cell col[]: cells) {
            for(Cell cell : col){
                if(cell.isLifeStatus()){
                    grid += "O";
                }else{
                    grid += ".";
                }
            }
            grid += "\n";
        }
        return grid;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}';
    }
}
