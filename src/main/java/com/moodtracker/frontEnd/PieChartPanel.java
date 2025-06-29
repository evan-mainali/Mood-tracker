package com.moodtracker.frontEnd;

import com.moodtracker.UserInfo;

import javax.swing.*;
import java.awt.*;

public class PieChartPanel extends JPanel {

    private Color[] colors;
    private double[] data;

    public PieChartPanel(){
        makeData();
        makeColor();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPieChart(g);
    }

    private void drawPieChart(Graphics g){

        Graphics2D graphics = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height)-20;

        int x = (width-diameter)/2;
        int y = (height-diameter)/2;

        double totalAngle = 0.0;
        double startAngle = 0.0;

        for (int i = 0; i < data.length; i++) {
            graphics.setColor(colors[i % colors.length]);

            double angle = data[i] / 100.0 * 360.0;

            // the last slice completes the circle
            if (i == data.length - 1) {
                angle = 360.0 - totalAngle;
            }

            int arcAngle = (int) Math.round(angle);

            graphics.fillArc(x, y, diameter, diameter, (int) Math.round(startAngle), arcAngle);

            startAngle += angle;
            totalAngle += angle;
        }

        int lengendX = width-110;
        int legendY = 20;

        for(int i =0;i< data.length;i++){

            graphics.setColor(colors[i%colors.length]);
            graphics.fillRect(lengendX,legendY,20,20);
            graphics.setColor(Color.BLACK);
            graphics.drawString("Slice" + (i+1)+":"+data[i]+"%",lengendX+30,legendY+15);
            legendY+=30;
        }


    }


    private void makeData(){
        int size = UserInfo.getPercentage().size();
        data= new double[size];
        for(int i =0;i<UserInfo.getPercentage().size();i++){
            data[i]=UserInfo.getPercentage().get(i);
        }


    }

    private void makeColor(){
        int size = ColorSelect.getColourArray().length;
        colors= new Color[size];
        for(int i =0;i<ColorSelect.getColourArray().length;i++){
            colors[i]=ColorSelect.getColourArray()[i];
        }


    }
}
