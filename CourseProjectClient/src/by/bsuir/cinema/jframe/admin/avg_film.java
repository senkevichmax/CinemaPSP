package by.bsuir.cinema.jframe.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;

public class avg_film {
    public JPanel avg_filmPanel;
    private JButton backButton;
    private JLabel film_sred;
    private JTextArea film_value;
    private JScrollPane Jsc;
    private JButton рассчитатьButton;
    public JFrame frame;
    private DataOutputStream output;

        public avg_film(JFrame avg_frame, DataOutputStream outputStream) {
            frame = avg_frame;
            this.output = outputStream;

            film_value.setEditable(false);
            backButton.addActionListener(e -> {
                avg_frame.dispose();
                opedAdminUserMenu();
            });
            рассчитатьButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int[] numbers;
                    numbers = new int[10];
                    for (int i = 0; i < numbers.length; i++) {
                        numbers[i] = ((int)(90 + Math.random()* 160) );
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
                    String str = Integer.toString(average);
                    film_value.append(str);

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

