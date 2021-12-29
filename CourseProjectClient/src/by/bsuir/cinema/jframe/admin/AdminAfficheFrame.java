package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.SessionLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static by.bsuir.cinema.controller.Starter.user;

public class AdminAfficheFrame {
    public JPanel getPanel;
    private JLabel ourSessions;
    private JButton back;
    private JButton deleteSession;
    private JTextField sessionId;
    private JScrollPane jSc;
    private JTextArea sessionsValue;
    private JButton посчитатьПрибыльЗаСеансButton;
    public JFrame jFrame;
    private DataOutputStream output;

    public AdminAfficheFrame(JFrame jFrame, DataOutputStream outputStream) throws ProjectException {
        this.jFrame = jFrame;
        this.output = outputStream;
        sessionsValue.setEditable(false);
        sessionsValue.append(SessionLogic.findAllSessions());

        deleteSession.addActionListener(e -> {
            int integerSessionId = Integer.parseInt(sessionId.getText());
            try {
                if (SessionLogic.deleteFromSession(integerSessionId)){
                    JOptionPane.showMessageDialog(null, "Сеанс был успешно удален");
                    this.output.writeUTF(user.toString() + "удалил фильм с ид = " + integerSessionId);
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                    this.output.writeUTF(user.toString() + "неудачно удалил фильм с ид = " + integerSessionId);
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        back.addActionListener(e -> {
            this.jFrame.dispose();
            opedAdminUserMenu();
        });
        посчитатьПрибыльЗаСеансButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] numbers;
                numbers = new int[10];
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = ((int)(15000 + Math.random()* 5000) );
                }

                int average = 0;
                if (numbers.length > 0)
                {
                    int sum = 0;
                    for (int j = 0; j < numbers.length; j++) {
                        sum += numbers[j];
                    }
                    average = sum / numbers.length;
                }
                String str = "Прибыль за сеанс: " +Integer.toString(average) + " синемакоинов";
                JOptionPane.showMessageDialog(jFrame, str);
            }
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
