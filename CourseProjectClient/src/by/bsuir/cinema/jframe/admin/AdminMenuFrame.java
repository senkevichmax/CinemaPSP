package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.exception.ProjectException;
import static by.bsuir.cinema.controller.Starter.user;
import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.swing.WindowConstants;

public class AdminMenuFrame implements Runnable {
    public JFrame frame;
    public JPanel adminMenu;
    private JButton addFilm;
    private JButton seeAndDeleteAffiche;
    private JButton exit;
    private JButton addingSessionToAffiche;
    private JButton films;
    private JButton расчетСреднейПродолжительностиФильмаButton;
    private JButton barChartButton;
    private JButton pieChartButton;
    private JButton среднееКоличествоФильмовВButton;
    private DataOutputStream output;
    //private static ObjectInputStream input;

    public AdminMenuFrame(JFrame frame, DataOutputStream dataOutputStream) {
        this.frame=frame;
        this.output = dataOutputStream;

        addFilm.addActionListener(e -> {
                this.frame.dispose();
                openAddingFilm();
        });

        addingSessionToAffiche.addActionListener(e -> {
            this.frame.dispose();
            opedAddingSessionToAffiche();
        });

        films.addActionListener(e -> {
            this.frame.dispose();
            try {
                openFilms();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        seeAndDeleteAffiche.addActionListener(e -> {
            this.frame.dispose();
            try {
                openAffiche();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });


        exit.addActionListener(e -> {
            this.frame.dispose();
        });
        расчетСреднейПродолжительностиФильмаButton.addActionListener(e -> {
         this.frame.dispose();
            try {
                openfunc();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });

        barChartButton.addActionListener(e ->  {
            this.frame.dispose();
            try {
                openBarChart();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }

        });

        pieChartButton.addActionListener(e -> {
            this.frame.dispose();
            try {
                openPieChart();
            } catch (ProjectException e1) {
                e1.printStackTrace();
            }
        });
    }

    public AdminMenuFrame(DataOutputStream dataOutputStream) {
        this.output = dataOutputStream;
    }

    private void openFilms() throws ProjectException {
        JFrame filmFrame = new JFrame("Просмотр фильмов");
        filmFrame.setBounds(650, 300, 800, 300);
        filmFrame.setContentPane(new FilmsFrame(filmFrame, output).getPanel);
        filmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmFrame.pack();
        filmFrame.setVisible(true);
    }
    private void openPieChart() throws ProjectException {
        PieChartExample example = new PieChartExample("Количество жанкров в прокате");
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
    }
    private void openfunc() throws ProjectException {
        JFrame avg = new JFrame("Просмотр фильмов");
        avg.setBounds(650, 300, 800, 300);
        avg.setContentPane(new avg_film(avg, output).avg_filmPanel);
        avg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        avg.pack();
        avg.setVisible(true);
    }
    private void openBarChart() throws ProjectException {
        XYLineChartExample example = new XYLineChartExample("График посещаемости");
        example.setSize(800, 400);
        example.setLocationRelativeTo(null);
        example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        example.setVisible(true);
//        BarChart chart = new BarChart();
//        get vax = new get();
//        int a = vax.getOne();
//        int b = vax.getTwo();
//        int c = vax.getThree();
//        int d = vax.getFour();
////        chart.addBar(Color.red, a);
////        chart.addBar(Color.green, b);
////        chart.addBar(Color.blue, c);
////        chart.addBar(Color.black, d);
//        frame.setBounds(650, 300, 800, 300);
//        frame.setLocation(400,400);
//        frame.getContentPane().add(chart);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
    }
//    private void openGraphic() throws ProjectException {
//        JFrame f = new JFrame("PieChart");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.getContentPane().add(new PieChartPanel());
//        f.setSize(400,400);
//        f.setLocation(200,200);
//        f.setVisible(true);
//    }

    private void opedAddingSessionToAffiche() {
        JFrame sessionFrame = new JFrame("Добавление cеанса");
        sessionFrame.setBounds(650, 300, 800, 300);
        sessionFrame.setContentPane(new AddSessionToAfficheFrame(sessionFrame, output).adminAddingSessionPanel);
        sessionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sessionFrame.pack();
        sessionFrame.setVisible(true);
    }

    private void openAddingFilm(){
        JFrame filmFrame = new JFrame("Добавление фильма");
        filmFrame.setBounds(650, 300, 800, 300);
        filmFrame.setContentPane(new AdminAddFilmFrame(filmFrame, output).adminAddingFilmPanel);
        filmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        filmFrame.pack();
        filmFrame.setVisible(true);
    }

    private void openAffiche() throws ProjectException {
        JFrame afficheFrame = new JFrame("Просмотр афиши");
        afficheFrame.setBounds(650, 300, 800, 300);
        afficheFrame.setContentPane(new AdminAfficheFrame(afficheFrame, output).getPanel);
        afficheFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        afficheFrame.pack();
        afficheFrame.setVisible(true);
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
