import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Vente> ventes = CSVReaderVente.lireFichier("ventes.csv");

        System.out.println("=== Total par catégorie ===");
        Map<String, Double> totaux = AnalyseVentes.totalParCategorie(ventes);
        totaux.forEach((cat, total) -> System.out.println(cat + ": " + total + " DH"));

        System.out.println("\n=== Produits les plus vendus ===");
        Map<String, Integer> topProduits = AnalyseVentes.produitsLesPlusVendus(ventes);
        topProduits.forEach((p, q) -> System.out.println(p + ": " + q + " unités"));

        System.out.println("\n=== Produit le plus cher ===");
        System.out.println(AnalyseVentes.produitLePlusCher(ventes));

        System.out.println("\n=== Produit le moins cher ===");
        System.out.println(AnalyseVentes.produitLeMoinsCher(ventes));

        System.out.println("\n=== Ventes par mois ===");
        Map<String, Double> ventesMois = AnalyseVentes.ventesParMois(ventes);
        ventesMois.forEach((mois, total) -> System.out.println(mois + ": " + total + " DH"));
        //GraphiqueVentes.afficherGraphique(ventesMois);
    }
}
