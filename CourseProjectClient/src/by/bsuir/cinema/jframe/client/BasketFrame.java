package by.bsuir.cinema.jframe.client;

import by.bsuir.cinema.entity.user.Client;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.BasketLogic;

import javax.swing.*;

import java.io.DataOutputStream;
import java.io.IOException;

import static by.bsuir.cinema.controller.Starter.user;

public class BasketFrame {
    private JTextArea orders;
    public JPanel getPanel;
    public JFrame frame;
    private JLabel films;
    private JButton back;
    private JButton buyTicket;
    private JTextField sessionId;
    private JButton cancelOrder;
    private DataOutputStream output;

    public BasketFrame(JFrame frame, DataOutputStream output) throws ProjectException {
        //JFrame
        this.frame = frame;
        this.output = output;
        orders.removeAll();
        orders.append(BasketLogic.findAllSessionOfUser(user.getId()));

        buyTicket.addActionListener(e -> {
            try {
                int integerSessionId = Integer.parseInt(sessionId.getText());
                if (BasketLogic.isEnoughMoney(((Client) user).getMoney(), integerSessionId)){
                    ((Client) user).setMoney(BasketLogic.buyTicket(user.getId(), integerSessionId,
                            ((Client) user).getMoney()));
                    JOptionPane.showMessageDialog(null,
                            "Билет был успешно куплен");
                    output.writeUTF(user.toString() + " купил билет с ид = " + integerSessionId);
                    BasketLogic.deleteFromBasket(user.getId(), integerSessionId);
                    this.frame.dispose();
                    openUserMenu();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "У вас недостаточно денег: пополните счет");
                    output.writeUTF(user.toString() + " не хватило денег купить билет с ид = " + integerSessionId);
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null,
                        "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        cancelOrder.addActionListener(e -> {
            int integerSessionId = Integer.parseInt(sessionId.getText());
            try {
                if (BasketLogic.deleteFromBasket(user.getId(), integerSessionId)){
                    JOptionPane.showMessageDialog(null, "Билет был успешно отменен");
                    this.output.writeUTF(user.toString() + "отменил билет с ид = " + integerSessionId);
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                    this.output.writeUTF(user.toString() + " неудачно отменил билет с ид = " + integerSessionId);
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        back.addActionListener(e -> {
            frame.dispose();
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
