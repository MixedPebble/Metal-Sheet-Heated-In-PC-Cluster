package pkg_safe;


import java.util.concurrent.RecursiveAction;

public class Controller extends RecursiveAction {
    Model A;
    Model B;
    int row, y, height, width;
    private boolean solutionFound=false;
    int iterations = 0;
    Model baseModel;
    Controller upperLeft, upperRight, bottomLeft, bottomRight;

    public Controller(Model a, Model current, Model b, int row, int y, int width, int height){
        A = a;
        B = b;
        baseModel = current;
        //B = new Model(A);
        this.row = row;
        this.y = y;
        this.height = height;
        this.width = width;
        baseModel =current;

    }

    public void compute(){
        /*
        Controller:
        The controller is designed to divide a Model into multiple subsections of a model and perform the same
        algorithm on each of those subsections. It will also sub-divide the problem if it is too big.
        The row and column variables are so the algorithm knows where to start when subdividing the task
         */
        double newTemp;
        double temp;

        if (height <=A.myCells.length) {

            for (int i = row; i < row + baseModel.width; i++) { //width
                for (int j = y; j < y+ baseModel.height; j++) { //height
                    temp = 0;
                    for (int k = 0; k < 3; k++) {
                        newTemp = 0;
                        if (i == 0 && j == 0) {
                            //top left corner
                            newTemp = (newTemp + baseModel.myCells[i + 1][j].temperature * baseModel.myCells[i + 1][j].metalPercent[k] + baseModel.myCells[i][j + 1].temperature * baseModel.myCells[i][j + 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 3;
                        } else if (i == baseModel.width - 1 && j == 0) {
                            //top right corner
                            newTemp = (newTemp + baseModel.myCells[i - 1][j].temperature * baseModel.myCells[i - 1][j].metalPercent[k] + baseModel.myCells[i][j + 1].temperature * baseModel.myCells[i][j + 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 3;
                        } else if (i == 0 && j == baseModel.height - 1) {
                            //bottom left corner
                            newTemp = (newTemp + baseModel.myCells[i + 1][j].temperature * baseModel.myCells[i + 1][j].metalPercent[k] + baseModel.myCells[i][j - 1].temperature * baseModel.myCells[i][j - 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 3;
                        } else if (i == baseModel.width - 1 && j == baseModel.height - 1) {
                            //bottom right corner
                            newTemp = (newTemp + baseModel.myCells[i - 1][j].temperature * baseModel.myCells[i - 1][j].metalPercent[k] + baseModel.myCells[i][j - 1].temperature * baseModel.myCells[i][j - 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 3;
                        } else if (i == 0) {
                            //left wall
                            newTemp = (newTemp + baseModel.myCells[i + 1][j].temperature * baseModel.myCells[i + 1][j].metalPercent[k] + baseModel.myCells[i][j - 1].temperature * baseModel.myCells[i][j - 1].metalPercent[k] + baseModel.myCells[i][j + 1].temperature * baseModel.myCells[i][j + 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 4;
                        } else if (i == baseModel.width - 1) {
                            //right wall
                            newTemp = (newTemp + baseModel.myCells[i - 1][j].temperature * baseModel.myCells[i - 1][j].metalPercent[k] + baseModel.myCells[i][j - 1].temperature * baseModel.myCells[i][j - 1].metalPercent[k] + baseModel.myCells[i][j + 1].temperature * baseModel.myCells[i][j + 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 4;
                        } else if (j == 0) {
                            //ceiling
                            newTemp = (newTemp + baseModel.myCells[i - 1][j].temperature * baseModel.myCells[i - 1][j].metalPercent[k] + baseModel.myCells[i + 1][j].temperature * baseModel.myCells[i + 1][j].metalPercent[k] + baseModel.myCells[i][j + 1].temperature * baseModel.myCells[i][j + 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 4;
                        } else if (j == baseModel.height - 1) {
                            //floor
                            newTemp = (newTemp + baseModel.myCells[i - 1][j].temperature * baseModel.myCells[i - 1][j].metalPercent[k] + baseModel.myCells[i + 1][j].temperature * baseModel.myCells[i + 1][j].metalPercent[k] + baseModel.myCells[i][j - 1].temperature * baseModel.myCells[i][j - 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 4;
                        } else {
                            //middle area
                            newTemp = (newTemp + baseModel.myCells[i - 1][j].temperature * baseModel.myCells[i - 1][j].metalPercent[k] + baseModel.myCells[i + 1][j].temperature * baseModel.myCells[i + 1][j].metalPercent[k] + baseModel.myCells[i][j - 1].temperature * baseModel.myCells[i][j - 1].metalPercent[k] + baseModel.myCells[i][j + 1].temperature * baseModel.myCells[i][j + 1].metalPercent[k] + baseModel.myCells[i][j].temperature * baseModel.myCells[i][j].metalPercent[k])/ 5;

                        }
                        temp += + newTemp * baseModel.ThermalConstant[k];
                    }
                    if (A== baseModel) {
                        A.myCells[i][j].temperature = temp;
                    } else {
                        B.myCells[i][j].temperature = temp;
                    }
                }
            }
        } else {
            //public Controller(Model a, Model current, Model b, int row, int y, int width, int height){
            upperRight = new Controller(A, baseModel,B, row +width/2, y, width/2 + width % 2, height/2 + height % 2);
            upperLeft = new Controller(A, baseModel,B, row, y, width/2 + (width % 2), height/2 + height % 2); //top left quadrant
            bottomRight = new Controller(A, baseModel,B, row +width/2, y+height/2, width/2 + width % 2, height/2 + height % 2);
            bottomLeft = new Controller(A, baseModel,B, row, y+height/2, width/2 + width % 2, height/2 + height % 2);
            invokeAll(upperLeft, upperRight, bottomLeft, bottomRight);
        }




    }

    public void setEqual(Model updated, Model ToBeUpdated){
        for (int i = 0; i < updated.myCells.length; i++) {
            for (int j = 0; j < updated.myCells[0].length; j++) {
                ToBeUpdated.myCells[i][j].temperature = updated.myCells[i][j].temperature;
            }
        }
    }



    public Boolean convergence(Model modA, Model modB) {
        double diff = Math.abs(totalTemp(modB,modA));

        if (diff < 0.001) {
            return true;
        }
        return false;
    }


    public double totalTemp(Model mod, Model mod2){
        double total=0;
        for (int i = 0; i < mod.myCells.length; i++) {
            for (int j = 0; j < mod.myCells[0].length; j++) {
                total+= mod.myCells[i][j].temperature-mod2.myCells[i][j].temperature;
            }
        }
        return total;
    }


}
