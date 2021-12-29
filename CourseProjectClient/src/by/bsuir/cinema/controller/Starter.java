package by.bsuir.cinema.controller;

import by.bsuir.cinema.entity.user.User;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.jframe.admin.AdminMenuFrame;
import by.bsuir.cinema.jframe.client.SignUpFrame;
import by.bsuir.cinema.logic.UserLogic;
import by.bsuir.cinema.jframe.client.UserMenuFrame;
import by.bsuir.cinema.util.Encryption;
import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Starter {
    private static final int X = 650;
    private static final int Y = 300;
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 300;
    public static User user;

    public JPanel authorizationPanel;
    private JButton signIn;
    private JLabel authtorlabel;
    private JLabel login;
    private JLabel password;
    private JTextField loginText;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private static Socket connection;
    private static OutputStream output;
    private static DataOutputStream dataOutputStream;

    public static void main(String[] args) throws IOException {
            connection = new Socket(InetAddress.getByName("127.0.0.1"), 8888);
        output = connection.getOutputStream();
        dataOutputStream = new DataOutputStream(output);
        JFrame authorizationFrame = new JFrame("Авторизация");
        authorizationFrame.setBounds(X, Y, WIDTH, HEIGHT);
        authorizationFrame.setContentPane(new Starter(authorizationFrame, dataOutputStream).authorizationPanel);
        authorizationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        authorizationFrame.pack();
        authorizationFrame.setVisible(true);
    }

    public Starter(JFrame authorizationFrame, DataOutputStream output) {
        signIn.addActionListener(e -> {
            try {
                user = UserLogic.findUser(loginText.getText(), Encryption.encryptPassword(passwordField.getText()));
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
            if (user != null) {
                switch (user.getType()) {
                    case ADMIN:
                        authorizationFrame.dispose();
                        openAdminMenu(dataOutputStream);
                        break;
                    case CLIENT:
                        authorizationFrame.dispose();
                        openUserMenu(dataOutputStream);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Ошибка");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Такого пользователя нет");
            }
        });

        signUpButton.addActionListener(e -> {
            authorizationFrame.dispose();
            openSignUpMenu(dataOutputStream);
        });
    }

    private void openAdminMenu(DataOutputStream dataOutputStream) {
        JFrame frame = new JFrame("Меню администратора");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new AdminMenuFrame(frame, dataOutputStream).adminMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        new Thread(new AdminMenuFrame(dataOutputStream)).start();
    }
    private void openUserMenu(DataOutputStream dataOutputStream) {
        JFrame frame = new JFrame("Меню пользователя");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new UserMenuFrame(frame, dataOutputStream).userMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        new Thread(new UserMenuFrame(dataOutputStream)).start();
    }

    private void openSignUpMenu(DataOutputStream dataOutputStream) {
        JFrame frame = new JFrame("Меню регистрации");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new SignUpFrame(frame, dataOutputStream).signUpPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
