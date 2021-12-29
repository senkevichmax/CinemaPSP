package by.bsuir.cinema.jframe.admin;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.FilmLogic;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
public class PieChartExample extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public PieChartExample(String title) {
        super(title);

        // Create dataset
        PieDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Жанры в прокате",
                dataset,
                true,
                true,
                false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "Количество {0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private PieDataset createDataset() {
        FilmLogic axs = new FilmLogic();
        int comedy=0;
        try {
            comedy = FilmLogic.Find_comedy();
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        int fantasy=0;
        try {
            fantasy = FilmLogic.Find_fantasy();
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        int thriller=0;
        try {
            thriller = FilmLogic.Find_thiller();
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        int action = 0;
        try {
            action = FilmLogic.Find_thiller();
        } catch (ProjectException e) {
            e.printStackTrace();
        }
        int [] data = new int[4];
        data[0] = comedy;
        data[1] = fantasy;
        data[2] = thriller;
        data[3] = action;
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Комедия", comedy);
        dataset.setValue("Фантастика",fantasy);
        dataset.setValue("Триллеры",thriller);
        dataset.setValue("Боевики", action);
        return dataset;
    }
}