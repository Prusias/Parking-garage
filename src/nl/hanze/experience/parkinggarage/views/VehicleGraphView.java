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

/**
 * The view for the vehicle graph
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class VehicleGraphView extends JPanelView {

    private XYDataset xyDataset;
    private JFreeChart jFreeChart;

    /**
     * Make new view vehicle graph
     */
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

    /**
     * update the information in the graph
     * @param model Model with the new information
     */
    @Override
    public void update(Model model) {
        VehicleGraphModel vehicleGraphModel = (VehicleGraphModel) model;
        xyDataset = vehicleGraphModel.getXYDataset();
        jFreeChart.getXYPlot().setDataset(xyDataset);
    }
}
