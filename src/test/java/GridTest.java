import objects.Grid;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by zmans on 4/15/2017.
 */
public class GridTest {

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
