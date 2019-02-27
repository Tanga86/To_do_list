package gui;

import gui.frames.MainWindow;

import javax.swing.*;

public class FrameRunner {
    public static void run(){
        configureLookAndFeel();

        MainWindow window = new MainWindow();
        window.setLocationRelativeTo( null );
        window.setVisible(true);
        window.intilal();
    }


    private static void configureLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break; //preferred!
                }
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception e) {}
    }
}
