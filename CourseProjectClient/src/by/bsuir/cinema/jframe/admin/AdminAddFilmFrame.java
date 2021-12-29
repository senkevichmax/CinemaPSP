package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static by.bsuir.cinema.controller.Starter.user;

public class AdminAddFilmFrame {
    public JPanel adminAddingFilmPanel;
    private JTextField mainRolesValue;
    private JTextField filmNameValue;
    private JTextField genreValue;
    private JButton addFilmButton;
    private JButton backButton;
    private JLabel filmNameLabel;
    private JLabel genreLabel;
    private JLabel producersLabel;
    private JTextField producersValue;
    private JLabel mainRolesLabel;
    public JFrame frame;
    private DataOutputStream output;

    public AdminAddFilmFrame(JFrame filmFrame, DataOutputStream output) {
        frame = filmFrame;
        this.output = output;

        addFilmButton.addActionListener(e -> {
            String filmName = filmNameValue.getText();
            String genreName = genreValue.getText();
            String producers = producersValue.getText();
            String mainRoles = mainRolesValue.getText();
            if (filmName != "" && genreName != "" && producers != "" && mainRoles != ""){
                try {
                    if (FilmLogic.addNewFilm(filmName, genreName, producers, mainRoles)){
                        JOptionPane.showMessageDialog(null, "Фильм был успешно добавлен");
                        this.output.writeUTF(user.toString() + " добавил новый фильм: " + filmName + ", "
                                + genreName + ", " + producers + ", " + mainRoles);
                    } else {
                        JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                        this.output.writeUTF(user.toString() + " неудачно добавил новый фильм: " + filmName + ", "
                                + genreName + ", " + producers + ", " + mainRoles);
                    }
                } catch (ProjectException e1) {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Вы не все поля заполнили");
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            opedAdminUserMenu();
        });
    }

    private void opedAdminUserMenu() {
        JFrame frame = new JFrame("Меню администратора");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new AdminMenuFrame(frame, output).adminMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
