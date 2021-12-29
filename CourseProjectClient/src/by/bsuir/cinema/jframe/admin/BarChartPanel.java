package by.bsuir.cinema.jframe.admin;

import by.bsuir.cinema.database.dao.SessionDao;
import by.bsuir.cinema.exception.ProjectException;
import by.bsuir.cinema.logic.SessionLogic;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;


import org.jfree.data.category.CategoryDataset;


import javax.swing.JFrame;

import org.jfree.data.category.DefaultCategoryDataset;



















//class BarChartExample extends JFrame {
//
//    private static final long serialVersionUID = 1L;
//
//    public BarChartExample(String appTitle) {
//        super(appTitle);
//
//        // Create Dataset
//        CategoryDataset dataset = createDataset();
//
//        //Create chart
//        JFreeChart chart=ChartFactory.createBarChart(
//                "Сравнение цен на билеты в разных странах", //Chart Title
//                "Year", // Category axis
//                "Цены на билеты", // Value axis
//                dataset,
//                PlotOrientation.VERTICAL,
//                true,true,false
//        );
//
//        ChartPanel panel=new ChartPanel(chart);
//        setContentPane(panel);
//    }
//
//    private CategoryDataset createDataset() {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
////         int array[] = new int[25];
////         for(int i=0; i<25; i++) {
////             try {
////                 array[i] = SessionLogic.find_price();
////             } catch (ProjectException e) {
////                 e.printStackTrace();
////             }
////         }
//
//        // Population in 2005
//        dataset.addValue(10, "USA", "2005");
//        dataset.addValue(15, "India", "2005");
//        dataset.addValue(20, "China", "2005");
//
//        // Population in 2010
//        dataset.addValue(15, "USA", "2010");
//        dataset.addValue(20, "India", "2010");
//        dataset.addValue(25, "China", "2010");
//
//        // Population in 2015
//        dataset.addValue(20, "USA", "2015");
//        dataset.addValue(25, "India", "2015");
//        dataset.addValue(30, "China", "2015");
//
//        return dataset;
//    }
//}
//class BarChart extends JPanel {
//
//    private Map<Color, Integer> bars =
//            new LinkedHashMap<Color, Integer>();
//
//    /**
//     * Add new bar to chart
//     *
//     * @param color color to display bar
//     * @param value size of bar
//     */
//    public void addBar(Color color, int value) {
//        bars.put(color, value);
//        repaint();
//    }
//
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(bars.size() * 10 + 2, 50);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        // determine longest bar
//        int max = Integer.MIN_VALUE;
//        for (Integer value : bars.values()) {
//            max = Math.max(max, value);
//        }
//
//        // paint bars
//        int width = (getWidth() / 10) - 2;
//        int x = 1;
//        for (Color color : bars.keySet()) {
//            int value = bars.get(color);
//            int height = (int)
//                    ((getHeight() - 5) * ((double) value / max));
//            g.setColor(color);
//            g.fillRect(x, getHeight() - height, width, height);
//            g.setColor(Color.black);
//            g.drawRect(x, getHeight() - height, width, height);
//            x += (width + 2);
//        }
//    }
//}