import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class GraphiqueVentes {

    public static void afficherGraphique(Map<String, Double> ventesParMois) {
        // 1. Préparer le dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map.Entry<String, Double> entry : ventesParMois.entrySet()) {
            dataset.addValue(entry.getValue(), "Ventes", entry.getKey());
        }

        // 2. Créer le graphique
        JFreeChart chart = ChartFactory.createLineChart(
                "Ventes Mensuelles",
                "Mois",
                "Montant (DH)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // 3. Afficher dans une fenêtre Swing
        JFrame frame = new JFrame("Graphique des Ventes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

