import exceptions.InvalidArgumentsException;
import objects.Grid;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by zmans on 4/15/2017.
 */
public class GameOfLife {

    public static void main(String[] args) throws FileNotFoundException{
        if(args.length > 1 || args.length < 1){
            throw new InvalidArgumentsException();
        }
        String gridFilePath = args[0];
        File gridFile = new File(gridFilePath);
        Grid grid = new Grid(gridFile);
    }
}
