package org.example.presenter;

import org.example.model.entity.FisaMedicala;
import org.example.model.entity.Medic;
import org.example.model.entity.User;
import org.example.model.repository.FisaMedicalaRepository;
import org.example.model.repository.MedicRepository;
import org.example.model.repository.UserRepository;
import org.example.utils.LoggedInUser;
import org.example.utils.SessionManager;
import org.example.view.InterfaceAsistent;
import org.example.view.Login;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;

import static org.example.utils.ExtensionFunctions.logDebug;

public class AsistentPresenter {

    private InterfaceAsistent interfaceAsistent;
    private FisaMedicalaRepository fisaMedicalaRepository = new FisaMedicalaRepository();

    private UserRepository userRepository = new UserRepository();
    private MedicRepository medicRepository = new MedicRepository();

    public AsistentPresenter(InterfaceAsistent interfaceAsistent) {
        this.interfaceAsistent = interfaceAsistent;
    }


    public void handleFilterByDiagnostic() {
        interfaceAsistent.getFiltrarePacientiDiagnosticTextArea().setText("");
        String diagnostic = interfaceAsistent.getFiltrarePacientiDiagnosticComboBox().getSelectedItem().toString();

        List<FisaMedicala> allByDiagnostic = fisaMedicalaRepository.allFisaMedicalaByDiagnostic(diagnostic);

        for (FisaMedicala fisaMedicala : allByDiagnostic) {
            interfaceAsistent.getFiltrarePacientiDiagnosticTextArea().append("Numar Fisa: " + fisaMedicala.getIdFisaMedicala().toString() + "\n" + "Diagnostic: " + fisaMedicala.getDiagnostic() + "\n" + ", Simptome: " +
                    fisaMedicala.getSimptome() + "\n" + ", Tratament: " + "\n" + fisaMedicala.getTratament() + "\n" + ", Varsta Pacient: " + fisaMedicala.getVarstaPacient().toString() + "\n" + "\n");
        }
    }

    public void handleFilterByVarsta() {
        interfaceAsistent.getFiltrarePacientiVarstaTextArea().setText("");
        Integer varsta = Integer.parseInt(interfaceAsistent.getFiltrarePacientiVarstaComboBox().getSelectedItem().toString());

        List<FisaMedicala> allByVarsta = fisaMedicalaRepository.allFisaMedicalaByVarsta(varsta);

        for (FisaMedicala fisaMedicala : allByVarsta) {
            interfaceAsistent.getFiltrarePacientiVarstaTextArea().append("Numar Fisa: " + fisaMedicala.getIdFisaMedicala().toString() + "\n" + "Diagnostic: " + fisaMedicala.getDiagnostic() + "\n" + ", Simptome: " +
                    fisaMedicala.getSimptome() + "\n" + ", Tratament: " + "\n" + fisaMedicala.getTratament() + "\n" + ", Varsta Pacient: " + fisaMedicala.getVarstaPacient().toString() + "\n" + "\n");
        }

    }

    public void handleFilterByMedic() {

        interfaceAsistent.getFiltrarePacientiMedicTextArea().setText("");
        Integer medicID = Integer.parseInt(interfaceAsistent.getFiltrarePacientiMedicComboBox().getSelectedItem().toString());

        List<FisaMedicala> allByMedicID = fisaMedicalaRepository.allFisaMedicalaByMedicID(medicID);

        for (FisaMedicala fisaMedicala : allByMedicID) {
            interfaceAsistent.getFiltrarePacientiMedicTextArea().append("Numar Fisa: " + fisaMedicala.getIdFisaMedicala().toString() + "\n" + "Diagnostic: " + fisaMedicala.getDiagnostic() + "\n" + ", Simptome: " +
                    fisaMedicala.getSimptome() + "\n" + ", Tratament: " + "\n" + fisaMedicala.getTratament() + "\n" + ", Varsta Pacient: " + fisaMedicala.getVarstaPacient().toString() + "\n" + "\n");
        }

    }

    public void addItemsDiagnosticComboBox() {
        LoggedInUser loggedInUser = SessionManager.getLoggedInUser();
        Integer idUserLoggedIn = loggedInUser.getIdUserLoggedIn();
        List<FisaMedicala> allFisaMedicala = fisaMedicalaRepository.allFisaMedicalaByUserId(loggedInUser.getIdUserLoggedIn());

        HashSet<String> diagnostics = new HashSet<>();

        for (FisaMedicala fisaMedicala : allFisaMedicala) {
            diagnostics.add(fisaMedicala.getDiagnostic());
        }
        if (diagnostics != null) {
            for (String diagnostic : diagnostics) {
                interfaceAsistent.getFiltrarePacientiDiagnosticComboBox().addItem(diagnostic);
            }
        }
    }

    public void addItemsMedicComboBox() {
        LoggedInUser loggedInUser = SessionManager.getLoggedInUser();
        Integer idUserLoggedIn = loggedInUser.getIdUserLoggedIn();
        List<FisaMedicala> allFisaMedicala = fisaMedicalaRepository.allFisaMedicalaByUserId(loggedInUser.getIdUserLoggedIn());

        HashSet<Medic> medics = new HashSet<>();

        for (FisaMedicala fisaMedicala : allFisaMedicala) {
            medics.add(fisaMedicala.getIdMedic());
        }

        if (medics != null) {
            for (Medic medic : medics) {
                //LazyInitializationException if I try to get the name of the medic
                interfaceAsistent.getFiltrarePacientiMedicComboBox().addItem(medic.getIdMedic());
            }
        }
    }

    public void addItemsVarstaComboBox() {
        LoggedInUser loggedInUser = SessionManager.getLoggedInUser();
        Integer idUserLoggedIn = loggedInUser.getIdUserLoggedIn();
        List<FisaMedicala> allFisaMedicala = fisaMedicalaRepository.allFisaMedicalaByUserId(loggedInUser.getIdUserLoggedIn());

        HashSet<Integer> ages = new HashSet<>();

        for (FisaMedicala fisaMedicala : allFisaMedicala) {
            ages.add(fisaMedicala.getVarstaPacient());
        }
        if (ages != null) {
            for (Integer age : ages) {
                interfaceAsistent.getFiltrarePacientiVarstaComboBox().addItem(age);
            }
        }

    }

    public void handleUpdateFisa() {

        Integer fisaID = Integer.parseInt(interfaceAsistent.getIdFisaUpdateTextField().getText());
        Integer varstaPacient = Integer.parseInt(interfaceAsistent.getVarstaPacientUpdateTextField().getText());
        Integer medicApartinator = Integer.parseInt(interfaceAsistent.getMedicUpdateTextField().getText());
        Integer asistentApartinator = Integer.parseInt((interfaceAsistent.getAsistentUpdateTextField().getText()));

        FisaMedicala existingFisa = fisaMedicalaRepository.findById(fisaID);

        User updatedUser = userRepository.findByID(asistentApartinator);
        Medic updatedMedic = medicRepository.findMedicByID(medicApartinator);

        String diagnostic = existingFisa.getDiagnostic();
        String simptome = existingFisa.getSimptome();
        String tratament = existingFisa.getTratament();

        FisaMedicala updatedFisa = new FisaMedicala(fisaID, diagnostic, simptome, tratament, varstaPacient, updatedMedic, updatedUser);

        fisaMedicalaRepository.updateFisa(updatedFisa);
    }

    public void handleDeleteFisa() {
        Integer fisaID = Integer.parseInt(interfaceAsistent.getIdFisaDeleteTextField().getText());
        FisaMedicala fisaMedicalaToDelete = fisaMedicalaRepository.findById(fisaID);
        fisaMedicalaRepository.delete(fisaMedicalaToDelete);
    }

    public void handleVizualizareFise() {
        interfaceAsistent.getVizualizareFiseTextArea().setText("");

        LoggedInUser loggedInUser = SessionManager.getLoggedInUser();
        Integer idUserLoggedIn = loggedInUser.getIdUserLoggedIn();

        List<FisaMedicala> allFisaMedicala = fisaMedicalaRepository.allFisaMedicalaByUserId(idUserLoggedIn);

        for (FisaMedicala fisaMedicala : allFisaMedicala) {
            interfaceAsistent.getVizualizareFiseTextArea().append("Numar Fisa: " + fisaMedicala.getIdFisaMedicala().toString() + "\n" + "Diagnostic: " + fisaMedicala.getDiagnostic() + ", Simptome: " +
                    fisaMedicala.getSimptome() + "\n" + ", Tratament: " + fisaMedicala.getTratament() + ", Varsta Pacient: " + fisaMedicala.getVarstaPacient().toString() + "\n" + "\n");
        }
    }

    public void handleCreateFisa() {

        String diagnostic = interfaceAsistent.getDiagnosticCreateTextField().getText();
        String tratament = interfaceAsistent.getTratamentCreateTextField().getText();
        String simptome = interfaceAsistent.getSimptomeCreateTextField().getText();
        Integer varsta = Integer.parseInt(interfaceAsistent.getVarstaPacientCreateTextField().getText());
        Integer medicApartinatorID = Integer.parseInt(interfaceAsistent.getMedicCreateTextField().getText());
        Integer asistentApartinatorID = Integer.parseInt(interfaceAsistent.getAsistentCreateTextField().getText());

        Medic newMedic = medicRepository.findMedicByID(medicApartinatorID);
        User newAsistent = userRepository.findByID(asistentApartinatorID);

        FisaMedicala newFisaMedicala = new FisaMedicala();
        newFisaMedicala.setDiagnostic(diagnostic);
        newFisaMedicala.setTratament(tratament);
        newFisaMedicala.setSimptome(simptome);
        newFisaMedicala.setVarstaPacient(varsta);
        newFisaMedicala.setIdMedic(newMedic);
        newFisaMedicala.setIdAsistent(newAsistent);
//        newFisaMedicala.setIdFisaMedicala(4);

        try {
            fisaMedicalaRepository.save(newFisaMedicala);
        } catch (Exception e) {
            logDebug(" -- handleCreateFisa -- " + e);
        }


    }

    public void handleLogOut() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(interfaceAsistent.getPanel1());
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
