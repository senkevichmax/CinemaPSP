package by.bsuir.cinema.jframe.client;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.TicketsLogic;
import static by.bsuir.cinema.controller.Starter.user;
import javax.swing.*;
import java.io.DataOutputStream;

public class TicketsFrame {
    public JPanel getPanel;
    private JLabel yourTickets;
    private JButton back;
    private JTextArea tickets;
    private JFrame jFrame;
    private DataOutputStream output;

    public TicketsFrame(JFrame jFrame, DataOutputStream output){
        this.jFrame = jFrame;
        this.output = output;

        tickets.removeAll();
        try {
            tickets.append(TicketsLogic.findAllTickets(user.getId()));
        } catch (ProjectException e) {
            e.printStackTrace();
        }

        back.addActionListener(e -> {
            this.jFrame.dispose();
            openUserMenu();
        });
    }

    private void openUserMenu() {
        JFrame newFrame = new JFrame("Меню пользователя");
        newFrame.setBounds(650, 300, 15000, 300);
        newFrame.setContentPane(new UserMenuFrame(newFrame, this.output).userMenu);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);
    }
}
