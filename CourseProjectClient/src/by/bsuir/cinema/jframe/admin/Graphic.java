//package by.bsuir.cinema.jframe.admin;
////
////import static by.bsuir.cinema.controller.Starter.user;
//////
////import by.bsuir.cinema.exception.ProjectException;
////import by.bsuir.cinema.logic.FilmLogic;
////import by.bsuir.cinema.jframe.admin.get;
////import by.bsuir.cinema.logic.FilmLogic;
//////
//
//import by.bsuir.cinema.exception.ProjectException;
//import by.bsuir.cinema.logic.FilmLogic;
//import by.bsuir.cinema.database.dao.FilmDao;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.font.*;
//import java.awt.geom.*;
//import java.awt.image.BufferedImage;
//import java.text.NumberFormat;
//import javax.swing.*;
//
//
//class PieChartPanel extends JPanel
//{
//    BufferedImage image;
//    final int PAD = 30;
//    Font font;
//    NumberFormat nf;
//    boolean showConstructionMarkers;
//
//    public PieChartPanel ()
//
//
//    {
//        JTextArea Nameofvalue;
//        font = new Font("lucida sans regular", Font.PLAIN, 20);
//        nf = NumberFormat.getPercentInstance();
//        showConstructionMarkers = true;
//        addComponentListener(new ComponentAdapter()
//        {
//            public void componentResized(ComponentEvent e)
//            {
//                image = null;
//            }
//        });
//    }
//
//    protected void paintComponent(Graphics g)
//    {
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D)g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        if(image == null)
//            createChartImage();
//        g2.drawImage(image, 0, 0, this);
//    }
//
//    private void createChartImage()
//    {
//        FilmLogic axs = new FilmLogic();
//        int comedy=0;
//        try {
//            comedy = FilmLogic.Find_comedy();
//        } catch (ProjectException e) {
//            e.printStackTrace();
//        }
//        int fantasy=0;
//        try {
//            fantasy = FilmLogic.Find_fantasy();
//        } catch (ProjectException e) {
//            e.printStackTrace();
//        }
//        int thriller=0;
//        try {
//            thriller = FilmLogic.Find_thiller();
//        } catch (ProjectException e) {
//            e.printStackTrace();
//        }
//        int action = 0;
//        try {
//            action = FilmLogic.Find_thiller();
//        } catch (ProjectException e) {
//            e.printStackTrace();
//        }
//        int [] data = new int[4];
//        data[0] = comedy;
//        data[1] = fantasy;
//        data[2] = thriller;
//        data[3] = action;
//        int w = getWidth();
//        int h = getHeight();
//        int cx = w/2;
//        int cy = h/2;
//        image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g2 = image.createGraphics();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setPaint(Color.white);
//        g2.fillRect(0, 0, w, h);
//        g2.setPaint(Color.black);
//        // draw circle
//        int dia = Math.min(w,h) - 2*PAD;
//        g2.draw(new Ellipse2D.Double(cx - dia/2, cy - dia/2, dia, dia));
//        double total = 0;
//        for(int j = 0; j < data.length; j++)
//            total += data[j];
//        double theta = 0, phi = 0;
//        double x, y;
//        for(int j = 0; j < data.length; j++)
//        {
//            // draw leading edge of pie slice
//            x = cx + (dia/2) * Math.cos(theta);
//            y = cy + (dia/2) * Math.sin(theta);
//            g2.draw(new Line2D.Double(cx, cy, x, y));
//            // angle of pie slice
//            phi = (data[j]/total) * 2 * Math.PI;
//            if(showConstructionMarkers)
//            {
//                // draw centerline of slice
//                g2.setXORMode(Color.cyan);
//                x = cx + (dia/2) * Math.cos(theta + phi/2);
//                y = cy + (dia/2) * Math.sin(theta + phi/2);
//                g2.draw(new Line2D.Double(cx, cy, x, y));
//                g2.setPaintMode();
//            }
//
//            // mark text center on centerline
//            x = cx + (9*dia/24) * Math.cos(theta + phi/2);
//            y = cy + (9*dia/24) * Math.sin(theta + phi/2);
//            if(showConstructionMarkers)
//            {
//                g2.setPaint(Color.blue);
//                g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
//                g2.setPaint(Color.black);
//            }
//
//            // draw text for data value inside pie slice
//            g2.setFont(font);
//            String s = String.valueOf(data[j]);
//            FontRenderContext frc = g2.getFontRenderContext();
//            float textWidth = (float)font.getStringBounds(s, frc).getWidth();
//            LineMetrics lm = font.getLineMetrics(s, frc);
//            float sx = (float)(x - textWidth/2);
//            float sy = (float)(y + lm.getAscent()/2);
//            g2.drawString(s, sx, sy);
//
//            // locate text center for outer, percent values
//            x = cx + (dia/2 + 4*PAD/5) * Math.cos(theta + phi/2);
//            y = cy + (dia/2 + 4*PAD/5) * Math.sin(theta+ phi/2);
//            if(showConstructionMarkers)
//            {
//                g2.setPaint(Color.red);
//                g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
//                g2.setPaint(Color.black);
//            }
//
//            // show percent values on outside of circle
//            s = nf.format(data[j]/total);
//            textWidth = (float)font.getStringBounds(s, frc).getWidth();
//            lm = font.getLineMetrics(s, frc);
//            sx = (float)(x - textWidth/2);
//            sy = (float)(y + lm.getAscent()/2);
//            g2.drawString(s, sx, sy);
//
//            theta += phi;
//        }
//        g2.dispose();
//    }
//
//    public void toggleVisibility()
//    {
//        showConstructionMarkers = !showConstructionMarkers;
//        image = null;
//        repaint();
//    }
//}
//
///**
// * double-click PieChartPanel to reveal/hide construction lines
// */
//
////import javax.swing.*;
////import java.awt.*;
////import java.io.DataOutputStream;
////import java.io.IOException;
////import java.io.OutputStream;
////
////import java.awt.Color;
////import java.awt.Dimension;
////import java.awt.Graphics;
////import java.awt.Rectangle;
////import java.util.Random;
////
/////**
//// *
//// * @author ilya
//// */
////
////class PieChart extends javax.swing.JPanel {
////    public JPanel PieChartPanel;
////    Slice[] slices;
////    String[] name={"пк","телефон","ноутбук","периферия"};
////    /**
////     * Creates new form PieChart3
////     * @param qwer
////     */
////    public PieChart(Double qwer[]) {
////        Random random = new Random();
////        slices= new Slice[4];
////        slices[0]=new Slice(qwer[0],Color.RED);
////        slices[1]=new Slice(qwer[1],Color.BLUE);
////        slices[2]=new Slice(qwer[2],Color.YELLOW);
////        slices[3]=new Slice(qwer[3],Color.GREEN);
////        initComponents();
////        setSize(new Dimension(400,400));
////    }
////    @Override
////    public void paintComponent(Graphics g) {
////        super.paintComponent(g);
////        double total = 0.0D;
////        for (Slice slice : slices) {
////            total += slice.value;
////        }
////        Rectangle area=getBounds();
////        double curValue = 0.0D;
////        int startAngle;
////        for (Slice slice : slices) {
////            startAngle = (int) (curValue * 360 / total);
////            int arcAngle = (int) (slice.value * 360 / total);
////            g.setColor(slice.color);
////            g.fillArc(area.x/2, area.y/2, area.width/2, area.height/2,
////                    startAngle, arcAngle);
////            curValue += slice.value;
////        }
////        int y=30;
////        for(int i=0;i<name.length;i++)
////        {g.setColor(slices[i].color);
////            g.drawString(name[i], 250, y);
////            y+=20;
////        }
////    }
////
////    @SuppressWarnings("unchecked")
////    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
////    private void initComponents() {
////
////        setAutoscrolls(true);
////
////        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
////        this.setLayout(layout);
////        layout.setHorizontalGroup(
////                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
////                        .addGap(0, 400, Short.MAX_VALUE)
////        );
////        layout.setVerticalGroup(
////                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
////                        .addGap(0, 300, Short.MAX_VALUE)
////        );
////    }// </editor-fold>//GEN-END:initComponents
////    class Slice {
////        double value;
////        Color color;
////        public Slice(double value, Color color) {
////            this.value = value;
////            this.color = color;
////        }
////    }
////
////    // Variables declaration - do not modify//GEN-BEGIN:variables
////    // End of variables declaration//GEN-END:variables
////}
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
//////class GraphFrame extends JFrame {
//////
//////    GraphFrame() {
//////        setLayout(new FlowLayout());
//////        add(new GraphTotal());
//////        pack();
//////    }
//////}
//////
//////class GraphTotal extends JComponent {
//////
//////    static final String[] xlabs = {"100", "200", "300", "400", "500"};
//////    static final double[] tottimes = {1.03, 1.52, 1.20, 3.23, 4.11, 5.23};
//////
//////    public Dimension getPreferredSize() {
//////        return new Dimension(550, 500);
//////    }
//////
//////    public void paintComponent(Graphics g) {
//////
//////        Graphics2D g2 = (Graphics2D) g;
//////        g2.setFont(new Font("Arial", Font.ITALIC, 18));
//////        g2.drawString("Рост цен на билеты", 150, 20);
//////        g2.drawLine(0, 450, 520, 450);
//////        g2.drawLine(50, 0, 50, 500);
//////        g2.setFont(new Font("Arial", Font.PLAIN, 14));
//////
//////        for (int i = 0; i < 5; i++) {
//////            int ix = i * 100 + 100;
//////            g2.drawLine(ix, 440, ix, 460); // lines on horisontal line
//////            g2.drawString(xlabs[i], ix - 10, 470);
//////        }
//////
//////        double scale = 400 / tottimes[4];
//////
//////        for (int i = 0; i < 5; i++) {
//////
//////            int ix0 = i * 100 + 100;
//////            int iy0 = 450 - (int) (tottimes[i] * scale);
//////            g2.drawRect(ix0 - 2, iy0 - 2, 4, 4);
//////
//////            if (i < 4) {
//////                int ix1 = (i + 1) * 100 + 100;
//////                int iy1 = 450 - (int) (tottimes[i + 1] * scale);
//////                g2.drawLine(ix0, iy0, ix1, iy1);
//////            }
//////
//////        }
//////
//////    }
//////}
////
////
////
////
////
////
////
////
////
//////class MyDrawPanel extends JPanel {
//////    private JButton backButton;
//////    public JFrame frame;
//////    private DataOutputStream output;
//////
//////    public void paintComponent(Graphics g) {
//////        Image image = new ImageIcon("bilet.png").getImage();
//////        g.drawImage(image, 3, 4, this);
//////    }}
////
////
////
////
////
////
//////public class Graphic extends JPanel{
//////
//////    public JPanel Graphic;
//////    private JLabel stat;
//////    public JFrame frame;
//////    private DataOutputStream output;
//////
//////
//////    public Graphic(JFrame graphic, DataOutputStream output) {
//////        this.frame = graphic;
//////        this.output = output;
//////        Image image = new ImageIcon("00203.jpg").getImage();
//////        g.drawImage(image, 3, 4, this);
//////
//////    }
//////}
