package by.bsuir.cinema.jframe.client;

import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.UserLogic;
import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import static by.bsuir.cinema.controller.Starter.user;

public class UserMenuFrame implements Runnable {
    public JPanel userMenu;
    private JButton affiche;
    private JButton basket;
    private JButton exit;
    private JButton tickets;
    private JTextField value;
    private JButton topUp;
    private JTextField balance;
    public JFrame frame;
    private DataOutputStream output;

    public UserMenuFrame(JFrame frame, DataOutputStream dataOutputStream) {
        this.frame=frame;
        this.output = dataOutputStream;
        balance.setText("Ваш баланс = " + ((Client) user).getMoney());
        affiche.addActionListener(e -> {
            try {
                this.frame.dispose();
                openAffice();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        basket.addActionListener(e -> {
            try {
                this.frame.dispose();
                showBasket();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        tickets.addActionListener(e -> {
            this.frame.dispose();
            showTickets();
        });

        topUp.addActionListener(e -> {
            boolean flag;
            try {
                flag = UserLogic.updateUserMoney(BigDecimal.valueOf(Double.parseDouble(value.getText())),
                        (Client) user);
                if (flag){
                    JOptionPane.showMessageDialog(null, "Баланс был успешно пополнен");
                    output.writeUTF(user.toString() + "пополнил себе баланс на " + value.getText());
                    this.frame.dispose();
                    openUserMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                    output.writeUTF(user.toString() + "неудачно пополнил себе баланс на " + value.getText());
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });

        exit.addActionListener(e -> {
            frame.dispose();
            try {
                this.output.writeUTF(user.toString() + " вышел из приложения");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public UserMenuFrame(DataOutputStream dataOutputStream) {
        this.output = dataOutputStream;
    }

    private void openAffice() throws ProjectException {
        JFrame ticketFrame = new JFrame("Покупка билета");
        ticketFrame.setBounds(650, 300, 15000, 300);
        ticketFrame.setContentPane(new AfficheFrame(ticketFrame, output).getPanel);
        ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticketFrame.pack();
        ticketFrame.setVisible(true);
    }

    private void showBasket() throws ProjectException {
        JFrame basketFrame = new JFrame("Корзина");
        basketFrame.setBounds(650, 300, 1000, 300);
        basketFrame.setContentPane(new BasketFrame(basketFrame, output).getPanel);
        basketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        basketFrame.pack();
        basketFrame.setVisible(true);
        frame.setVisible(false);
    }

    private void showTickets(){
        JFrame ticketFrame = new JFrame("Билеты");
        ticketFrame.setBounds(650, 300, 1000, 300);
        ticketFrame.setContentPane(new TicketsFrame(ticketFrame, output).getPanel);
        ticketFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ticketFrame.pack();
        ticketFrame.setVisible(true);
        frame.setVisible(false);
    }

    private void openUserMenu() {
        JFrame newFrame = new JFrame("Меню пользователя");
        newFrame.setBounds(650, 300, 15000, 300);
        newFrame.setContentPane(new UserMenuFrame(newFrame, output).userMenu);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.pack();
        newFrame.setVisible(true);
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            this.output.writeUTF(user.toString() + " подключился");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
