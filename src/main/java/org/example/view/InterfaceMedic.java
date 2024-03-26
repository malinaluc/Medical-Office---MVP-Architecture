package org.example.view;

import javax.swing.*;

public interface InterfaceMedic {

    JPanel getPanel1();

    JTextArea getVizualizareFiseTextArea();

    void handleUpdateFisaMedicala();

    void handleReadAllFisaMedicala();

    JTextField getIdFisaMedicalaTextField();

    JTextField getSimptomeTextField();

    JTextField getDiagnosticTextField();

    JTextField getTratamentTextField();

    void handlefilterByDiagnostic();

    JComboBox getFiltrareDiagnosticComboBox();

    JTextArea getFiltrareDiagnosticTextArea();

    void addItemsDiagnosticComboBox();

    JComboBox getFiltrareTratamentComboBox();

    JTextArea getFiltrareTratamentTextArea();

    void handleFilterByTratament();

    void handleLogOutAction();


}
