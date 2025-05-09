import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;

public class CalculeVentes {
    public static Hashtable<String, Double> RevenueParCategorie(List<Vente> ventes)
    {
        Hashtable<String, Double> ht = new Hashtable<>();
        for(int i = 0; i < ventes.size(); i++)
        {
            String categorie = ventes.get(i).Categorie;
            ht.put(categorie, (ht.getOrDefault(categorie, 0.0) + ventes.get(i).Prix_Unitaire));
        }
        return ht;
    }
    public static Hashtable<String, Double> RevenueParProduit(List<Vente> ventes)
    {
        Hashtable<String, Double> ht = new Hashtable<>();
        for(int i = 0; i < ventes.size(); i++)
        {
            String produit = ventes.get(i).Produit;
            ht.put(produit, (ht.getOrDefault(produit, 0.0) + ventes.get(i).Total));
        }
        return ht;
    }
    public static Hashtable<String, Integer> QuantiteParProduit(List<Vente> ventes)
    {
        Hashtable<String, Integer> ht = new Hashtable<>();
        for(int i = 0; i < ventes.size(); i++)
        {
            String produit = ventes.get(i).Produit;
            ht.put(produit, (ht.getOrDefault(produit, 0) + ventes.get(i).Quantite));
        }
        return ht;
    }
    public static Hashtable<LocalDate, Double> RevenueParDate(List<Vente> ventes)
    {
        Hashtable<LocalDate, Double> ht = new Hashtable<>();
        for(int i = 0; i < ventes.size(); i++)
        {
            LocalDate date = ventes.get(i).Date_Vente;
            ht.put(date, (ht.getOrDefault(date, 0.0) + ventes.get(i).Total));
        }
        return ht;
    }
    public static Hashtable<LocalDate, Double> RevenueParDate(List<Vente> ventes, String categorie)
    {
        Hashtable<LocalDate, Double> ht = new Hashtable<>();
        for(int i = 0; i < ventes.size(); i++)
        {
            if (ventes.get(i).Categorie.equals(categorie))
            {
                LocalDate date = ventes.get(i).Date_Vente;
                ht.put(date, ht.getOrDefault(date, 0.0) + ventes.get(i).Total);
            }
        }
        return ht;
    }
    
    public static Hashtable<String, Double> MoyennePrixParProduit(List<Vente> ventes)
    {
        Hashtable<String, Double> MoyprixHt = new Hashtable<>();
        Hashtable<String, Integer> CountHt = new Hashtable<>(); 
        for(int i = 0; i < ventes.size(); i++)
        {
            String produit = ventes.get(i).Produit;
            MoyprixHt.put(produit, MoyprixHt.getOrDefault(produit, 0.0) + ventes.get(i).Prix_Unitaire);
            CountHt.put(produit, CountHt.getOrDefault(produit, 0) + 1);
        }
        for(int i = 0; i < MoyprixHt.size(); i++)
        {
            String produit = ventes.get(i).Produit;

            System.out.println(MoyprixHt.get(produit));
            System.out.println(CountHt.get(produit));

            MoyprixHt.put(produit, MoyprixHt.get(produit)/CountHt.get(produit));
            
        }
        return MoyprixHt;
    }
    public static Hashtable<String, Double> Produit_par_Prix(List<Vente> ventes)
    {
        Hashtable<String, Double> ht = new Hashtable<>();
        for(int i = 0; i < ventes.size(); i++)
        {
            String produit = ventes.get(i).Produit;
            ht.put(produit, (ht.getOrDefault(produit, 0.0) + ventes.get(i).Prix_Unitaire));
        }
        return ht;
    }
    public static int nbrVentes_Categorie(List<Vente> ventes, String categorie) {
		int count = 0;
		for (int i = 0; i < ventes.size(); i++) {
			if (ventes.get(i).Categorie.equals(categorie)) {
				count++;
			}
		}
		return count;
	}

	public static int Produits_PlusVendues(List<Vente> ventes) {
		Hashtable<String, Integer> ht = new Hashtable<>();
		for (Vente vente : ventes) {
			ht.put(vente.Produit, ht.getOrDefault(vente.ID_Vente, 0) + 1);
		}

		int max = 0;
		for (int count : ht.values()) {
			if (count > max) {
				max = count;
			}
		}
		return max;
	}
	public static double Produit_PlusCher(List<Vente> ventes)
	{
		double max = 0;
		for(int i = 0; i < ventes.size(); i++)
		{
			double prix = ventes.get(i).Prix_Unitaire;
			if(prix > max)
			{
				max = prix;
			}
		}
		return max;
	}
	public static double Produit_MoinsCher(List<Vente> ventes)
	{
		double min = Double.POSITIVE_INFINITY;
		for(int i = 0; i < ventes.size(); i++)
		{
			double prix = ventes.get(i).Prix_Unitaire;
			if(prix < min)
			{
				min = prix;
			}
		}
		return min;
	}
    
}   
