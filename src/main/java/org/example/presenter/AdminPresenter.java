package org.example.presenter;

import org.example.model.entity.User;
import org.example.model.repository.UserRepository;
import org.example.utils.SessionManager;
import org.example.view.InterfaceAdmin;
import org.example.view.InterfaceLogin;
import org.example.view.Login;

import javax.swing.*;
import java.util.List;

public class AdminPresenter {

    private InterfaceAdmin interfaceAdmin;

    private InterfaceLogin interfaceLogin;

    private UserRepository userRepository = new UserRepository();

    public AdminPresenter(InterfaceAdmin interfaceAdmin) {
        this.interfaceAdmin = interfaceAdmin;
    }


    public void handleViewAllUsers() {
        interfaceAdmin.getVizualizareUtilizatoritextArea().setText("");
        List<User> allUsers = userRepository.readAll();
        for (User user : allUsers) {
            String rol = (user.getRol() == 1) ? "administrator" : (user.getRol() == 2) ? "medic" : "asistent";
            interfaceAdmin.getVizualizareUtilizatoritextArea().append("ID User: " + user.getIdUser() + "\n" + "Username: " + user.getUsername() + "\n" + "Password: " + user.getPassword() + "\n" + "Rol: " + rol + "\n" + "\n");
        }
    }

    public void handleCreateUser() {
        String username = interfaceAdmin.getUsernameCreareUtilizatorTextField().getText();
        String password = interfaceAdmin.getPasswordCreareUtilizatorTextField().getText();
        Integer rol = Integer.parseInt(interfaceAdmin.getTipUtilizatorCreareTextField().getText());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRol(rol);

        userRepository.save(newUser);
    }

    public void handleUpdateUser() {
        String username = interfaceAdmin.getUsernameUtilizatorUpdateTextField().getText();
        String password = interfaceAdmin.getPasswordUpdateUtilizatorTextField().getText();
        Integer idUtilizator = Integer.parseInt(interfaceAdmin.getIdUtilizatorUpdateTextField().getText());

        User existingUser = userRepository.findByID(idUtilizator);

        Integer rol = existingUser.getRol();

        User newUser = new User(idUtilizator, username, password, rol);

        userRepository.update(newUser);
    }

    public void handleDeleteUser() {
        Integer idUser = Integer.parseInt(interfaceAdmin.getIdUtilizatorStergereTextField().getText());
        User userToDelete = userRepository.findByID(idUser);
        userRepository.delete(userToDelete);
    }

    public void handleLogOut() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(interfaceAdmin.getPanel1());
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
