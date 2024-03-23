package org.example.presenter;

import org.example.model.entity.User;
import org.example.model.repository.UserRepository;
import org.example.view.InterfaceLogin;
import org.example.view.MedicForm;

import javax.swing.*;

import static org.example.utils.ExtensionFunctions.logDebug;


public class LoginPresenter {

    private InterfaceLogin interfaceLogin;

    private UserRepository userRepository = new UserRepository();

    public LoginPresenter(InterfaceLogin interfaceLogin) {
        this.interfaceLogin = interfaceLogin;
    }

    public void handleLoginAction() {
        String username = interfaceLogin.getUsernameTextField().getText();
        char[] passwordChars = interfaceLogin.getPasswordField().getPassword();
        String password = new String(passwordChars);

        User user = userRepository.getUserByEmailAndPassword(username, password);

        logDebug("Username = " + username + " and Password = " + password);
        if (user != null) {
            System.out.println(user.getUsername() + " " + user.getPassword());
        } else
            JOptionPane.showMessageDialog(null, "Wrong username or password", "Error Message", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMedicForm() {

        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(interfaceLogin.getPanel1());
        frame.setVisible(false);
        MedicForm medicForm = new MedicForm();
        JFrame medicFrame = new JFrame("Medic Form");
        medicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        medicFrame.getContentPane().add(medicForm.getPanel1());
        medicFrame.setSize(700, 700);
        medicFrame.setLocationRelativeTo(null);
        medicFrame.setVisible(true);
    }

}

