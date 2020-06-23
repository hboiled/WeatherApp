
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 61406
 */
public class Test extends JFrame {

    private ChartTest chartTest;
    private Scraper scraper;
    
    public Test() {        
        scraper = new Scraper();
        chartTest = new ChartTest(scraper.scrapeWeather());
        initUI();
    }

    private void initUI() {

        XYDataset dataset = chartTest.createDataset();
        JFreeChart chart = chartTest.createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        add(chartPanel);

        pack();
        setTitle("Weather");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(() -> {

            var ex = new Test();
            ex.setVisible(true);
        });
    }
    
}
