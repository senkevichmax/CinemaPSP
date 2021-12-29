package by.bsuir.cinema.jframe.admin;

import static by.bsuir.cinema.controller.Starter.user;

import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FilmsFrame {
    private JTextArea filmsValue;
    public JPanel getPanel;
    private JLabel films;
    private JButton back;
    private JTextField filmId;
    private JButton deleteFilm;
    private JScrollPane jSc;
    private JButton расчетПрибылиФильмаButton;
    private JButton редактированиеЖанраButton;
    private JButton редактироватьНазваниеButton;
    public JFrame jFrame;
    private DataOutputStream output;

    public FilmsFrame(JFrame frame, DataOutputStream output) throws ProjectException {
        this.output = output;

        jFrame = frame;
        filmsValue.append(FilmLogic.findAllFilms());

        deleteFilm.addActionListener(e -> {
           int integerFilmId = Integer.parseInt(filmId.getText());
            try {
                if (FilmLogic.deleteFilmById(integerFilmId)){
                    JOptionPane.showMessageDialog(null, "Фильм был успешно удален");
                    this.output.writeUTF(user.toString() + "deleted film with id = " + integerFilmId);
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        back.addActionListener(e -> {
            jFrame.dispose();
            openAdminUserMenu();
        });
        расчетПрибылиФильмаButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] numbers;
                numbers = new int[10];
                for (int i = 0; i < numbers.length; i++) {
                    numbers[i] = ((int)(250000 + Math.random()* 50000) );
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
                String str = "Прибыль за весь прокат фильма: " +Integer.toString(average) + " синемакоинов";
                JOptionPane.showMessageDialog(jFrame, str);

            }
        });
        редактированиеЖанраButton.addActionListener(e -> {
            String str = filmId.getText();
            int integerFilmId = Integer.parseInt(str.split(" ")[0]);
            String genre = str.split(" ")[1];
            try {
                if (FilmLogic.Edit_genre(genre,integerFilmId)){
                    JOptionPane.showMessageDialog(null, "Фильм был успешно отредактирован");
                    this.output.writeUTF(user.toString() + "deleted film with id = " + integerFilmId);
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        редактироватьНазваниеButton.addActionListener(e -> {
            String str = filmId.getText();
            int integerFilmId = Integer.parseInt(str.split(" ")[0]);
            String name = str.split(" ")[1];
            try {
                if (FilmLogic.Edit_name(name,integerFilmId)){
                    JOptionPane.showMessageDialog(null, "Фильм был успешно отредактирован");
                    this.output.writeUTF(user.toString() + "deleted film with id = " + integerFilmId);
                } else {
                    JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
                }
            } catch (ProjectException e1) {
                JOptionPane.showMessageDialog(null, "Упс, что-то пошло не так:(");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void openAdminUserMenu(){
        JFrame frame = new JFrame("Меню администратора");
        frame.setBounds(650, 300, 15000, 300);
        frame.setContentPane(new AdminMenuFrame(frame, output).adminMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
