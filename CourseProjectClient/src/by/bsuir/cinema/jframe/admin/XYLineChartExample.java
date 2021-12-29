package by.bsuir.cinema.jframe.admin;

import javax.swing.*;

import by.bsuir.cinema.exception.ProjectException;
import org.jfree.chart.ChartFactory;
        import org.jfree.chart.ChartPanel;
        import org.jfree.chart.JFreeChart;
        import org.jfree.chart.plot.PlotOrientation;
        import org.jfree.data.xy.XYDataset;
        import org.jfree.data.xy.XYSeries;
        import org.jfree.data.xy.XYSeriesCollection;

import java.io.DataOutputStream;

/**
 * @author imssbora
 */
public class XYLineChartExample extends JFrame {
    private static final long serialVersionUID = 6294689542092367723L;

    public XYLineChartExample(String title) {
        super(title);

        // Create dataset
        XYDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График посещаемости",
                "Года",
                "Млн. Человек",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset() {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries series = new XYSeries("Y = X");
        series.add(2013, 176);
        series.add(2014, 176);
        series.add(2015, 174);
        series.add(2016, 192);
        series.add(2017, 212);
        series.add(2018, 200);
        series.add(2019, 205);

        //Add series to dataset
        dataset.addSeries(series);

        return dataset;
    }
}