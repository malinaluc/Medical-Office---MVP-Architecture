package org.example;

import org.example.view.Login;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login loginForm = new Login();
                JFrame frame = new JFrame("Login Form");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(loginForm.getPanel1()); // Add the JPanel from Login to the JFrame
                frame.setSize(700, 700);
                // frame.pack(); // Size the frame to fit its contents
                frame.setLocationRelativeTo(null); // Center the frame on the screen
                frame.setVisible(true); // Make the frame visible
            }
        });
    }
}
