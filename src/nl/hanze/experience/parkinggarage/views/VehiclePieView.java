package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.models.VehiclePieModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

/**
 * The view for the vehicle graph
 * @author Mike van der Velde
 * @author Zein Bseis
 * @author Steven Woudstra
 * @author Ivo Gerner
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

        jFreeChart = ChartFactory.createPieChart("Vehicle types in garage",
                defaultPieDataset,
                true,
                true,
                false
        );
        PiePlot plot = (PiePlot) jFreeChart.getPlot();
        plot.setSectionPaint("Cars with a subscription", new Color(255, 30, 30));
        plot.setSectionPaint("Cars with a reservation", new Color(100, 255, 255));
        plot.setSectionPaint("Cars with a ticket", new Color(168, 168, 168));
        plot.setSectionPaint("Electric cars with a ticket", new Color(10, 100, 0));
        plot.setSectionPaint("Motorcycles with a ticket", new Color(255, 155, 50));
        plot.setLabelGenerator(null);
        plot.setBackgroundPaint(new Color(255, 255, 255));
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
