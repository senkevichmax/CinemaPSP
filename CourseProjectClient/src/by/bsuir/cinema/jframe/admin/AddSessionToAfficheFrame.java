package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;
import by.bsuir.cinema.logic.SessionLogic;
import static by.bsuir.cinema.controller.Starter.user;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AddSessionToAfficheFrame {
    public JPanel adminAddingSessionPanel;
    private JLabel filmNameLabel;
    private JTextField filmNameValue;
    private JLabel dateAndTimeLabel;
    private JTextField dateAndTimeValue;
    private JLabel costLabel;
    private JTextField costValue;
    private JButton addSessionButton;
    private JButton backButton;
    public JFrame frame;
    private DataOutputStream output;

    public AddSessionToAfficheFrame(JFrame sessionFrame, DataOutputStream output) {
        this.frame = sessionFrame;
        this.output = output;

        addSessionButton.addActionListener(e -> {
            String filmName = filmNameValue.getText();
            String dateAndTime = dateAndTimeValue.getText();
            String cost = costValue.getText();
            if (filmName != "" && dateAndTime != "" && cost != ""){
                try {
                    if (SessionLogic.addNewSession(filmName, dateAndTime, cost)){
                        JOptionPane.showMessageDialog(null, "Сеанс был успешно добавлен");
                        this.output.writeUTF(user.toString() + " добавил новый сеанс: " + filmName + ", "
                                + dateAndTime + ", " + cost);
                    } else {
                        JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                        this.output.writeUTF(user.toString() + " пытался добавить сеанс, но произошла ошибка: "
                                + filmName + ", " + dateAndTime + ", " + cost);
                    }
                } catch (ProjectException e1) {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                    try {
                        this.output.writeUTF(user.toString() + " пытался добавить сеанс, но произошла ошибка: "
                                + filmName + ", " + dateAndTime + ", " + cost);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Вы не все поля заполнили");
                try {
                    this.output.writeUTF(user.toString() + " пытался добавить сеанс, но произошла ошибка: "
                            + filmName + ", " + dateAndTime + ", " + cost);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
