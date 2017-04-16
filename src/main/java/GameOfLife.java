import exceptions.InvalidArgumentsException;
import objects.Grid;

import java.io.File;

/**
 * Created by zmans on 4/15/2017.
 */
public class GameOfLife {

    public static void main(String[] args){
        if(args.length > 1 || args.length < 1){
            throw new InvalidArgumentsException();
        }
        String gridFilePath = args[0];
        File gridFile = new File(gridFilePath);
        Grid grid = new Grid(gridFile);
    }
}
