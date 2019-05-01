package pkg_safe;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel{
    private static int squareSize;
    private static int gridWidth;
    private static int gridHeight;
    private static int squaresPerRow;
    private static int squaresPerCol;
    Model currentModel;

    @Override
    public void setDoubleBuffered(boolean b) {
        super.setDoubleBuffered(b);
    }


    public View(int sS, int gW, int gH){
        //setDoubleBuffered(false);
        gridWidth = gW;
        gridHeight = gH;
        squareSize = sS;
        squaresPerRow = gridHeight /squareSize;
        squaresPerCol = gridWidth /squareSize;

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double maxTemp = currentModel.maxTemp;
        Color currentTempColor;
        for (int x =0;x<squaresPerCol;x++){
            for (int y = 0;y<squaresPerRow;y++){
                System.out.println(currentModel.myCells[x][y].temperature);
                if (currentModel.myCells[x][y].temperature <= maxTemp * .0125) {
                    currentTempColor = new Color(255, 238, 160);
                    g.setColor(currentTempColor);
                    g.fillRect(x * squareSize, y * squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .025){
                    currentTempColor = new Color(169, 255, 187);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .05){
                    currentTempColor = new Color(169, 255, 250);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                } else if (currentModel.myCells[x][y].temperature <= maxTemp * .15){
                    currentTempColor = new Color(70, 186, 255);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .20){
                    currentTempColor = new Color(66, 58, 255);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .30){
                    currentTempColor = new Color(5, 4, 255);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .35){
                    currentTempColor = new Color(107, 255, 90);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .45){
                    currentTempColor = new Color(93, 255, 12);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .50){
                    currentTempColor = new Color(7, 255, 0);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .60){
                    currentTempColor = new Color(255, 154, 224);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .65){
                    currentTempColor = new Color(255, 80, 230);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .75){
                    currentTempColor = new Color(255, 0, 237);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .80){
                    currentTempColor = new Color(255, 238, 162);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .90){
                    currentTempColor = new Color(255, 234, 123);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }else if (currentModel.myCells[x][y].temperature <= maxTemp * .95){
                    currentTempColor = new Color(255, 222, 44);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                } else if (currentModel.myCells[x][y].temperature<=maxTemp){
                    currentTempColor = new Color(255, 239, 3);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                } else {
                    currentTempColor = new Color(0, 0, 0);
                    g.setColor(currentTempColor);
                    g.fillRect(x*squareSize, y*squareSize, squareSize, squareSize);
                }
            }
        }
    }
}
