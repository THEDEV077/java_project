import java.util.List;

public class Interface {
    public static List<Vente> AddSale(List<Vente> ventes, Vente vente)
    {
        ventes.add(vente);
        return ventes;
    }

    public static List<Vente> DeleteSale(List<Vente> ventes, Vente vente)
    {
        ventes.remove(vente);
        return ventes;
    }
    public static List<Vente> DeleteSale(List<Vente> ventes, int id)
    {
        int index = 0;
        while(ventes.get(index).ID_Vente != id) 
        {
            index += 1;
        }
        ventes.remove(index);
        return ventes;
    }

    public static List<Vente> ModifySale(List<Vente> ventes, Vente newvente)
    {
        int index = 0;
        while(ventes.get(index).ID_Vente != newvente.ID_Vente) 
        {
            index += 1;
        }
        ventes.set(index, newvente);
        return ventes;
    }
}
