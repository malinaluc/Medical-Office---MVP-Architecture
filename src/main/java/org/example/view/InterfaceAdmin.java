package org.example.view;

import javax.swing.*;

public interface InterfaceAdmin {
    JPanel getPanel1();

    void handleViewAllUsers();

    JTextArea getVizualizareUtilizatoritextArea();

    JTextField getUsernameCreareUtilizatorTextField();

    JTextField getPasswordCreareUtilizatorTextField();

    JTextField getTipUtilizatorCreareTextField();

    JTextField getIdUtilizatorUpdateTextField();

    JTextField getUsernameUtilizatorUpdateTextField();

    JTextField getPasswordUpdateUtilizatorTextField();

    JTextField getIdUtilizatorStergereTextField();

    void handleCreateUser();

    void handleUpdateUser();

    void handleDeleteUser();

    void handleLogOut();


}
