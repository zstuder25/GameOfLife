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
        Grid grid = new Grid(new File("C:\\Users\\zmans\\IdeaProjects\\GameOfLife\\src\\test\\resources\\gridConstuctor.txt"));
        assertEquals(grid.getColumns(), 8);
        assertEquals(grid.getRows(), 6);
        assertTrue(grid.getCells()[2][1].isLifeStatus());
        assertTrue(!grid.getCells()[4][2].isLifeStatus());
    }

    @Test
    public void testPrintCells() throws FileNotFoundException{
        File file = new File("C:\\Users\\zmans\\IdeaProjects\\GameOfLife\\src\\test\\resources\\gridConstuctor.txt");
        String testFile = new Scanner(file).useDelimiter("\\Z").next().replaceAll("\\s", "");
        Grid grid = new Grid(file);
        assertEquals(testFile, grid.printCells().replaceAll("\\s",""));
    }

    @Test
    public void testNextGeneration()throws FileNotFoundException{
        Grid grid = new Grid(new File(":\\Users\\zmans\\IdeaProjects\\GameOfLife\\src\\test\\resources\\gridConstuctor.txt"));
        grid.nextGeneration();

    }
}
