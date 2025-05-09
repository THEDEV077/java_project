import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.awt.image.BufferedImage;

public class Graphs extends JFrame{

    public static Integer X_SIZE = 500;
    public static Integer Y_SIZE = 500;

    public static BufferedImage Diagramme_de_baton(Hashtable data, String title, String xLabel, String yLabel)
    {
        // Create a dataset for the chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Enumeration keys = data.keys();
        for(int i = 0; i < data.size(); i++)
        {
            Object key = keys.nextElement();
            dataset.addValue((Number)data.get(key), key.toString(), key.toString());
        }

        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
            title, // Chart title
            xLabel,            // X-axis label
            yLabel,         // Y-axis label
            dataset            // Dataset
        );

        // Return the chart as an image
        return barChart.createBufferedImage(X_SIZE, Y_SIZE);
    }

    public static BufferedImage Camembert(Hashtable data, String title)
    {
        // Create a dataset for the chart
        DefaultPieDataset dataset = new DefaultPieDataset();

        Enumeration keys = data.keys();
        for(int i = 0; i < data.size(); i++)
        {
            Object key = keys.nextElement();
            ((DefaultPieDataset) dataset).setValue(key.toString(), (Number) data.get(key));
        }

        // Create a bar chart
        JFreeChart piechart = ChartFactory.createPieChart(
            title,          // Chart title
            dataset            // Dataset
        );

        // Create and display the chart panel
        ChartPanel chartPanel = new ChartPanel(piechart);
        javax.swing.JFrame frame = new javax.swing.JFrame();
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);

        return piechart.createBufferedImage(X_SIZE, Y_SIZE);
    }
    public static BufferedImage LineChart(Hashtable<LocalDate, Double> data, String title, String xLabel, String yLabel)
    {
        TimeSeries series = new TimeSeries("Revenue");
            

        Enumeration<LocalDate> keys = data.keys();
        while (keys.hasMoreElements()) {
            LocalDate date = keys.nextElement();
            double revenue = data.get(date);
            series.add(new Day(date.getDayOfMonth(), date.getMonthValue(), date.getYear()), revenue);
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Revenue Over Time",
                "Date",
                "Revenue",
                dataset,
                false,
                true,
                false
        );
        javax.swing.JFrame frame = new javax.swing.JFrame();
        ChartPanel linechart_panel = new ChartPanel(chart);
        frame.setContentPane(linechart_panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        return chart.createBufferedImage(X_SIZE, Y_SIZE);
    }
    public static List<BufferedImage> ProcessAllGraphs(List<Vente> ventes)
    {
        List<BufferedImage> graphImages = new ArrayList<BufferedImage>();

        graphImages.add(Graphs.Diagramme_de_baton(CalculeVentes.RevenueParProduit(ventes), "Graphe de chaque Produit et la somme de ses ventes", "Produits", "Revenue (DH)"));
        graphImages.add(Graphs.Camembert(CalculeVentes.RevenueParCategorie(ventes), "Graphe de chaque Produit et la somme de ses ventes"));
        graphImages.add(Graphs.LineChart(CalculeVentes.RevenueParDate(ventes), "Revenue par Date", "Dates", "Revenue (DH)"));
        graphImages.add(Graphs.LineChart(CalculeVentes.RevenueParDate(ventes, "Informatique"), "Revenue par Date", "Dates", "Revenue (DH)"));
        graphImages.add(Graphs.LineChart(CalculeVentes.RevenueParDate(ventes, "Electronique"), "Revenue par Date", "Dates", "Revenue (DH)"));
        graphImages.add(Graphs.Diagramme_de_baton(CalculeVentes.QuantiteParProduit(ventes), "Quantité vendue par produits", "Produits", "Quantité"));

        return graphImages;
    }
}
