
import Weather.WeatherData;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.Random;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 61406
 */
public class ChartTest {
    private List<WeatherData> data;    
    
    public ChartTest(List<WeatherData> data) {
        this.data = data;
    }
    
    public XYDataset createDataset() {

        Random rando = new Random();
        
        XYSeries series = new XYSeries("Temp C");
        XYSeries series2 = new XYSeries("Rainfall");
        
        for (int i = 0; i < data.size(); i++) {
            series.add(data.get(i).getTime(), data.get(i).getTemp());
            series2.add(data.get(i).getTime(), data.get(i).getPrecip());
        }
              

        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        dataset.addSeries(series2);

        return dataset;
    }
    
    public JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Rainfall Chance",
                "Time",
                "%",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Rainfall",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }
}
