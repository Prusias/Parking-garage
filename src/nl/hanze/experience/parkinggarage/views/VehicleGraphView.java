package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.*;

import nl.hanze.experience.parkinggarage.models.VehicleGraphModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class VehicleGraphView extends JPanelView {

    private XYDataset xyDataset;
    private JFreeChart jFreeChart;

    public VehicleGraphView() {
        this.setSize(400, 400);

        xyDataset = new TimeSeriesCollection();
        jFreeChart = ChartFactory.createXYLineChart("Vehicles in garage",
                "Date",
                "Amount of cars",
                xyDataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(jFreeChart);
        chartPanel.setSize(400, 400);
        add(chartPanel);
    }

    @Override
    public void update(Model model) {
        VehicleGraphModel vehicleGraphModel = (VehicleGraphModel) model;
        xyDataset = vehicleGraphModel.getXYDataset();
        jFreeChart.getXYPlot().setDataset(xyDataset);
    }
}
