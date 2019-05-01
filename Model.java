package pkg_safe;


import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class Model implements Serializable{
    String name;
    double ThermalConstant[];
    int width;
    int height;
    int squareSize;
    Cell[][] myCells;
    View v;
    double tLTemp;
    double lRTemp;
    double maxTemp;

    public Model(Model b){
        name = "B";
        tLTemp = b.tLTemp;
        lRTemp = b.lRTemp;
        maxTemp = b.maxTemp;
        ThermalConstant = new double[3];
        ThermalConstant[0]=b.ThermalConstant[0];
        ThermalConstant[1]=b.ThermalConstant[1];
        ThermalConstant[2]=b.ThermalConstant[2];
        width = b.width;
        height = b.height;
        v = b.v;
        squareSize = b.squareSize;
        myCells = new Cell[b.myCells.length][b.myCells[0].length];
        width = b.myCells.length;
        height = b.myCells[0].length;
        for (int i = 0;i<b.myCells.length;i++){
            for (int j = 0; j <b.myCells[0].length;j++){
                myCells[i][j]= new Cell(b.myCells[i][j]);
            }
        }
    }

    public Model(int s, int w, int h, View v, double lT, double hT){
        name = "A";
        tLTemp = lT;
        lRTemp = hT;
        if(tLTemp>lRTemp){
            maxTemp = tLTemp;
        } else {
            maxTemp = lRTemp;
        }
        ThermalConstant = new double[3];
        ThermalConstant[0]=.75;
        ThermalConstant[1]=1.0;
        ThermalConstant[2]=1.25;
        this.v = v;
        squareSize = s;
        myCells = new Cell[w/10][h/10];
        width = myCells.length;
        height = myCells[0].length;

        for (int i = 0;i<myCells.length;i++){
            for (int j = 0; j <myCells[0].length;j++){
                myCells[i][j]= new Cell();
            }
        }
        myCells[0][0].temperature= tLTemp;
        myCells[myCells.length-1][myCells[0].length-1].temperature= lRTemp;
    }

    public void updateView() {
        v.currentModel=this;
        v.repaint();
    }

    public class Cell {
        double metalPercent[];
        double temperature;
        public Cell(Cell c){
            temperature = c.temperature;
            metalPercent = new double[3];
            metalPercent[0]= c.metalPercent[0];
            metalPercent[1]= c.metalPercent[1];
            metalPercent[2] = c.metalPercent[2];
        }
        public Cell(){
            double c1,c2,c3,total;
            c1 = 100+ThreadLocalRandom.current().nextInt(-13,12);
            c2 = 100+ThreadLocalRandom.current().nextInt(-13,12);
            c3 = 100+ThreadLocalRandom.current().nextInt(-13,12);

            total = c1+c2+c3;
            c1 =c1/total;
            c2 =c2/total;
            c3 = 1-c1-c2;

            temperature = 1;
            metalPercent = new double[3];
            metalPercent[0]= c1;
            metalPercent[1]= c2;
            metalPercent[2] = c3;

        }
    }

}
