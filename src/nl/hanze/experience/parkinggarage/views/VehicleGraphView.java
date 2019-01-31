package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;

import nl.hanze.experience.parkinggarage.models.VehicleGraphModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * The view for the vehicle graph
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class VehicleGraphView extends JPanelView {
    private static final int HEIGHT = 450;
    private static final int WIDTH = 700;

    private TimeSeriesCollection  timeSeriesCollection;
    private JFreeChart jFreeChart;

    /**
     * Make new view vehicle graph
     */
    public VehicleGraphView() {
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setAlignmentY(TOP_ALIGNMENT);
        this.setSize(HEIGHT, WIDTH);
        this.setBackground(Color.WHITE);

        timeSeriesCollection = new TimeSeriesCollection();
        jFreeChart = ChartFactory.createTimeSeriesChart("Vehicles in garage",
                "Date",
                "Amount of cars",
                timeSeriesCollection,
                true,
                true,
                false
        );
        XYPlot xyPlot = jFreeChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(255, 159, 15));
        renderer.setSeriesPaint(1, new Color(255, 30, 30));
        renderer.setSeriesPaint(2, new Color(68, 68, 68));
        renderer.setSeriesPaint(3, new Color(100, 255, 255));
        renderer.setBaseShapesVisible(false);
        xyPlot.setRenderer(renderer);
        xyPlot.setBackgroundPaint(new Color(255, 255, 255));
        DateAxis da = (DateAxis) xyPlot.getDomainAxis();
        DateFormat df;
        df = new SimpleDateFormat("MM-dd HH:mm");
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
        VehicleGraphModel vehicleGraphModel = (VehicleGraphModel) model;
        timeSeriesCollection = vehicleGraphModel.getXYDataset();
        jFreeChart.getXYPlot().setDataset(timeSeriesCollection);
    }
}
