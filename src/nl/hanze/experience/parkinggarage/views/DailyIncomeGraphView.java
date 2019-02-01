package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.models.DailyIncomeGraphModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The view for the vehicle graph
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
 * @version 0.0.4
 * @since 0.0.4
 */
public class DailyIncomeGraphView extends JPanelView {
    private static final int HEIGHT = 450;
    private static final int WIDTH = 700;

    private TimeSeriesCollection  timeSeriesCollection;
    private JFreeChart jFreeChart;

    /**
     * Make new view vehicle graph
     */
    public DailyIncomeGraphView() {
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setAlignmentY(TOP_ALIGNMENT);
        this.setSize(HEIGHT, WIDTH);
        this.setBackground(Color.WHITE);

        timeSeriesCollection = new TimeSeriesCollection();
        jFreeChart = ChartFactory.createTimeSeriesChart("Daily Income",
                "Date",
                "Income in €",
                timeSeriesCollection,
                true,
                true,
                false
        );
        XYPlot xyPlot = jFreeChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(8, 124, 22));
        renderer.setBaseShapesVisible(false);
        xyPlot.setRenderer(renderer);
        xyPlot.setBackgroundPaint(new Color(255, 255, 255));
        DateAxis da = (DateAxis) xyPlot.getDomainAxis();
        DateFormat df;
        df = new SimpleDateFormat("MM-dd");
        da.setDateFormatOverride(df);
        jFreeChart.setAntiAlias(true);
        jFreeChart.setTextAntiAlias(true);
        jFreeChart.setBorderVisible(false);
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        chartPanel.setSize(WIDTH, HEIGHT);
        chartPanel.setMaximumDrawHeight(HEIGHT * 2);
        chartPanel.setMaximumDrawWidth(WIDTH * 2);
        add(chartPanel);
    }

    /**
     * update the information in the graph
     * @param model Model with the new information
     */
    @Override
    public void update(Model model) {
        DailyIncomeGraphModel dailyIncomeGraphModel = (DailyIncomeGraphModel) model;
        timeSeriesCollection = dailyIncomeGraphModel.getXYDataset();
        jFreeChart.getXYPlot().setDataset(timeSeriesCollection);
    }
}
