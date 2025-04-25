import java.util.*;
import java.util.stream.Collectors;

public class AnalyseVentes {

    public static Map<String, Double> totalParCategorie(List<Vente> ventes) {
        Map<String, Double> map = new HashMap<>();
        for (Vente v : ventes) {
            map.put(v.categorie, map.getOrDefault(v.categorie, 0.0) + v.total);
        }
        return map;
    }

    public static Map<String, Integer> produitsLesPlusVendus(List<Vente> ventes) {
        Map<String, Integer> map = new HashMap<>();
        for (Vente v : ventes) {
            map.put(v.produit, map.getOrDefault(v.produit, 0) + v.quantite);
        }
        return map.entrySet()
                  .stream()
                  .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                  .collect(Collectors.toMap(
                      Map.Entry::getKey, Map.Entry::getValue,
                      (e1, _) -> e1, LinkedHashMap::new));
    }

    public static Vente produitLePlusCher(List<Vente> ventes) {
        return Collections.max(ventes, Comparator.comparingDouble(v -> v.prixUnitaire));
    }

    public static Vente produitLeMoinsCher(List<Vente> ventes) {
        return Collections.min(ventes, Comparator.comparingDouble(v -> v.prixUnitaire));
    }

    public static Map<String, Double> ventesParMois(List<Vente> ventes) {
        Map<String, Double> map = new TreeMap<>();
        for (Vente v : ventes) {
            String mois = v.date.substring(0, 7); // YYYY-MM
            map.put(mois, map.getOrDefault(mois, 0.0) + v.total);
        }
        return map;
    }
}
