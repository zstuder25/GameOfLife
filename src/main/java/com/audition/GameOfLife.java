package com.audition;

import com.audition.exceptions.InvalidArgumentsException;
import com.audition.objects.Grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by zmans on 4/15/2017.
 */
public class GameOfLife {

    private static void nextGeneration(Grid grid){
        System.out.println("Would you like to run the next generation?: Y/N");
        Scanner in = new Scanner(System.in);
        String cin = in.next();
        while(!cin.equals("N") && !cin.equals("n")){
            if(cin.equals("Y") || cin.equals("y")){
                generateAndPrint(grid);
            }else{
                System.out.println("Invalid Input: Please Enter only 'Y' or 'N'");
            }
            System.out.println("Would you like to run the next generation?: Y/N");
            cin = in.next();
        }
    }

    private static void generateAndPrint(Grid grid){
        System.out.println("Current Generation:");
        System.out.println(grid.printCells());
        grid.nextGeneration();
        System.out.println("Next Generation:");
        System.out.println(grid.printCells());
    }

    private static void processInput(String[] args) throws FileNotFoundException{
        if(args.length > 1 || args.length < 1){
            throw new InvalidArgumentsException();
        }
        String gridFilePath = args[0];
        File gridFile = new File(gridFilePath);
        Grid grid = new Grid(gridFile);
        generateAndPrint(grid);

        nextGeneration(grid);
    }

    public static void main(String[] args) throws FileNotFoundException{
        processInput(args);
    }
}
