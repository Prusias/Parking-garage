package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;

import nl.hanze.experience.parkinggarage.models.VehicleGraphModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class VehicleGraphView extends JPanelView {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;

    private TimeSeriesCollection  timeSeriesCollection;
    private JFreeChart jFreeChart;

    public VehicleGraphView() {
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.setBorder(new LineBorder(Color.BLACK));

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
        DateAxis da = (DateAxis) xyPlot.getDomainAxis();
        DateFormat df;
        df = new SimpleDateFormat("MM-dd HH:mm");
        da.setDateFormatOverride(df);
        jFreeChart.setAntiAlias(true);
        jFreeChart.setTextAntiAlias(true);
        jFreeChart.setBorderVisible(true);
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        chartPanel.setSize(WIDTH, HEIGHT);
        chartPanel.setMaximumDrawHeight(HEIGHT * 2);
        chartPanel.setMaximumDrawWidth(WIDTH * 2);
        add(chartPanel);
    }

    @Override
    public void update(Model model) {
        VehicleGraphModel vehicleGraphModel = (VehicleGraphModel) model;
        timeSeriesCollection = vehicleGraphModel.getXYDataset();
        jFreeChart.getXYPlot().setDataset(timeSeriesCollection);
    }
}
