package com.typingcat.component;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;

import javax.swing.*;
import java.awt.*;

/**
 * @author huxin
 */
public class Dialog extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        PsiElement element = e.getData(CommonDataKeys.PSI_ELEMENT);
        Editor editor = e.getData(CommonDataKeys.EDITOR);

        JComponent component = editor.getComponent();
        JComponent contentComponent = editor.getContentComponent();
        JComponent headerComponent = editor.getHeaderComponent();
        Messages.showMessageDialog("" + element, "Information", Messages.getInformationIcon());


        JPanel jPanel = new JPanel() {

            long startTime = System.currentTimeMillis() / 1000;

            @Override
            public void paint(Graphics g) {
                BasicStroke bs = new BasicStroke(10.1f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);

                super.paint(g);

                Graphics2D gp2d = (Graphics2D) g;

                gp2d.setColor(Color.blue);
                gp2d.setPaint(new GradientPaint(0, 0, Color.BLUE, 200, 400, Color.RED));

                //第一个点的x坐标，第一个点的y坐标，第二个点的x坐标，第二点的坐标

//                gp2d.drawRect(30, 30, 80, 300);
                gp2d.fillRect(0, (int) (System.currentTimeMillis() / 1000 - startTime), 10, 1000);

            }
        };


//        (JScrollPane)((JBLayeredPane)((BorderLayout)component.getLayout()).getLayoutComponent(BorderLayout.CENTER)).component.get(1)
//        JScrollPane jScrollPane;
//        jScrollPane.getLayout()
//        ((JBLayeredPane)component.component.get(1)).add(new JButton("123"),0)
//        ((JBLayeredPane)component.component.get(1)).add(new JTextArea("3\n\n\n\n\n\n"),1)
        jPanel.setPreferredSize(new Dimension(10, 800));
        component.getParent().add(jPanel, BorderLayout.LINE_END);


    }


}
