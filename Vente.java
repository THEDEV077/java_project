public class Vente {
    public int id;
    public String date;
    public String produit;
    public String categorie;
    public int quantite;
    public double prixUnitaire;
    public double total;

    public Vente(int id, String date, String produit, String categorie, int quantite, double prixUnitaire, double total) {
        this.id = id;
        this.date = date;
        this.produit = produit;
        this.categorie = categorie;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.total = total;
    }

    public String toString() {
        return "[" + id + "] " + produit + " (" + categorie + ") - " + quantite + " x " + prixUnitaire + " = " + total;
    }
}
