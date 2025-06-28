package com.moodtracker.frontEnd;

import com.moodtracker.UserInfo;

import javax.swing.*;
import java.awt.*;

public class PieChartPanel extends JPanel {
    private Color[] chartColours = ColorSelect.getColourArray();


     private int[] data = {40,30,25};

     @Override
     protected void paintComponent(Graphics g ){
         super.paintComponent(g);
         drawPieChart(g);

     }
     private void drawPieChart(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();

        int height = getHeight();

        int diameter = Math.min(width,height)-20;

        int x = (width - diameter)/2;

        int y = (height-diameter)/2;

        int startAngle = 0;

        for(int i=0 ; i<data.length;i++){

            int arcAngle = (int) ((double) data[i]/100 *360);

            g2d.setColor(chartColours[i]);
            g2d.fillArc(x,y,diameter,diameter,startAngle,arcAngle);

            startAngle+=arcAngle;
        }

        int legendX = width-110;
        int legendY = 20;

        for(int i =0;i<data.length;i++){
            g2d.setColor(chartColours[i]);
            g2d.fillRect(legendX,legendY,20,20);
            g2d.setColor(Color.BLACK);
            g2d.drawString("Slice"+(i+1)+"."+data[i]+"%",legendX+30,legendY+15);
            legendY+=30;
        }
     }


}
