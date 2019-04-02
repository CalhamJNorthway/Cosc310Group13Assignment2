package chadbot;

import javax.swing.*;
import java.awt.*;

public class BotGUI extends Frame{

    public JFrame frame;
    public JPanel panel;
    public JTextField textInput;
    public JTextArea responseArea;
    public JScrollPane scrollyPollyOlly;
    public JScrollBar scrollBoi;



    public BotGUI(){


        this.frame  = new JFrame();
        this.panel = new JPanel();
        this.textInput = new JTextField(30);
        this.responseArea = new JTextArea(30,35);
        this.responseArea.setLineWrap(true);
        this.scrollyPollyOlly = new JScrollPane(responseArea);
        this.scrollBoi = scrollyPollyOlly.getVerticalScrollBar();

        this.responseArea.setBackground(Color.PINK);
        this.responseArea.setForeground(Color.darkGray);
        this.panel.setBackground(Color.lightGray);
        this.textInput.setForeground(Color.darkGray);

        responseArea.setEditable(false);
        this.scrollyPollyOlly.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollyPollyOlly.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


    }

    public void initGUI() {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(scrollyPollyOlly);
        panel.add(new Label("Type something!"));
        panel.add(textInput);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public void closeGUI() {
        frame.setVisible(false);
    }


}
