package com.audition.objects;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by zmans on 4/15/2017.
 */
public class GridTest {

    private Grid testGrid;

    @Before
    public void setup() throws FileNotFoundException{
        testGrid = new Grid(new File("src\\test\\resources\\grid1.txt"));
    }

    private boolean isCellAlive(int c, int r){
        boolean isNotACell = (c < 0 || r < 0 || c > testGrid.getColumns() - 1 || r > testGrid.getRows() - 1);
        return !isNotACell ? testGrid.getCells()[c][r].isLifeStatus() : false;
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
            }else{
                cell.setNextGenLifeStatus(false);
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

    @Test
    public void isCellAliveTest(){
        int aliveC = 6;
        int aliveR = 0;
        assertTrue(isCellAlive(aliveC, aliveR));

        int deadC = 2;
        int deadR = 3;
        assertFalse(isCellAlive(deadC, deadR));
    }

    @Test
    public void setNextCellTest(){
        Cell deadCell = new Cell(false);
        Cell aliveCell = new Cell(true);

        deadCell.setNeighborsAlive(3);
        setNextCellState(deadCell);
        assertTrue(deadCell.getNextGenLifeStatus());

        deadCell.setNeighborsAlive(2);
        setNextCellState(deadCell);
        assertFalse(deadCell.getNextGenLifeStatus());

        deadCell.setNeighborsAlive(4);
        setNextCellState(deadCell);
        assertFalse(deadCell.getNextGenLifeStatus());

        aliveCell.setNeighborsAlive(1);
        setNextCellState(aliveCell);
        assertFalse(aliveCell.getNextGenLifeStatus());

        aliveCell.setNeighborsAlive(4);
        setNextCellState(aliveCell);
        assertFalse(aliveCell.getNextGenLifeStatus());

        aliveCell.setNeighborsAlive(3);
        setNextCellState(aliveCell);
        assertTrue(aliveCell.getNextGenLifeStatus());
    }

    @Test
    public void testEvaluateCell(){
        Cell aliveThenDeadCell = new Cell(true);
        evaluateCell(aliveThenDeadCell, 0, 1);
        assertFalse(aliveThenDeadCell.getNextGenLifeStatus());

        Cell aliveThenAliveCell = new Cell(true);
        evaluateCell(aliveThenAliveCell, 1, 1);
        assertTrue(aliveThenAliveCell.getNextGenLifeStatus());

        Cell deadThenAliveCell = new Cell(false);
        evaluateCell(deadThenAliveCell, 1, 0);
        assertTrue(deadThenAliveCell.getNextGenLifeStatus());

        Cell deadThenDeadCell = new Cell(false);
        evaluateCell(deadThenDeadCell, 7, 0);
    }

    @Test
    public void testGridConstructor() throws FileNotFoundException{
        Grid grid = new Grid(new File("src\\test\\resources\\grid1.txt"));
        assertEquals(grid.getColumns(), 8);
        assertEquals(grid.getRows(), 6);
        assertTrue(grid.getCells()[2][1].isLifeStatus());
        assertTrue(!grid.getCells()[4][2].isLifeStatus());
    }

    @Test
    public void testPrintCells() throws FileNotFoundException{
        File file = new File("src\\test\\resources\\grid1.txt");
        String testFile = new Scanner(file).useDelimiter("\\Z").next().replaceAll("\\s", "");
        Grid grid = new Grid(file);
        assertEquals(testFile, grid.printCells().replaceAll("\\s",""));
    }

    @Test
    public void testNextGeneration()throws FileNotFoundException{
        Grid grid = new Grid(new File("src\\test\\resources\\grid1.txt"));
        grid.nextGeneration();
        String nextGen = new Scanner(new File("src\\test\\resources\\grid1NextGen.txt"))
                .useDelimiter("\\Z").next().replaceAll("\\s", "");
        assertEquals(nextGen, grid.printCells().replaceAll("\\s", ""));
    }

    @Test
    public void testOversizeGrids() throws FileNotFoundException{
        //Wide Grid test
        Grid wideGrid = new Grid(new File("src\\test\\resources\\extraWideGrid.txt"));
        wideGrid.nextGeneration();
        assertEquals(wideGrid.getRows(), 15);
        assertEquals(wideGrid.getColumns(), 75);
        assertTrue(!wideGrid.getCells()[55][13].isLifeStatus());

        //Long Grid test
        Grid longGrid = new Grid(new File("src\\test\\resources\\extraLongGrid.txt"));
        longGrid.nextGeneration();
        assertEquals(longGrid.getRows(), 29);
        assertEquals(longGrid.getColumns(), 8);
        assertTrue(!longGrid.getCells()[5][21].isLifeStatus());

        //Large Grid test
        Grid largeGrid = new Grid(new File("src\\test\\resources\\extraLargeGrid.txt"));
        largeGrid.nextGeneration();
        assertEquals(largeGrid.getRows(), 38);
        assertEquals(largeGrid.getColumns(), 44);
        assertTrue(largeGrid.getCells()[41][30].isLifeStatus());
    }
}
