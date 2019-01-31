package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.models.VehiclePieModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeriesCollection;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * The view for the vehicle graph
 * @author Mike van der Velde and Zein Bseis
 * @version 0.0.4
 * @since 0.0.4
 */
public class VehiclePieView extends JPanelView {
    private static final int HEIGHT = 450;
    private static final int WIDTH = 700;

    private DefaultPieDataset defaultPieDataset;
    private JFreeChart jFreeChart;

    /**
     * Make new view vehicle graph
     */
    public VehiclePieView() {
        this.setBackground(Color.WHITE);
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setAlignmentY(TOP_ALIGNMENT);
        this.setSize(HEIGHT, WIDTH);
        jFreeChart = ChartFactory.createPieChart("Vehicles in garage",
                defaultPieDataset,
                true,
                true,
                false
        );
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
        VehiclePieModel vehiclePieModel = (VehiclePieModel) model;
        defaultPieDataset = vehiclePieModel.getDefaultPieDataset();
        PiePlot piePlot = (PiePlot)jFreeChart.getPlot();
        piePlot.setDataset(defaultPieDataset);
    }
}
