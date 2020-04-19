package com.multilive;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.PolynomialFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Parabola extends ApplicationFrame {

    //private Integer value = Integer.valueOf(-1);

    public Parabola(final String title) {
        super(title);
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Parabola", "X", "Y", createDataset((int) JSONAPI.a),
                PlotOrientation.VERTICAL, true, true, false);
        final XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer r = (XYLineAndShapeRenderer) plot.getRenderer();
        r.setBaseShapesVisible(true);
        r.setSeriesShape(0, new Rectangle(-6, -6, 12, 12));
        final ChartPanel chartPanel = new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1280, 720);
            }
        };
        add(chartPanel, BorderLayout.CENTER);
        JPanel p = new JPanel();
        JSpinner s = new JSpinner();
        s.setValue((int) JSONAPI.a);
       // s.addChangeListener(new ChangeListener() {

       //     @Override
      //      public void stateChanged(ChangeEvent e) {
       //         JSpinner s = (JSpinner) e.getSource();
       //         int v = ((Number) s.getValue()).intValue();
       //         plot.setDataset(createDataset(v));
       //     }
       // });
       // p.add(new JLabel("a"));
       // p.add(s);
        add(p, BorderLayout.SOUTH);
    }

    private XYDataset createDataset(Integer value) {
        double[] array = {JSONAPI.c, JSONAPI.b, JSONAPI.a};
        Function2D p = new PolynomialFunction2D(array);
        return DatasetUtilities.sampleFunction2D(
                p, -20.0, 20.0, 50, "y = " + JSONAPI.a + "x² " + "+ " + JSONAPI.b + "x " + "+ " + JSONAPI.c + " {-20…20}");

    }

    public static int start() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                final Parabola demo = new Parabola("Rysowanie paraboli");
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
            }
        });


        return 0;
    }

}