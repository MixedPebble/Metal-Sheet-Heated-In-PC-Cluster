package pkg_safe;


import javax.swing.*;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {


    /*
    S: initial temperature of top left corner
    T: initial temperature of bottom right corner
    A: each of the 3 base metals
    C: thermal constant for metal A
    N: the set of neighbouring regions
    tempN:temperature of the neighbouring region
    pmn: percentage of metal A in neighbor n
    |N| number of neighboring regions

    C1: .75
    C2: 1.0
    C3: 1.25

     */
    public static void main(String args[]){
        /*
        The gridHeight SHOULD be a number divisible by the squareSize
        This will still work if it's not, it'll just be a bit off when displaying.
         */
        double s=20;
        double t=200;
        double cN[]= new double[3];
        cN[0]=.75;
        cN[1]=1.0;
        cN[2]=1.25;
        boolean converged= false;
        int iterations = 0;



        boolean isA=true;
        int squareSize = 10;
        int gridHeight=1000;
        int gridWidth=gridHeight*2;

        View v = new View(squareSize, gridWidth, gridHeight);
        Model A = new Model(squareSize, gridWidth, gridHeight, v,s,t);
        Model B = new Model(A);
        v.currentModel =A;

        JFrame f = new JFrame();

        f.setSize(gridWidth,gridHeight);
        f.add(v);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        v.paintComponent(v.getGraphics());

        Controller c = new Controller(A,A,B,0,0,0,0);
        ForkJoinPool pool = new ForkJoinPool();
        while (iterations <12500) {

            pool.invoke(c);
            //c.compute();
            if (isA==true){
                A.updateView();
                isA=false;
                c = new Controller(A,B,B,0,0,0,0);
            } else if (isA==false){
                B.updateView();
                isA=true;
                c = new Controller(A,A,B,0,0,0,0);

            }
            //converged = checkConvergence(A,B);
            iterations++;
        }
        if (isA==true){
            A.updateView();
        } else if (isA==false){
            B.updateView();
        }
    }
    static Boolean checkConvergence(Model modA, Model modB) {
        double diff = Math.abs(totalTemp(modB,modA));
        System.out.println(diff);
        if (diff < 0.001) {
            return true;
        }
        return false;
    }
    static double totalTemp(Model mod, Model mod2){
        double total=0;
        System.out.println("A "+mod.myCells[5][5].temperature);
        System.out.println("B "+mod2.myCells[5][5].temperature);
        for (int i = 0; i < mod.myCells.length; i++) {
            for (int j = 0; j < mod.myCells[0].length; j++) {
                total+= mod.myCells[i][j].temperature-mod2.myCells[i][j].temperature;
            }
        }
        return total;
    }
}
