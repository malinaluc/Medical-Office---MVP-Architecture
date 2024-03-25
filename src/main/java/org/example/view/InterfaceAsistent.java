package org.example.view;

import javax.swing.*;

public interface InterfaceAsistent {
    JPanel getPanel1();

    JTextArea getVizualizareFiseTextArea();

    JTextField getIdFisaUpdateTextField();

    JTextField getVarstaPacientUpdateTextField();

    JTextField getMedicUpdateTextField();

    JTextField getAsistentUpdateTextField();

    JTextField getIdFisaDeleteTextField();

    JTextField getDiagnosticCreateTextField();

    JTextField getTratamentCreateTextField();

    JTextField getSimptomeCreateTextField();

    JTextField getVarstaPacientCreateTextField();

    JTextField getMedicCreateTextField();

    JTextField getAsistentCreateTextField();

    JComboBox getFiltrarePacientiMedicComboBox();

    JComboBox getFiltrarePacientiDiagnosticComboBox();

    JComboBox getFiltrarePacientiVarstaComboBox();

    JTextArea getFiltrarePacientiMedicTextArea();

    JTextArea getFiltrarePacientiDiagnosticTextArea();

    JTextArea getFiltrarePacientiVarstaTextArea();

    void addItemsDiagnosticComboBox();

    void addItemsMedicComboBox();

    void addItemsVarstaComboBox();

    void handleVizualizareFise();

    void handleFiltrareByDiagnostic();

    void handleFilterByVarsta();

    void handleFilterByMedic();

    void handleUpdateFisa();

    void handleDeleteFisa();

    void handleCreateFisa();

}
