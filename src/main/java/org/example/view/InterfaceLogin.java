package org.example.view;

import javax.swing.*;

public interface InterfaceLogin {
    JPanel getPanel1();

    JTextField getUsernameTextField();

    JPasswordField getPasswordField();

    JButton getLoginButton();


    void showMedicForm();

    void handleLoginAction();


}
