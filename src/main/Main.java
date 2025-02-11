//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main;

import java.awt.Component;
import java.io.FileNotFoundException;
import javax.swing.*;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(3);
        window.setResizable(false);
        window.setTitle("Once a part of US");
        new Main().setIcon();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn==true)
        {
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo((Component)null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    public void setIcon()
    {
        ImageIcon icon=new ImageIcon(getClass().getClassLoader().getResource("objects/YourSoul.png"));
        window.setIconImage(icon.getImage());
    }
}
