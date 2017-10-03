package com.audition;

import com.audition.exceptions.InvalidArgumentsException;
import org.junit.Test;

import java.io.FileNotFoundException;


/**
 * Created by zmans on 10/1/2017.
 */
public class GameOfLifeTest {

    @Test(expected = InvalidArgumentsException.class)
    public void invalidArgumentExceptionNull() throws FileNotFoundException{
        String[] args = {};
        GameOfLife.main(args);
    }

    @Test(expected = InvalidArgumentsException.class)
    public void invalidArgumentExceptionMultiple() throws FileNotFoundException{
        String[] args = {"string1", "string2"};
        GameOfLife.main(args);
    }

    @Test(expected = FileNotFoundException.class)
    public void fileNotFound() throws FileNotFoundException{
        String[] args = {"notAFile"};
        GameOfLife.main(args);
    }

}
