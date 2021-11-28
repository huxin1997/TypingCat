package com.typingcat;


import com.intellij.codeInsight.completion.impl.CamelHumpMatcher;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

public class MainTest {


    @Test
    public void test() {

        String s = CamelHumpMatcher.applyMiddleMatching("Hello");
        System.out.println(s);
        Assert.assertTrue(s.length() > 1);

    }

    public static void main(String[] args) {
//        String s = CamelHumpMatcher.applyMiddleMatching("Hello");
//        System.out.println(s);

        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


        JPanel jPanel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                BasicStroke bs = new BasicStroke(10.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);

                super.paint(g);

                Graphics2D gp2d = (Graphics2D) g;

                gp2d.setColor(Color.blue);
                gp2d.setPaint(new GradientPaint(0, 0, Color.BLUE, 400, 400, Color.RED));

                //第一个点的x坐标，第一个点的y坐标，第二个点的x坐标，第二点的坐标

//                gp2d.drawRect(30, 30, 80, 300);
                gp2d.fillRect(0, 0, 80, 300);

            }
        };
        jPanel.add(new JButton("1111"), BorderLayout.CENTER);
        JTextArea comp = new JTextArea("1111");

        comp.setPreferredSize(new Dimension(100, 100));
        jPanel.add(comp, BorderLayout.LINE_END);
        jFrame.setContentPane(jPanel);
        jFrame.setSize(400, 300);
        jFrame.setLocation(1000, 500);
        jFrame.setVisible(true);
        jFrame.pack();
    }

}
