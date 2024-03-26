package org.example.presenter;

import org.example.model.entity.FisaMedicala;
import org.example.model.entity.Medic;
import org.example.model.entity.User;
import org.example.model.repository.FisaMedicalaRepository;
import org.example.utils.LoggedInUser;
import org.example.utils.SessionManager;
import org.example.view.InterfaceMedic;
import org.example.view.Login;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;

public class MedicPresenter {

    private InterfaceMedic interfaceMedic;


    private FisaMedicalaRepository fisaMedicalaRepository = new FisaMedicalaRepository();

    public MedicPresenter(InterfaceMedic interfaceMedic) {
        this.interfaceMedic = interfaceMedic;
    }


    public void handleReadAllFisaMedicala() {

        interfaceMedic.getVizualizareFiseTextArea().setText("");
        LoggedInUser loggedInUser = SessionManager.getLoggedInUser();
        List<FisaMedicala> allFisaMedicala = fisaMedicalaRepository.allFisaMedicalaByMedicUserId(loggedInUser.getIdUserLoggedIn());
        for (FisaMedicala fisaMedicala : allFisaMedicala) {
            interfaceMedic.getVizualizareFiseTextArea().append("Numar Fisa: " + fisaMedicala.getIdFisaMedicala().toString() + "\n" + "Diagnostic: " + fisaMedicala.getDiagnostic() + ", Simptome: " +
                    fisaMedicala.getSimptome() + "\n" + ", Tratament: " + fisaMedicala.getTratament() + ", Varsta Pacient: " + fisaMedicala.getVarstaPacient().toString() + "\n" + "\n");
        }
        /// logDebug("--handleReadAllFisaMedicala--" + allFisaMedicala);
    }

    public void handleUpdateFisaMedicala() {
        String fisaIDString = interfaceMedic.getIdFisaMedicalaTextField().getText();
        Integer fisaID = Integer.parseInt(fisaIDString);
        String diagnostic = interfaceMedic.getDiagnosticTextField().getText();
        String tratament = interfaceMedic.getTratamentTextField().getText();
        String simptome = interfaceMedic.getSimptomeTextField().getText();

        FisaMedicala existingFisaMedicala = fisaMedicalaRepository.findById(fisaID);

        Medic idMedic = existingFisaMedicala.getIdMedic();
        User idAsistent = existingFisaMedicala.getIdAsistent();
        Integer varstaPacient = existingFisaMedicala.getVarstaPacient();

        FisaMedicala fisaMedicala = new FisaMedicala(fisaID, diagnostic, simptome, tratament, varstaPacient, idMedic, idAsistent);

        fisaMedicalaRepository.updateFisa(fisaMedicala);

    }

    public void addItemsDiagnosticComboBox() {
        LoggedInUser loggedInUser = SessionManager.getLoggedInUser();
        Integer idUserLoggedIn = loggedInUser.getIdUserLoggedIn();
        List<FisaMedicala> allFisaMedicala = fisaMedicalaRepository.allFisaMedicalaByMedicUserId(loggedInUser.getIdUserLoggedIn());

        HashSet<String> diagnostics = new HashSet<>();

        for (FisaMedicala fisaMedicala : allFisaMedicala) {
            diagnostics.add(fisaMedicala.getDiagnostic());
        }
        if (diagnostics != null) {
            for (String diagnostic : diagnostics) {
                interfaceMedic.getFiltrareDiagnosticComboBox().addItem(diagnostic);
            }
        }
    }

    public void addItemsTratamentComboBox() {
        LoggedInUser loggedInUser = SessionManager.getLoggedInUser();
        Integer idUserLoggedIn = loggedInUser.getIdUserLoggedIn();
        List<FisaMedicala> allFisaMedicala = fisaMedicalaRepository.allFisaMedicalaByMedicUserId(loggedInUser.getIdUserLoggedIn());

        HashSet<String> trataments = new HashSet<>();
        ;
        for (FisaMedicala fisaMedicala : allFisaMedicala) {
            trataments.add(fisaMedicala.getTratament());
        }
        if (trataments != null) {
            for (String tratament : trataments) {
                interfaceMedic.getFiltrareTratamentComboBox().addItem(tratament);
            }
        }
    }

    public void handlefilterByDiagnostic() {

        interfaceMedic.getFiltrareDiagnosticTextArea().setText("");
        String diagnostic = interfaceMedic.getFiltrareDiagnosticComboBox().getSelectedItem().toString();

        List<FisaMedicala> allByDiagnostic = fisaMedicalaRepository.allFisaMedicalaByDiagnostic(diagnostic);

        for (FisaMedicala fisaMedicala : allByDiagnostic) {
            interfaceMedic.getFiltrareDiagnosticTextArea().append("Numar Fisa: " + fisaMedicala.getIdFisaMedicala().toString() + "\n" + "Diagnostic: " + fisaMedicala.getDiagnostic() + ", Simptome: " +
                    fisaMedicala.getSimptome() + "\n" + ", Tratament: " + fisaMedicala.getTratament() + ", Varsta Pacient: " + fisaMedicala.getVarstaPacient().toString() + "\n" + "\n");

        }
    }

    public void handleFilterByTratament() {
        interfaceMedic.getFiltrareTratamentTextArea().setText("");
        String tratament = interfaceMedic.getFiltrareTratamentComboBox().getSelectedItem().toString();
        List<FisaMedicala> allByTratament = fisaMedicalaRepository.allFisaMedicalaByTratament(tratament);

        for (FisaMedicala fisaMedicala : allByTratament) {
            interfaceMedic.getFiltrareTratamentTextArea().append("Numar Fisa: " + fisaMedicala.getIdFisaMedicala().toString() + "\n" + "Diagnostic: " + fisaMedicala.getDiagnostic() + ", Simptome: " +
                    fisaMedicala.getSimptome() + "\n" + ", Tratament: " + fisaMedicala.getTratament() + ", Varsta Pacient: " + fisaMedicala.getVarstaPacient().toString() + "\n" + "\n");
        }
    }

    public void handleLogOutAction() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(interfaceMedic.getPanel1());
        frame.setVisible(false);
        Login loginForm = new Login();
        JFrame loginFrame = new JFrame("Login Form");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().add(loginForm.getPanel1()); // Add the JPanel from Login to the JFrame
        loginFrame.setSize(700, 700);
        loginFrame.setLocationRelativeTo(null); // Center the frame on the screen
        loginFrame.setVisible(true); // Make the frame visible

        SessionManager.logOutUser();
    }
}
