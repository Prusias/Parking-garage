package nl.hanze.experience.parkinggarage.views;

import nl.hanze.experience.mvc.JPanelView;
import nl.hanze.experience.mvc.Model;
import nl.hanze.experience.parkinggarage.models.QueueGraphModel;
import nl.hanze.experience.parkinggarage.models.VehicleGraphModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;


public class QueueGraphView extends JPanelView {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private DefaultCategoryDataset set;
    private JFreeChart jFreeChart;

    public QueueGraphView() {
        set = new DefaultCategoryDataset();
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.setBorder(new LineBorder(Color.BLACK));

        jFreeChart = ChartFactory.createBarChart("Queues Graph",
                "Queues",
                "Axis",
                set,
                PlotOrientation.VERTICAL,
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
        QueueGraphModel queueGraphModel = (QueueGraphModel) model;
        set = queueGraphModel.getDataset();
        CategoryPlot plot = (CategoryPlot)jFreeChart.getPlot();
        plot.setDataset(set);
    }
}
